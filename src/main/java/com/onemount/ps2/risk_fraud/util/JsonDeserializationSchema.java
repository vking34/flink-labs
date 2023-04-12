package com.onemount.ps2.risk_fraud.util;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDeserializationSchema<T> implements DeserializationSchema<T> {
    private static final Logger log = LoggerFactory.getLogger(JsonDeserializationSchema.class);
    private Class<T> clazz;

    public JsonDeserializationSchema(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(byte[] message) {
        try {
            return Json.deserialize(message, clazz);
        } catch (Exception e) {
            log.error("ERROR when json deserialize message: " + new String(message), e);
            return null;
        }
    }

    @Override
    public boolean isEndOfStream(T nextElement) {
        return false;
    }

    @Override
    public TypeInformation<T> getProducedType() {
        return TypeInformation.of(clazz);
    }
}
