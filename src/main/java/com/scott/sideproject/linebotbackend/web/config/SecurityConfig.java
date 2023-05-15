package com.scott.sideproject.linebotbackend.web.config;

import com.scott.sideproject.linebotbackend.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AccountRepository accountRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic()
                .and().passwordManagement(Customizer.withDefaults());

        http.cors().disable().csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        ConcurrentMapCache concurrentMapCache = new ConcurrentMapCache("user-cache");
        SpringCacheBasedUserCache springCacheBasedUserCache = new SpringCacheBasedUserCache(concurrentMapCache);

        UserDetailsService userDetailsService = username -> accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found.", username)));

        CachingUserDetailsService cachingUserDetailsService = new CachingUserDetailsService(userDetailsService);
        cachingUserDetailsService.setUserCache(springCacheBasedUserCache);

        return cachingUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
