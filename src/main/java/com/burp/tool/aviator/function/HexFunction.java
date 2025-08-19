package com.burp.tool.aviator.function;

import cn.hutool.core.util.HexUtil;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

public class HexFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "hex";
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
            return new AviatorString(HexUtil.encodeHexStr(input).toLowerCase());
        } catch (Exception e) {
            throw new RuntimeException("HEX编码失败", e);
        }
    }
}
