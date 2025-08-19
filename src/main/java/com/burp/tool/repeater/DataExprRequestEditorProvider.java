package com.burp.tool.repeater;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.ui.editor.extension.EditorCreationContext;
import burp.api.montoya.ui.editor.extension.ExtensionProvidedHttpRequestEditor;
import burp.api.montoya.ui.editor.extension.HttpRequestEditorProvider;

public class DataExprRequestEditorProvider implements HttpRequestEditorProvider
{
    private final MontoyaApi api;

    public DataExprRequestEditorProvider(MontoyaApi api)
    {
        this.api = api;
    }

    @Override
    public ExtensionProvidedHttpRequestEditor provideHttpRequestEditor(EditorCreationContext creationContext)
    {
        return new DataExprHttpRequestEditor(api, creationContext);
    }
}