package com.burp.tool.aviator.function;

import cn.hutool.core.codec.Base64;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

public class B64EFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "b64e";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        try {
            Object value = arg1.getValue(env);
            if (value instanceof byte[]) {
                return new AviatorString(Base64.encode((byte[]) value));
            } else {
                return new AviatorString(Base64.encode(FunctionUtils.getStringValue(arg1, env)));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode Base64 string", e);
        }
    }
}
