package com.scott.sideproject.linebotbackend.web.config;

import com.scott.sideproject.linebotbackend.entity.Account;
import com.scott.sideproject.linebotbackend.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AccountRepository accountRepository;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().anyRequest().permitAll();
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        ConcurrentMapCache concurrentMapCache = new ConcurrentMapCache("user-cache");
//        UserDetailsService userDetailsService = new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                Account account = accountRepository.findByUsername(username);
//                return account;
//            }
//        };
//
//        return null;
//    }

}
