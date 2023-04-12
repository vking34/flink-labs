package com.onemount.ps2.risk_fraud.util;

import com.onemount.ps2.risk_fraud.constant.ConfigKey;
import com.onemount.ps2.risk_fraud.constant.DefaultValue;
import com.onemount.ps2.risk_fraud.model.source.Account;
import com.onemount.ps2.risk_fraud.model.source.Transaction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;


public class KafkaConnector {
    private static final String KAFKA_BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String KAFKA_GROUP_ID = "group.id";
    private static final String KAFKA_SECURITY_PROTOCOL = "security.protocol";
    private static final String KAFKA_SSL_TRUSTSTORE_LOCATION = "ssl.truststore.location";
    private static final String KAFKA_SSL_TRUSTSTORE_PASSWORD = "ssl.truststore.password";
    private static final String KAFKA_SSL_KEYSTORE_LOCATION = "ssl.keystore.location";
    private static final String KAFKA_SSL_KEYSTORE_PASSWORD = "ssl.keystore.password";
    private static final String AUTO_OFFSET_RESET = "auto.offset.reset";


    public static DataStream<Transaction> createTransactionStream(StreamExecutionEnvironment env) throws Exception {
        Properties config = ConfigUtil.getProperties();
        Properties properties = getKafkaProperties(config);
        properties.put(KAFKA_GROUP_ID, config.getProperty(ConfigKey.KAFKA_TRANSACTION_GROUP_ID));
        String topic = config.getProperty(ConfigKey.KAFKA_TRANSACTION_TOPIC);

        return env
                .addSource(new FlinkKafkaConsumer<>(topic, new JsonDeserializationSchema<>(Transaction.class), properties))
                .name("transaction-source");
    }

    public static DataStream<Account> createAccountStream(StreamExecutionEnvironment env) throws Exception {
        Properties config = ConfigUtil.getProperties();
        Properties properties = getKafkaProperties(config);
        properties.put(KAFKA_GROUP_ID, config.getProperty(ConfigKey.KAFKA_ACCOUNT_GROUP_ID));
        properties.put(AUTO_OFFSET_RESET, DefaultValue.EARLIEST);
        String topic = config.getProperty(ConfigKey.KAFKA_ACCOUNT_TOPIC);

        return env
                .addSource(new FlinkKafkaConsumer<>(topic, new JsonDeserializationSchema<>(Account.class), properties))
                .name("account-source");
    }

    private static Properties getKafkaProperties(Properties config) {
        Properties properties = new Properties();
        properties.put(KAFKA_BOOTSTRAP_SERVERS, config.getProperty(ConfigKey.KAFKA_BOOTSTRAP_SERVERS));

        String isEnabledSsl = config.getProperty(ConfigKey.KAFKA_SSL_ENABLED, DefaultValue.TRUE);
        if (DefaultValue.TRUE.equals(isEnabledSsl)) {
            properties.put(KAFKA_SECURITY_PROTOCOL, DefaultValue.SSL_PROTOCOL);
            properties.put(KAFKA_SSL_TRUSTSTORE_LOCATION, config.getProperty(ConfigKey.KAFKA_SSL_TRUSTSTORE_LOCATION));
            properties.put(KAFKA_SSL_TRUSTSTORE_PASSWORD, config.getProperty(ConfigKey.KAFKA_SSL_TRUSTSTORE_PASSWORD));
            properties.put(KAFKA_SSL_KEYSTORE_LOCATION, config.getProperty(ConfigKey.KAFKA_SSL_KEYSTORE_LOCATION));
            properties.put(KAFKA_SSL_KEYSTORE_PASSWORD, config.getProperty(ConfigKey.KAFKA_SSL_KEYSTORE_PASSWORD));
        }

        return properties;
    }
}
