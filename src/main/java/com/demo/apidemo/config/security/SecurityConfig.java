package com.demo.apidemo.config.security;

import com.demo.apidemo.auth.jwt.JWTTokenAuthenticationFilter;
import com.demo.apidemo.auth.jwt.JWTTokenProvider;
import com.demo.apidemo.auth.provider.DemoAuthenticationProvider;
import com.demo.apidemo.service.user.DemoUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DemoUserDetailsService demoUserDetailsService;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @Autowired
    DemoAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**","/console/**", "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .headers()
                .frameOptions().disable()
                .and()
                .addFilterBefore(new JWTTokenAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    JWTTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
        JWTTokenAuthenticationFilter jwtFilter = new JWTTokenAuthenticationFilter(jwtTokenProvider);
        return jwtFilter;
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers(
//                        "/img/favicon.png",
//                        "/**/favicon.*",
//                        "/css/**",
//                        "/img/**",
//                        "/scripts/**",
//                        "/vendor/**",
//                        "/vendor/**/*",
//                        "/built/**/*"
//                        //"/**/*.*"
//                );
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return demoUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
