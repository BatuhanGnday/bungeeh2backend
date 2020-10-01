package com.bungee.bungeeh2backend.api.security;

import com.bungee.bungeeh2backend.api.security.authentication.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    BungeeAuthenticationEntryPoint entryPoint;
    private UserCredentialService userCredentialService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(BungeeAuthenticationEntryPoint entryPoint,
                             UserCredentialService userCredentialService,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.entryPoint = entryPoint;
        this.userCredentialService = userCredentialService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(entryPoint).and()
                .sessionManagement().sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
