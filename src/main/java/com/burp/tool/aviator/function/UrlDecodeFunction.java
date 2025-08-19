package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UrlDecodeFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "urldecode";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String text = FunctionUtils.getStringValue(arg1, env);
        try {
            return new AviatorString(URLDecoder.decode(text, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to URL decode string", e);
        }
    }
}
