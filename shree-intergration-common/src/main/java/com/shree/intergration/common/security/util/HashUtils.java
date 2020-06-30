package com.shree.intergration.common.security.util;

import com.shree.intergration.common.base.singleton.Singleton;
import com.shree.intergration.common.security.messagedigest.MD5;
import com.shree.intergration.common.security.messagedigest.SHA1;

import java.nio.charset.Charset;

/**
 * 消息摘要便捷工具类
 *
 * @author Darkprayer
 */
public class HashUtils {

    /**
     * MD5 便捷方法
     **/
    public static String getMD5(String text, Charset charset, boolean lowerFormat) {
        return Singleton.getInstance(MD5.class).getDigest(text, charset, lowerFormat);
    }

    public static String getMD5(String text) {
        return Singleton.getInstance(MD5.class).getDigest(text, false);
    }

    public static String getMD5(byte[] dataBytes, boolean lowerFormat) {
        return Singleton.getInstance(MD5.class).getDigest(dataBytes, lowerFormat);
    }

    public static String getMD5(byte[] dataBytes) {
        return Singleton.getInstance(MD5.class).getDigest(dataBytes, false);
    }

    public static byte[] getMD5Bytes(String text, Charset charset) {
        return Singleton.getInstance(MD5.class).getDigestBytes(text, charset);
    }

    public static byte[] getMD5Bytes(String text) {
        return Singleton.getInstance(MD5.class).getDigestBytes(text);
    }

    public static byte[] getMD5Bytes(byte[] dataBytes) {
        return Singleton.getInstance(MD5.class).getDigestBytes(dataBytes);
    }

    /**
     * SHA1 便捷方法
     **/
    public static String getSHA1(String text, Charset charset, boolean lowerFormat) {
        return Singleton.getInstance(SHA1.class).getDigest(text, charset, lowerFormat);
    }

    public static String getSHA1(String text) {
        return Singleton.getInstance(SHA1.class).getDigest(text, false);
    }

    public static String getSHA1(byte[] dataBytes, boolean lowerFormat) {
        return Singleton.getInstance(SHA1.class).getDigest(dataBytes, lowerFormat);
    }

    public static String getSHA1(byte[] dataBytes) {
        return Singleton.getInstance(SHA1.class).getDigest(dataBytes, false);
    }

    public static byte[] getSHA1Bytes(String text, Charset charset) {
        return Singleton.getInstance(SHA1.class).getDigestBytes(text, charset);
    }

    public static byte[] getSHA1Bytes(String text) {
        return Singleton.getInstance(SHA1.class).getDigestBytes(text);
    }

    public static byte[] getSHA1Bytes(byte[] dataBytes) {
        return Singleton.getInstance(SHA1.class).getDigestBytes(dataBytes);
    }
}
