package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.security.SecureRandom;
import java.util.Map;

public class RandomStrFunction extends AbstractFunction {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public String getName() {
        return "randomStr";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        int count = FunctionUtils.getNumberValue(arg1, env).intValue();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return new AviatorString(sb.toString());
    }
}
