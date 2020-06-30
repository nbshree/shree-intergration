package com.shree.intergration.common.web.session;

import com.shree.intergration.common.base.singleton.AbstractSingleton;
import lombok.Getter;
import lombok.Setter;

/**
 * UserSession对象管理器
 */
public class UserSessionManager {

    public static final AbstractSingleton<UserSessionManager> HOLDER = new AbstractSingleton<UserSessionManager>() {
        @Override
        protected UserSessionManager newInstance() {
            return new UserSessionManager();
        }
    };

    public static UserSessionManager getInstance() {
        return HOLDER.getInstance();
    }

    public static UserSessionProvider getProviderInstance() {
        return HOLDER.getInstance().getProvider();
    }

    public static <TUserSession extends UserSession> TUserSession getUserSession() {
        return HOLDER.getInstance().getProvider().getUserSession();
    }

    public static <TUserSession extends UserSession> void setUserSession(TUserSession userSession) {
        HOLDER.getInstance().getProvider().setUserSession(userSession);
    }

    private UserSessionManager() {
        this.setProvider();
    }

    @Getter
    protected UserSessionProvider provider;

    @Getter
    @Setter
    protected String providerClassName;

    @Getter
    @Setter
    protected Class<?> providerClass;

    @Getter
    @Setter
    protected boolean debugMode = false;

    public void setProvider() {
        this.providerClassName = "ExpSessionProvider";
        this.setProvider(this.providerClassName);
    }

    public void setProvider(String providerClassName) {
        try {
            Class<?> clazz = Class.forName(providerClassName);
            this.providerClass = clazz;
            this.setProvider(clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setProvider(Class<?> providerClass) {
        try {
            if (classValidate(providerClass)) {
                this.provider = (UserSessionProvider) providerClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean classValidate(Class<?> providerClass) {
        Class<?>[] interfaces = providerClass.getInterfaces();
        for (Class<?> clazz : interfaces) {
            if (UserSessionProvider.class.getSimpleName().equals(clazz.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

}
