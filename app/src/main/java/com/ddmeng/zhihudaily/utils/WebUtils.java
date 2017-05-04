package com.ddmeng.zhihudaily.utils;

import java.util.List;
import java.util.Locale;

/**
 * Thanks to information from :
 * https://github.com/hefuyicoder/ZhihuDaily/blob/bf727d6b3cb1e8c197755463c27f39fb577d44e6/app/src/main/java/io/hefuyi/zhihudaily/util/WebUtils.java
 */
public class WebUtils {

    public static final String MIME_TYPE = "text/html; charset=utf-8";
    public static final String ENCODING = "UTF-8";

    private static final String CSS_LINK_FORMAT = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\">";
    private static final String TOP_IMAGE_PLACEHOLDER_CLASS = "class=\"img-place-holder\"";

    public static String buildHtmlWithCss(String htmlString, List<String> cssUrls) {
        StringBuilder stringBuilder = new StringBuilder();
        if (cssUrls != null) {
            stringBuilder.append("<head>");
            for (String cssUrl : cssUrls) {
                stringBuilder.append(String.format(Locale.US, CSS_LINK_FORMAT, cssUrl));
            }
            stringBuilder.append("</head>");
        }
        stringBuilder.append("<body>");
        htmlString = htmlString.replace(TOP_IMAGE_PLACEHOLDER_CLASS, "");
        stringBuilder.append(htmlString);
        stringBuilder.append("</body>");
        return stringBuilder.toString();
    }
}
