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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
        //Peticiones permitidas para todos

        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers(POST,"api/proveedor/save").permitAll();
        http.authorizeRequests().antMatchers(POST,"/email/send").permitAll();
        http.authorizeRequests().antMatchers(GET,"api/proveedores").permitAll();
        http.authorizeRequests().antMatchers(GET,"api/servicios").permitAll();
        http.authorizeRequests().antMatchers(GET,"api/servicio/**").permitAll();

        //Peticiones permitidas para usuarios Registrados
        http.authorizeRequests().antMatchers(POST, "/api/perfil/save/**").hasAuthority("ROLE_USUARIO");
        http.authorizeRequests().antMatchers(PUT, "/api/perfil/update/**").hasAuthority("ROLE_USUARIO");
        http.authorizeRequests().antMatchers(POST, "/api/usuario/save/**").hasAuthority("ROLE_USUARIO");
        http.authorizeRequests().antMatchers(POST, "/api/servicios/save/**").hasAuthority("ROLE_USUARIO");
        http.authorizeRequests().antMatchers(POST, "/api/servicios/update/**").hasAuthority("ROLE_USUARIO");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(filter);
        http.addFilterBefore(new CustomAuthorizationFilter(new TokenFilter()), UsernamePasswordAuthenticationFilter.class);

        //Configuracion de los cors
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:80", "*"));
            cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        });

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


}

