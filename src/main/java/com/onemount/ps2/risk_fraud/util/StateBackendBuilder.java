package com.onemount.ps2.risk_fraud.util;


import com.onemount.ps2.risk_fraud.constant.ConfigKey;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;

import java.util.Properties;

public class StateBackendBuilder {

    public static RocksDBStateBackend buildRocksDBStateBackend() throws Exception {
        Properties config = ConfigUtil.getProperties();
        return new RocksDBStateBackend(config.getProperty(ConfigKey.STATE_BACKEND_ROCKSDB_LOCALDIR));
    }
}
