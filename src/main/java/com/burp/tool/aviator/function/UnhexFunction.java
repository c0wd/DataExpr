package com.burp.tool.aviator.function;

import cn.hutool.core.util.HexUtil;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.Map;

public class UnhexFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "unhex";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        try {
            String hexStr = FunctionUtils.getStringValue(arg1, env);
            return new AviatorRuntimeJavaType(HexUtil.decodeHex(hexStr));
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode hex string", e);
        }
    }
}
