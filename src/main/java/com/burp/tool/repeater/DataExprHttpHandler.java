/*
 * Copyright (c) 2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package com.burp.tool.repeater;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;
import com.burp.tool.utils.BurpUtil;

public class DataExprHttpHandler implements HttpHandler {
    private final Logging logging;

    public DataExprHttpHandler(MontoyaApi api) {
        logging = api.logging();
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
        // 只修改Repeater
        if (httpRequestToBeSent.toolSource().isFromTool(ToolType.REPEATER)) {

            HttpRequest modifyRequest;
            try {
                // httpRequestToBeSent.toString方法直接可以获取到全部的HTTP报文。自己构造有\r\n问题
                String s = BurpUtil.matchAndEvaluate(httpRequestToBeSent);
                modifyRequest = HttpRequest.httpRequest(httpRequestToBeSent.httpService(), ByteArray.byteArray(s));
                if (modifyRequest.hasHeader("Content-Length")) {
                    modifyRequest = modifyRequest.withUpdatedHeader("Content-Length", String.valueOf(modifyRequest.body().length()));
                }

                return RequestToBeSentAction.continueWith(modifyRequest);
            } catch (Exception e) {
                logging.logToError(e);
                throw new RuntimeException("处理HTTP请求时发生错误: " + e.getMessage(), e);
            }

        } else {
            return RequestToBeSentAction.continueWith(httpRequestToBeSent);
        }

    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return ResponseReceivedAction.continueWith(httpResponseReceived);
    }
}