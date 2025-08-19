package com.burp.tool.aviator;

import com.burp.tool.utils.BurpUtil;
import com.burp.tool.utils.ReflectionUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.runtime.function.AbstractFunction;

import java.util.Map;

public class AviatorEngine {

    private static final AviatorEvaluatorInstance engine;

    static {
        engine = AviatorEvaluator.newInstance();
        // 自动注册
        startAutoRegister();
    }

    public static Map<String, Object> getFuncMap() {

        return engine.getFuncMap();
    }


    public static void startAutoRegister() {
        ReflectionUtil.getSubTypesOf(AviatorEngine.class.getPackage().getName(), AbstractFunction.class)
                .forEach(clazz -> {
                    engine.addFunction(ReflectionUtil.newInstanceWithoutException(clazz));
                });
    }


    public static Object evaluate(String input) throws Exception {
        return evaluate(input, null);
    }

    public static Object evaluate(byte[] input) throws Exception {
        return evaluate(new String(input, BurpUtil.CHARSET), null);
    }

    private static Object evaluate(String expression, Map<String, Object> env) throws Exception {
        Object result = env != null ?
                engine.execute(expression, env) :
                engine.execute(expression);
        return result;
    }

}
