package com.shopping.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class ResourceServerConfig {
    //配置资源的ID
    public static final String RESOURCE_ID = "resource";

    @Autowired
    private TokenStore tokenStore;

    //认证授权资源服务配置
    @Configuration
    @EnableResourceServer
    public class AuthenticationAuthorizationServiceConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)//资源ID
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/authentication-authorization/**").permitAll();
        }
    }

    //user-role-permission资源服务配置
    @Configuration
    @EnableResourceServer
    public class UserServiceConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)//资源ID
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/user-role-permission/**").access("#oauth2.hasScope('ALL')");
        }
    }

    //order资源服务配置
    @Configuration
    @EnableResourceServer
    public class OrderServiceConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)//资源ID
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/order/**").access("#oauth2.hasScope('ALL')");
        }
    }

    //provider资源服务配置
    @Configuration
    @EnableResourceServer
    public class ProviderServiceConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)//资源ID
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/provider/**").access("#oauth2.hasScope('ALL')");
        }
    }

    //payment资源服务配置
    @Configuration
    @EnableResourceServer
    public class PaymentServiceConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)//资源ID
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/payment/**").access("#oauth2.hasScope('ALL')");
        }
    }

    //evaluate资源服务配置
    @Configuration
    @EnableResourceServer
    public class EvaluateServiceConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)//资源ID
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/evaluate/**").access("#oauth2.hasScope('ALL')");
        }
    }

}
