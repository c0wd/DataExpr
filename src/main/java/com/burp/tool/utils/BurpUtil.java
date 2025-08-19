package com.burp.tool.utils;

import burp.api.montoya.http.message.requests.HttpRequest;
import com.burp.tool.aviator.ReEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BurpUtil {

    public static final Charset CHARSET = StandardCharsets.ISO_8859_1;

    public static byte[] getFullHeader(HttpRequest httpRequest) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        httpRequest.headers().forEach(header -> {
            String headerLine = header.name() + ": " + header.value() + "\r\n";
            try {
                outputStream.write(headerLine.getBytes(BurpUtil.CHARSET));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        outputStream.close();
        return outputStream.toByteArray();

    }

    public static String matchAndEvaluate(HttpRequest requestResponse) throws Exception {
        return ReEngine.getInstance().matchAndEvaluate(new String(requestResponse.toByteArray().getBytes(),CHARSET));
    }


    public static Boolean isMatch(HttpRequest requestResponse) throws Exception {
        return ReEngine.getInstance().isMatch(new String(requestResponse.toByteArray().getBytes(),CHARSET));
    }


}
