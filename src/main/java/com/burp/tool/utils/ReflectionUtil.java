package com.burp.tool.utils;

import com.burp.tool.aviator.AviatorEngine;
import com.burp.tool.aviator.anno.AviatorParam;
import com.googlecode.aviator.runtime.function.AbstractFunction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectionUtil {

    public static Set<Class<?>> getTypesAnnotated(String packageName, Class<? extends Annotation> annotationClass){
        org.reflections.Reflections reflections = new org.reflections.Reflections(packageName);
        return reflections.getTypesAnnotatedWith(annotationClass);
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(String packageName, Class<T> clazz){
        org.reflections.Reflections reflections = new org.reflections.Reflections(packageName);

        return reflections.getSubTypesOf(clazz);
    }

    /**
     * 安全创建实例，不抛出异常
     * @param clazz 要实例化的类
     * @param <T> 实例类型
     * @return 新创建的实例，失败时返回null
     */
    public static <T> T newInstanceWithoutException(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }


    public static <T extends Annotation> Map<String, T> getParamAnonByMethod(Method method, Class<T> annotationClass) {
        Parameter[] parameters = method.getParameters();
        Map<String, T> paramMap = new HashMap<>();
        for (Parameter parameter : parameters) {
            Annotation annotation = parameter.getAnnotation(annotationClass);
            if (annotation != null) {
                paramMap.put(parameter.getName(), ((T) annotation));
            }
        }
        return paramMap;
    }

}
