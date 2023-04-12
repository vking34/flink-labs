package com.onemount.ps2.risk_fraud.util;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Json {
    private static final DslJson<Object> dslJson = new DslJson<>(Settings.withRuntime().includeServiceLoader());

    /**
     * Decode a given JSON buffer to a POJO of the given class type.
     *
     * @param buf   the JSON buffer.
     * @param clazz the class to map to.
     * @param <T>   the generic type.
     * @return an instance of T
     * @throws IOException when there is a parsing or invalid mapping.
     */
    public static <T> T deserialize(byte[] buf, Class<T> clazz) throws IOException {
        return dslJson.deserialize(clazz, buf, buf.length);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serialize(obj, out);
        return out.toByteArray();
    }

    public static void serialize(Object obj, OutputStream outputStream) throws IOException {
        dslJson.serialize(obj, outputStream);
    }
}
