package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class GzipFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "gzip";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        try {
            byte[] input;
            if (arg1.getValue(env) instanceof byte[]) {
                input = (byte[]) arg1.getValue(env);
            } else {
                input = FunctionUtils.getStringValue(arg1, env).getBytes();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(input);
            }
            return new AviatorRuntimeJavaType(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to compress data with GZIP", e);
        }
    }
}
