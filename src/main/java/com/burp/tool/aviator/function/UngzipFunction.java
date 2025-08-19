package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class UngzipFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "ungzip";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        try {
            byte[] input;
            if (arg1.getValue(env) instanceof byte[]) {
                input = (byte[]) arg1.getValue(env);
            } else {
                throw new RuntimeException("Input must be a byte array");
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
            try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
                return new AviatorRuntimeJavaType(gzipInputStream.readAllBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to decompress GZIP data", e);
        }
    }
}
