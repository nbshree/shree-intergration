package com.shree.intergration.common.security.messagedigest;

/**
 * MD5消息摘要类
 *
 * @author Darkprayer
 */
public class MD5 extends BaseMessageDigest {
    public MD5() {
        this.setDigestType("MD5");
    }
}
