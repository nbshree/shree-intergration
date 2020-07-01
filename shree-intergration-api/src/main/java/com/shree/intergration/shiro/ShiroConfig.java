package com.shree.intergration.shiro;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@Configuration
public class ShiroConfig {

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // 开启Shiro AOP
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        log.info("开始初始化Shiro Filter Factory");
        // 设定Shiro拦截器工厂，指定SecurityManager
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义Ajax拦截器
//        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//        filtersMap.put("ajaxAccess", new AjaxAccessControlFilter());
//        shiroFilterFactoryBean.setFilters(filtersMap);

        // 配置公开资源拦截不过滤 顺序判断
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //静态资源访问
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 登录成功后转跳界面
        shiroFilterFactoryBean.setSuccessUrl("redirect:/");
        // 因为特殊设定了ExceptionHandler此处的转跳都无需设定
        // 设定登录转跳界面
        //shiroFilterFactoryBean.setLoginUrl("redirect:/login");
        // 设定未授权转跳界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("redirect:/error/403");

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1000 * 60 * 60 * 24);// 会话过期时间，单位：毫秒(在无操作时开始计时)--->一分钟,用于测试
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        return defaultWebSessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("ismShiroRealm") ShiroRealm shiroRealm, @Qualifier("ehCacheManager") EhCacheManager ehCacheManager) {
        log.info("开始初始化Shiro Security Manager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义realm
        securityManager.setRealm(shiroRealm);
        // 配置记住我
        //securityManager.setRememberMeManager(rememberMeManager());
        // 配置ehcache
        securityManager.setCacheManager(ehCacheManager);
        // 配置自定义session管理，使用redis 参考博客：
        securityManager.setSessionManager(getDefaultWebSessionManager());
        //securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        log.info("开始初始化Shiro EhCache Manager");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    @Bean
    public ShiroRealm ismShiroRealm() {
        log.info("开始初始化Sport Manager Shiro Realm");
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCachingEnabled(true);
        // 启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        shiroRealm.setAuthenticationCachingEnabled(true);
        // 设置缓存AuthenticationInfo信息的名称 在ehcache-shiro.xml中有对应缓存的配置
        shiroRealm.setAuthenticationCacheName("authenticationCache");
        // 启用授权缓存，即缓存AuthorizationInfo信息，默认false
        shiroRealm.setAuthorizationCachingEnabled(true);
        // 设置缓存AuthorizationInfo信息的名称  在ehcache-shiro.xml中有对应缓存的配置
        shiroRealm.setAuthorizationCacheName("authorizationCache");
        return shiroRealm;
    }

//    @Bean
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//        Properties properties = new Properties();
//        properties.setProperty("AuthorizationException", "redirect:/login");
//        resolver.setExceptionMappings(properties);
//        return resolver;
//    }
}
