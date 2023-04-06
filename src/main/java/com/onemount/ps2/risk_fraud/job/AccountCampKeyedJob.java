package com.onemount.ps2.risk_fraud.job;

import com.onemount.ps2.risk_fraud.function.AccountCampKeyedFunction;
import com.onemount.ps2.risk_fraud.key.KeyGenerator;
import com.onemount.ps2.risk_fraud.model.source.Transaction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


public class AccountCampKeyedJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        long now = System.currentTimeMillis();
        DataStream<Transaction> transactionStream = env.fromElements(
                new Transaction("A1", "C1", 3000, now - 230000),
                new Transaction("A1", "C1", 8000, now - 130000),
                new Transaction("A1", "C1", 2000, now - 80000),
                new Transaction("A2", "C1", 500, now),
                new Transaction("A2", "C1", 9600, now),
                new Transaction("A3", "C1", 9600, now)
        );

        transactionStream
                .keyBy(KeyGenerator::generateAccountCampKey)
                .process(new AccountCampKeyedFunction())
                .print();

        env.execute(AccountCampKeyedJob.class.getSimpleName());
    }
}
