package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "file";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String filePath = FunctionUtils.getStringValue(arg1, env);
        try {
            byte[] content = Files.readAllBytes(Paths.get(filePath));
            return new AviatorRuntimeJavaType(content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }
}
