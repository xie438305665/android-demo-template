package com.android.library.bridge.util;

/**
 * @author y
 * @create 2018/12/5
 */
public class HtmlUtils {

    public static String getHtml(CharSequence text) {
        return "<!DOCTYPE html>"
                + "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>"
                + "<script src='http://g.tbcdn.cn/mtb/lib-flexible/0.3.2/??flexible_css.js,flexible.js'></script>"
                + "<head><style>*{padding:8;margin:0;}p{display:inline-block;}html{background:#D6DCE4;}</style><meta charset='utf-8'/></head>"
                + "<body>"
                + text
                + "</body>"
                + "</html>";
    }
}
