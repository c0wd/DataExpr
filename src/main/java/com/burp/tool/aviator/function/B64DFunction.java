package com.burp.tool.aviator.function;

import cn.hutool.core.codec.Base64;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.Map;


public class B64DFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "b64d";
    }



    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String input = FunctionUtils.getStringValue(arg1, env);
        return new AviatorRuntimeJavaType(Base64.decode(input));
    }
}
