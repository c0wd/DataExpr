package com.burp.tool.aviator.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.googlecode.aviator.runtime.type.AviatorBoolean;

import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncodeFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "urlencode";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String text = FunctionUtils.getStringValue(arg1, env);
        boolean encodeAll = FunctionUtils.getBooleanValue(arg2, env);
        try {
            if (encodeAll) {
                return new AviatorString(urlEncodeAll(text));
            } else {
                return new AviatorString(URLEncoder.encode(text, StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to URL encode string", e);
        }
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        return call(env, arg1, AviatorBoolean.FALSE);
    }

    public static String urlEncodeAll(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int ascii = (int) c;
            String hex = Integer.toHexString(ascii); // 转换为十六进制字符串
            result.append("%").append(hex);
        }

        return result.toString();
    }

}
