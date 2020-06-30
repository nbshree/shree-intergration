package com.shree.intergration.common.common;

public class ProjectConst {

    public static String IspUidKey = "isport:uid";

    public static long SystemUserId = 10000L;
    public static String SystemUserName = "iSport";

    public static class RecordStatus {
        public static final String Delete = "0";
        public static final String Active = "1";
    }

    public static class IdentifyStatus {
        public static final String Unauthorized = "0";
        public static final String Authorized = "1";
        public static final String Prechecked = "2";
        public static final String CheckFailed = "3";
    }

    public static class OssFileTypes {
        public static final String News = "news";
        public static final String Banner = "banner";
        public static final String Goods = "goods";
        public static final String Certificate = "certificate";
    }
}
