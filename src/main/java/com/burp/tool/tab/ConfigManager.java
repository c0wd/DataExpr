package com.burp.tool.tab;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.persistence.Persistence;
import java.io.Serializable;
import java.io.*;

public class ConfigManager {
    private static volatile ConfigManager instance;
    private final Persistence persistence;

    // 私有构造函数
    private ConfigManager(MontoyaApi api) {
        this.persistence = api.persistence();
    }

    /**
     * 获取ConfigManager单例实例
     * @param api Burp MontoyaAPI实例
     * @return ConfigManager单例
     */
    public static void initialize(MontoyaApi api) {
        if (instance == null) {
            instance = new ConfigManager(api);
        }
    }

    // 获取实例方法
    public static ConfigManager getInstance() {
        return instance;
    }

    // 存储字符串配置
    public void saveString(String key, String value) {
        persistence.preferences().setString(key, value);
    }

    // 读取字符串配置（带默认值）
    public String getString(String key, String defaultValue) {
        String value = persistence.preferences().getString(key);
        return value != null ? value : defaultValue;
    }

    // 存储布尔配置
    public void saveBoolean(String key, boolean value) {
        persistence.preferences().setBoolean(key, value);
    }

    // 读取布尔配置（带默认值）
    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean value = persistence.preferences().getBoolean(key);
        return value != null ? value : defaultValue;
    }

    // 删除配置项
    public void removeStr(String key) {
        persistence.preferences().deleteString(key);
    }
}