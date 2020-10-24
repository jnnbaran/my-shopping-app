package com.shoppingapp.demo.auth.conf;

import com.shoppingapp.demo.auth.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JsonEntryPoint jsonEntryPoint;
    private final JwtFilter jwtFilter;

    public SecurityConfig(JsonEntryPoint jsonEntryPoint, JwtFilter jwtFilter) {
        this.jsonEntryPoint = jsonEntryPoint;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().configurationSource(corsConfigurationSource())
            .and().headers().frameOptions().disable()
            .and()
            .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/api/auth/*").permitAll()
                .antMatchers("/assets/*").permitAll()
                .antMatchers("/img/*").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.ico").permitAll()
                .antMatchers("/*.svg").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jsonEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        return source;
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

