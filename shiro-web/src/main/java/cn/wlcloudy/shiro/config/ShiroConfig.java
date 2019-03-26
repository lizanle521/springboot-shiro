package cn.wlcloudy.shiro.config;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description shiro配置类
 * @Author wangxiong
 * @Date 2018/10/25 18:26
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {
    @Value("{server.servlet.session.timeout}")
    private int tomcatTimeout;

    /**
     * 配置拦截器
     * <p>
     * 定义拦截URL权限，优先级从上到下
     * * 1). anon  : 匿名访问，无需登录
     * * 2). authc : 登录后才能访问
     * * 3). logout: 登出
     * * 4). roles : 角色过滤器
     * *
     * * URL 匹配风格
     * * 1). ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/；
     * * 2). *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1；
     * 2). **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
     * <p>
     * 配置身份验证成功，失败的跳转路径
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 静态资源匿名访问
        filterChainDefinitionMap.put("/static/**", "anon");
        // Swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        // 登录匿名访问
        filterChainDefinitionMap.put("/api/v1/login", "anon");
//		filterChainDefinitionMap.put("/api/v1/user/logout", "logout");	// 用户退出，只需配置logout即可实现该功能
        // 其他路径均需要身份认证，一般位于最下面，优先级最低
        filterChainDefinitionMap.put("/api/v1/guest/**", "anon");
        filterChainDefinitionMap.put("/api/v1/admin/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		shiroFilterFactoryBean.setLoginUrl("/login");		// 登录的路径
//		shiroFilterFactoryBean.setSuccessUrl("/api/v1/dashboard");	// 登录成功后跳转的路径
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403");	// 验证失败后跳转的路径
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        JsonFilter jsonFilter = new JsonFilter();
        MyAuthorizationFilter authorizationFilter = new MyAuthorizationFilter();
        filters.put("login", jsonFilter);
        filters.put("authorize",authorizationFilter);
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动创建代理类，若不添加，Shiro的注解可能不会生效。
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(EhCacheManager ehCacheManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager(ehCacheManager));
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置加密匹配，使用MD5的方式，进行2次加密
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        MyHashedCredentialsMatcher myHashedCredentialsMatcher = new MyHashedCredentialsMatcher();
        myHashedCredentialsMatcher.setHashAlgorithmName("MD5");
        myHashedCredentialsMatcher.setHashIterations(2);
        return myHashedCredentialsMatcher;
    }

    /**
     * 自定义Realm，可以多个
     */
    @Bean
    public ShiroRealm baicMotorShiroRealm() {
        ShiroRealm baicMotorShiroRealm = new ShiroRealm();
        baicMotorShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return baicMotorShiroRealm;
    }

    /**
     * ehcache缓存管理器
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        //将ehcacheManager转换成shiro包装后的ehcacheManager对象
        em.setCacheManager(CacheManager.getCacheManager("shiroCache"));
        return em;
    }

    /**
     * SecurityManager 安全管理器；Shiro的核心
     */
    @Bean
    public DefaultWebSecurityManager securityManager(EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(baicMotorShiroRealm());
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setSessionManager(sessionManager(ehCacheManager));
        return securityManager;
    }

    /**
     * sessionDAO
     * @return
     */
    @Bean
    public SessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    /**
     * cookie信息
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    /**
     * session管理器
     * @param ehCacheManager
     * @return
     */
    @Bean
    public SessionManager sessionManager(EhCacheManager ehCacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO());

        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);

        sessionManager.setSessionIdCookie(simpleCookie());
        sessionManager.setCacheManager(ehCacheManager);
        return sessionManager;
    }
}
