package com.onemount.ps2.risk_fraud.job;

import com.onemount.ps2.risk_fraud.function.CleanUpTimerFunction;
import com.onemount.ps2.risk_fraud.function.EnrichTransactionFunction;
import com.onemount.ps2.risk_fraud.key.KeyGenerator;
import com.onemount.ps2.risk_fraud.model.source.Account;
import com.onemount.ps2.risk_fraud.model.source.Transaction;
import com.onemount.ps2.risk_fraud.util.KafkaConnector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CleanUpTimerJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.enableCheckpointing(60000);
//        env.setStateBackend(StateBackendBuilder.buildRocksDBStateBackend());

        DataStream<Transaction> transactionStream = KafkaConnector.createTransactionStream(env);
        DataStream<Account> accountStream = KafkaConnector.createAccountStream(env);

        transactionStream
                .keyBy(Transaction::getDebitAccountNumber)
                .connect(accountStream.keyBy(Account::getAccountNumber))
                .process(new EnrichTransactionFunction())
                .filter(detailTransaction -> detailTransaction.getDebitAccount() != null && detailTransaction.getDebitAccount().getAccountLevel() > 1)
                .keyBy(KeyGenerator::generateAccountCampKey)
                .process(new CleanUpTimerFunction())
                .print();

        env.execute(CleanUpTimerJob.class.getSimpleName());
    }
}
