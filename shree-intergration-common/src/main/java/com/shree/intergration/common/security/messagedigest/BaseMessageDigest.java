package com.shree.intergration.common.security.messagedigest;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要类
 *
 * @author Darkprayer
 */
public abstract class BaseMessageDigest {

    @Getter
    @Setter
    protected String digestType;

    public byte[] getDigestBytes(byte[] data) {
        byte[] digest;
        try {
            digest = MessageDigest.getInstance(this.getDigestType()).digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            digest = null;
        }
        return digest;
    }

    public byte[] getDigestBytes(String text, Charset charset) {
        return getDigestBytes(text.getBytes(charset));
    }

    public byte[] getDigestBytes(String text) {
        return getDigestBytes(text.getBytes(StandardCharsets.UTF_8));
    }

    public String getDigest(String text, Charset charset, boolean lowerFormat) {
        return this.getDigest(text.getBytes(charset), lowerFormat);
    }

    public String getDigest(String text, boolean lowerFormat) {
        return this.getDigest(text.getBytes(StandardCharsets.UTF_8), lowerFormat);
    }

    public String getDigest(byte[] data, boolean lowerFormat) {
        String result = null;
        byte[] dataHashed = this.getDigestBytes(data);
        if (dataHashed != null) {
            StringBuffer sb = new StringBuffer();
            for (byte b : dataHashed) {
                // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
                sb.append(String.format("%02X", b));
            }
            result = sb.toString();
            if (lowerFormat) result = result.toLowerCase();
        }
        return result;
    }

}
