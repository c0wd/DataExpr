package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;
import java.util.UUID;

public class UuidFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "uuid";
    }

    @Override
    public AviatorObject call(Map<String, Object> env) {
        return new AviatorString(UUID.randomUUID().toString());
    }
}
