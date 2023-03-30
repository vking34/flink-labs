package com.onemount.ps2.risk_fraud.function;

import com.onemount.ps2.risk_fraud.Transaction;
import com.onemount.ps2.risk_fraud.dto.AccountCampOutput;
import com.onemount.ps2.risk_fraud.key.AccountCampKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

@Slf4j
public class AccountCampKeyedFunction extends KeyedProcessFunction<AccountCampKey, Transaction, AccountCampOutput> {

    private static final int TOTAL_AMOUNT_THRESHOLD = 10000;

    ValueState<Integer> state;

    @Override
    public void open(Configuration parameters) {
        state = getRuntimeContext().getState(new ValueStateDescriptor<>("state", Integer.class));
    }

    @Override
    public void processElement(Transaction transaction, Context context, Collector<AccountCampOutput> collector) throws Exception {
        AccountCampKey key = context.getCurrentKey();
        log.info("Processing key: {}, transaction: {}", key, transaction);

        Integer totalAmount = state.value();
        if (totalAmount == null) {
            totalAmount = 0;
        }

        int newTotalAmount = totalAmount + transaction.getAmount();
        state.update(newTotalAmount);

        if (totalAmount < TOTAL_AMOUNT_THRESHOLD && newTotalAmount > TOTAL_AMOUNT_THRESHOLD)
            collector.collect(new AccountCampOutput(transaction.getDebitAccountNumber()));
    }
}
