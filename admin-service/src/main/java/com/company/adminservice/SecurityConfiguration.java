package com.company.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(datasource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);

    }

    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers("/loggedin").authenticated()
                .mvcMatchers(HttpMethod.GET, "/**").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_TEAMLEAD", "ROLE_MANAGER", "ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/inventories").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_TEAMLEAD", "ROLE_MANAGER", "ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/**").hasAnyAuthority("ROLE_TEAMLEAD", "ROLE_MANAGER", "ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/customers").hasAnyAuthority("ROLE_TEAMLEAD", "ROLE_MANAGER", "ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority("ROLE_ADMIN");

        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

}