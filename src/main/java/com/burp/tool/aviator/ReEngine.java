package com.burp.tool.aviator;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.burp.tool.utils.BurpUtil.CHARSET;

public class ReEngine {

    public static final Pattern pattern = Pattern.compile("\\{\\{([^{}]*)}}");

    private static final ReEngine engine = new ReEngine();

    public ReEngine() {
    }

    public static ReEngine getInstance() {
        return engine;
    }

    public Boolean isMatch(String input) throws Exception {
        return ReUtil.contains(pattern, input);
    }


    public String matchAndEvaluate(String input) throws Exception {
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String expression = matcher.group(1);

            Object evaluated = AviatorEngine.evaluate(expression);

            if (evaluated instanceof String) {
                matcher.appendReplacement(result, Matcher.quoteReplacement(evaluated.toString()));
            } else if (evaluated instanceof byte[]) {
                matcher.appendReplacement(result, Matcher.quoteReplacement(new String((byte[]) evaluated,CHARSET)));
            }

        }
        matcher.appendTail(result);

        return result.toString();

    }

    public static void main(String[] args) throws Exception {
    }

}
