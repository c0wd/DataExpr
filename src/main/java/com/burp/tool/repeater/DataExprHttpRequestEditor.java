package com.burp.tool.repeater;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.ui.editor.EditorOptions;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.RawEditor;
import burp.api.montoya.ui.editor.extension.EditorCreationContext;
import burp.api.montoya.ui.editor.extension.EditorMode;
import burp.api.montoya.ui.editor.extension.ExtensionProvidedHttpRequestEditor;
import com.burp.tool.aviator.ReEngine;
import com.burp.tool.utils.BurpUtil;

import java.awt.*;

import static burp.api.montoya.core.ByteArray.byteArray;

public class DataExprHttpRequestEditor implements ExtensionProvidedHttpRequestEditor {
    private final HttpRequestEditor previewEditor;
    private final MontoyaApi api;


    DataExprHttpRequestEditor(MontoyaApi api, EditorCreationContext creationContext) {
        this.api = api;
        previewEditor = api.userInterface().createHttpRequestEditor(EditorOptions.READ_ONLY);
        // 只能通过这种方式进行高亮。
        previewEditor.setSearchExpression(ReEngine.pattern.pattern());
    }

    @Override
    public HttpRequest getRequest() {
        api.logging().logToError("调用getRequest方法");
        throw new UnsupportedOperationException("此编辑器不支持获取请求内容");
    }

    // preview选项卡显示表达式处理后的内容
    @Override
    public void setRequestResponse(HttpRequestResponse requestResponse) {
        String httprequest;
        try {
            httprequest = BurpUtil.matchAndEvaluate(requestResponse.request());
        } catch (Exception e) {
            api.logging().logToError(e);
            httprequest = e.getMessage();
        }
        HttpRequest modifyRequest = HttpRequest.httpRequest(requestResponse.request().httpService(), byteArray(httprequest));
        if (modifyRequest.hasHeader("Content-Length")) {
            modifyRequest = modifyRequest.withUpdatedHeader("Content-Length", String.valueOf(modifyRequest.body().length()));
        }

        this.previewEditor.setRequest(modifyRequest);
    }

    @Override
    public boolean isEnabledFor(HttpRequestResponse requestResponse) {
        try {
            return BurpUtil.isMatch(requestResponse.request());
        } catch (Exception e) {
            api.logging().logToError(e);
        }

        return false;
    }

    @Override
    public String caption() {
        return "preview";
    }

    @Override
    public Component uiComponent() {
        return previewEditor.uiComponent();
    }

    @Override
    public Selection selectedData() {
        return previewEditor.selection().isPresent() ? previewEditor.selection().get() : null;
    }

    @Override
    public boolean isModified() {
        return previewEditor.isModified();
    }
}