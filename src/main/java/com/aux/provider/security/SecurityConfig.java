package com.aux.provider.security;

import com.aux.provider.filters.CustomAuthenticationFilter;
import com.aux.provider.filters.CustomAuthorizationFilter;
import com.aux.provider.filters.TokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManagerBean());
        filter.setFilterProcessesUrl("/api/login/**");
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/proveedor/save/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/proveedores/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/perfil/save/**").permitAll();
        http.authorizeRequests().antMatchers(PUT, "/api/perfil/update/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/usuario/save/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/servicios/save/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(filter);
        http.addFilterBefore(new CustomAuthorizationFilter(new TokenFilter()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}

