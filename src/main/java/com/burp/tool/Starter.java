/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package com.burp.tool;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.extension.Extension;
import burp.api.montoya.extension.ExtensionUnloadingHandler;
import burp.api.montoya.http.Http;
import burp.api.montoya.logging.Logging;
import com.burp.tool.repeater.DataExprHttpHandler;
import com.burp.tool.repeater.DataExprRequestEditorProvider;
import com.burp.tool.tab.ConfigManager;


//Burp will auto-detect and load any class that extends BurpExtension.
public class Starter implements BurpExtension {
    private Logging logging;

    @Override
    public void initialize(MontoyaApi api) {
        logging = api.logging();

        Http http = api.http();
        Extension extension = api.extension();

        // set extension name
        extension.setName("DataExpr");
        // 初始化单例ConfigManager
        ConfigManager.initialize(api);

        // register a new HTTP handler
        http.registerHttpHandler(new DataExprHttpHandler(api));

        // 注册表达式编辑器提供者。
        api.userInterface().registerHttpRequestEditorProvider(new DataExprRequestEditorProvider(api));


        // register a new extension unload handler
        extension.registerUnloadingHandler(new MyExtensionUnloadHandler());

        logging.logToOutput("https://github.com/c0wd/DataExpr");
    }

    private class MyExtensionUnloadHandler implements ExtensionUnloadingHandler {
        @Override
        public void extensionUnloaded() {
            logging.logToOutput("Extension was unloaded.");
        }
    }
}