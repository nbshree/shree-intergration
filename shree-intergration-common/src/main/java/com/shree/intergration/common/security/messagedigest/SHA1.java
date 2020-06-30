package com.shree.intergration.common.security.messagedigest;

/**
 * SHA1消息摘要类
 *
 * @author Darkprayer
 */
public class SHA1 extends BaseMessageDigest {
    public SHA1() {
        this.setDigestType("SHA1");
    }
}
