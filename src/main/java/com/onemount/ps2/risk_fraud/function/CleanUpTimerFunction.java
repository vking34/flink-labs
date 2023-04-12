package com.onemount.ps2.risk_fraud.function;

import com.onemount.ps2.risk_fraud.key.AccountCampKey;
import com.onemount.ps2.risk_fraud.model.sink.AccountCampAlert;
import com.onemount.ps2.risk_fraud.model.source.DetailTransaction;
import com.onemount.ps2.risk_fraud.state.TransactionSumWindowState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanUpTimerFunction extends KeyedProcessFunction<AccountCampKey, DetailTransaction, AccountCampAlert> {

    private static final Logger log = LoggerFactory.getLogger(CleanUpTimerFunction.class);

    private static final int TOTAL_AMOUNT_THRESHOLD = 10000;

    private static final long WINDOW_TIME = 60 * 1000;

    ValueState<TransactionSumWindowState> state;
    ValueState<Long> timerState;


    @Override
    public void open(Configuration parameters) {
        ValueStateDescriptor<TransactionSumWindowState> stateDescriptor = new ValueStateDescriptor<>("TransactionSumWindowState", TransactionSumWindowState.class);
        state = getRuntimeContext().getState(stateDescriptor);

        timerState = getRuntimeContext().getState(new ValueStateDescriptor<>("timerState", Long.class));
    }

    @Override
    public void processElement(DetailTransaction transaction, Context context, Collector<AccountCampAlert> collector) throws Exception {
        AccountCampKey key = context.getCurrentKey();

        TransactionSumWindowState currentState = getState();
        log.info(
                "Processing key: {}, num of transactions in the window: {}, transaction: {}",
                key,
                currentState.getTransactions().size(),
                transaction
        );
        TransactionSumWindowState newState = currentState.addTransaction(transaction, WINDOW_TIME);

        if (newState.getSum() > TOTAL_AMOUNT_THRESHOLD) {
            log.info("Alerting for key: {}, state: {}", key, newState);
            collector.collect(new AccountCampAlert(transaction.getDebitAccountNumber()));
        }

        state.update(newState);

        Long timer = timerState.value();
        if (timer != null) {
            context.timerService().deleteProcessingTimeTimer(timerState.value());
        }
        long newTimer = transaction.getProcessingTime() + WINDOW_TIME;
        context.timerService().registerProcessingTimeTimer(newTimer);
        timerState.update(newTimer);
        log.info("Timer set to: {}", newTimer);
    }

    private TransactionSumWindowState getState() throws Exception {
        TransactionSumWindowState keyedState = state.value();
        if (keyedState == null) {
            keyedState = new TransactionSumWindowState();
        }

        return keyedState;
    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<AccountCampAlert> out) throws Exception {
        log.info("Timer fired at: {}", timestamp);
        clearState(ctx);
    }

    private void clearState(Context context) throws Exception {
        state.clear();
        timerState.clear();
    }
}
