package com.projectflow.projectflowwebsocket.global.security.httpsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class MvcSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final JwtTokenValidator validator;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .formLogin().disable()
                .apply(new JwtConfigure(validator))
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
