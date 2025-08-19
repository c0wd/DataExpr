package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorNumber;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "randomInt";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        int min = FunctionUtils.getNumberValue(arg1, env).intValue();
        int max = FunctionUtils.getNumberValue(arg2, env).intValue();
        return AviatorNumber.valueOf(ThreadLocalRandom.current().nextInt(min, max + 1));
    }
}
