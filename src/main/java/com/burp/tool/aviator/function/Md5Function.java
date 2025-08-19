package com.burp.tool.aviator.function;

import cn.hutool.core.annotation.Alias;
import com.burp.tool.aviator.anno.AviatorParam;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;


public class Md5Function extends AbstractFunction {
    @Override
    public String getName() {
        return "md5";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject str) {
        Object value = str.getValue(env);
        String input = value != null ? value.toString() : "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return new AviatorString(sb.toString());
        } catch (Exception e) {
            throw new RuntimeException("MD5 calculation failed", e);
        }
    }
}