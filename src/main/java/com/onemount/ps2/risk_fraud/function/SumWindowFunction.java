package com.onemount.ps2.risk_fraud.function;

import com.onemount.ps2.risk_fraud.model.sink.AccountCampAlert;
import com.onemount.ps2.risk_fraud.key.AccountCampKey;
import com.onemount.ps2.risk_fraud.model.source.Transaction;
import com.onemount.ps2.risk_fraud.state.TransactionSumWindowState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SumWindowFunction extends KeyedProcessFunction<AccountCampKey, Transaction, AccountCampAlert> {

    private final static Logger log = LoggerFactory.getLogger(SumWindowFunction.class);
    private static final int TOTAL_AMOUNT_THRESHOLD = 10000;

    private static final long WINDOW_TIME = 5 * 60 * 1000;

    ValueState<TransactionSumWindowState> state;

    @Override
    public void open(Configuration parameters) {
        state = getRuntimeContext().getState(new ValueStateDescriptor<>("state", TransactionSumWindowState.class));
    }

    @Override
    public void processElement(Transaction transaction,
                               Context context,
                               Collector<AccountCampAlert> collector) throws Exception {
        AccountCampKey key = context.getCurrentKey();
        log.info("Processing key: {}, transaction: {}", key, transaction);

        TransactionSumWindowState windowState = getState();
        TransactionSumWindowState newWindowState = windowState.addTransaction(transaction, WINDOW_TIME);

        if (newWindowState.getSum() > TOTAL_AMOUNT_THRESHOLD) {
            collector.collect(new AccountCampAlert(transaction.getDebitAccountNumber()));
        }

        state.update(newWindowState);
    }

    private TransactionSumWindowState getState() throws Exception {
        TransactionSumWindowState keyedState = state.value();
        if (keyedState == null) {
            keyedState = new TransactionSumWindowState();
        }

        return keyedState;
    }
}
