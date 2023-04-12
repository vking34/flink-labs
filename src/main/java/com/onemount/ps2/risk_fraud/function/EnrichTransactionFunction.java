package com.onemount.ps2.risk_fraud.function;

import com.onemount.ps2.risk_fraud.model.source.DetailTransaction;
import com.onemount.ps2.risk_fraud.model.source.Account;
import com.onemount.ps2.risk_fraud.model.source.Transaction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnrichTransactionFunction extends KeyedCoProcessFunction<String, Transaction, Account, DetailTransaction> {

    private static final Logger log = LoggerFactory.getLogger(EnrichTransactionFunction.class);


    ValueState<Account> accountState;

    @Override
    public void open(Configuration parameters) {
        accountState = getRuntimeContext().getState(new ValueStateDescriptor<>("accountState", Account.class));
    }

    @Override
    public void processElement1(Transaction transaction, Context context, Collector<DetailTransaction> collector) throws Exception {
//        log.info("Processing transaction: {}", transaction);
        Account account = accountState.value();
        DetailTransaction detailTransaction = new DetailTransaction(transaction, account);
//        log.info("Detail Transaction: {}", detailTransaction);
        collector.collect(detailTransaction);
    }

    @Override
    public void processElement2(Account account, Context context, Collector<DetailTransaction> collector) throws Exception {
        log.info("Processing account: {}", account);
        accountState.update(account);
    }
}
