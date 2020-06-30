package com.shree.intergration.common.security.util;


import java.util.Base64;

/**
 * JDK1.8版本后自带Base64便捷工具类
 *
 * @author Darkprayer
 */
public class JDKBase64Utils {

    private static final String UTF_8 = "UTF-8";

    public static byte[] encode(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    public static String encodeToString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encodeToString(String text, String charsetName) throws Exception {
        byte[] bytes = text.getBytes(charsetName);
        return encodeToString(bytes);
    }

    public static String encodeToString(String text) throws Exception {
        return encodeToString(text, UTF_8);
    }

    public static byte[] decode(String base64Str) {
        return Base64.getDecoder().decode(base64Str);
    }

    public static byte[] decode(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    public static String decodeToString(String base64Str, String charsetName) throws Exception {
        return new String(decode(base64Str), charsetName);
    }

    public static String decodeToString(String base64Str) throws Exception {
        return decodeToString(base64Str, UTF_8);
    }

    public static byte[] urlEncode(byte[] bytes) throws Exception {
        return Base64.getUrlEncoder().encode(bytes);
    }

    public static String urlEncodeToString(byte[] bytes) {
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    public static String urlEncodeToString(String text, String charsetName) throws Exception {
        byte[] bytes = text.getBytes(charsetName);
        return urlEncodeToString(bytes);
    }

    public static String urlEncodeToString(String text) throws Exception {
        return urlEncodeToString(text, UTF_8);
    }

    public static byte[] urlDecode(String base64Str) {
        return Base64.getUrlDecoder().decode(base64Str);
    }

    public static byte[] urlDecode(byte[] bytes) {
        return Base64.getUrlDecoder().decode(bytes);
    }

    public static String urlDecodeToString(String base64Str, String charsetName) throws Exception {
        return new String(urlDecode(base64Str), charsetName);
    }

    public static String urlDecodeToString(String base64Str) throws Exception {
        return urlDecodeToString(base64Str, UTF_8);
    }
}
