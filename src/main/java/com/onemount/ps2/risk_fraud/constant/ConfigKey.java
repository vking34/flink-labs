package com.onemount.ps2.risk_fraud.constant;

public class ConfigKey {
    public static final String STATE_BACKEND_ROCKSDB_LOCALDIR = "state.backend.rocksdb.localdir";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "kafka.bootstrap-servers";
    public static final String KAFKA_SSL_ENABLED = "kafka.ssl.enabled";
    public static final String KAFKA_SSL_TRUSTSTORE_LOCATION = "kafka.ssl.truststore.location";
    public static final String KAFKA_SSL_TRUSTSTORE_PASSWORD = "kafka.ssl.truststore.password";
    public static final String KAFKA_SSL_KEYSTORE_LOCATION = "kafka.ssl.keystore.location";
    public static final String KAFKA_SSL_KEYSTORE_PASSWORD = "kafka.ssl.keystore.password";
    public static final String KAFKA_TRANSACTION_TOPIC = "kafka.topics.transaction.name";
    public static final String KAFKA_TRANSACTION_GROUP_ID = "kafka.topics.transaction.group-id";
    public static final String KAFKA_ACCOUNT_TOPIC = "kafka.topics.account.name";
    public static final String KAFKA_ACCOUNT_GROUP_ID = "kafka.topics.account.group-id";
}
