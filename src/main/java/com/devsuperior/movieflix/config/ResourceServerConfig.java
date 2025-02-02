package com.devsuperior.movieflix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    final JwtTokenStore tokenStore;
    final Environment env;

    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
    private static final String[] VISITOR_OR_MEMBER = {"/movies", "/genres/**"};
    private static final String[] MEMBER = {"/reviews"};

    public ResourceServerConfig(JwtTokenStore tokenStore, Environment env) {
        this.tokenStore = tokenStore;
        this.env = env;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //H2
        if (Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        };

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, VISITOR_OR_MEMBER).hasAnyRole("VISITOR", "MEMBER")
                .antMatchers(MEMBER).hasRole("MEMBER")
                .anyRequest().authenticated();
    }
}
