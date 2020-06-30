package com.shree.intergration.common.common;

public class ApiConst {

    public class Wechat {

        /**
         * 微信Oauth认证
         */
        public class OAuth {
            // 微信OAuth的Api地址
            public class Url {
                // 获取服务端访问微信OAuth API的AccessToken(个人)
                public static final String AccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
                // 刷新服务端访问微信OAuth API的AccessToken(个人)
                public static final String RefreshToken = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
                // 获取OAuth用户信息
                public static final String UserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
                // OAuth AccessToken校验
                public static final String TokenValidate = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
                // 微信Oauth登录转跳地址
                public static final String LoginRedirect = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
            }
        }

        /**
         * 微信小程序
         */
        public class MiniProgram {

            public static final String SessionPrefix = "Diyitiyu:Wechat:MiniProgram:SessionKey:";
            public static final String AccessToken = "Diyitiyu:Wechat:MiniProgram:AccessToken";

            // 微信小程序的Api地址
            public class Url {
                // 获取服务端访问微信API的AccessToken
                public static final String ApiToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
                // 通过认证码获取微信小程序登录Session
                public static final String Code2Session = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
            }
        }

        /**
         * 微信公众号
         */
        public class OfficialAccount {
            // 校验失败返回字符串
            public static final String ValidateFailed = "ValidateFailed";
            public static final String ApiError = "ApiError";
            public static final String MessageParseError = "MessageParseError";

            // 微信公众号的Api地址
            public class Url {
                // 获取服务端访问微信API的AccessToken
                public static final String ApiToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
                // 获取用户信息，如果用户没有关注公众号则无法获取
                public static final String UserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
                // 获取用户信息，如果用户没有关注公众号则无法获取
                public static final String UserOpenIdList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";
                public static final String UserOpenIdListWithNextId = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
            }

            // 微信公众号的消息类型
            public class MessageType {
                public static final String Text = "text";
                public static final String Image = "image";
                public static final String Voice = "voice";
                public static final String Video = "video";
                public static final String ShortVideo = "shortvideo";
                public static final String Location = "location";
                public static final String Link = "link";
                public static final String Event = "event";
            }

            // 微信公众号的消息事件类型
            public class MessageEventType {
                // 关注事件
                public static final String Subscribe = "subscribe";
                // 取消关注事件
                public static final String Unsubscribe = "unsubscribe";
                // 扫描带参数二维码事件
                public static final String Scan = "SCAN";
                // 上报地理位置事件
                public static final String Location = "LOCATION";
                // 点击菜单拉取消息时的事件推送
                public static final String Click = "CLICK";
                // 点击菜单跳转链接时的事件推送
                public static final String View = "VIEW";
            }
        }


    }
}
