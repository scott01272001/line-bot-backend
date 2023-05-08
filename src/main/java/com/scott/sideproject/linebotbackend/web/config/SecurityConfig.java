package com.scott.sideproject.linebotbackend.web.config;

import com.scott.sideproject.linebotbackend.entity.Account;
import com.scott.sideproject.linebotbackend.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AccountRepository accountRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.oauth2Client().clientRegistrationRepository(clientRegistrationRepository());
       // http.oauth2Login();

        http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic()
            .and().passwordManagement(Customizer.withDefaults());

        http.cors().disable().csrf().disable();

//        http.addFilterBefore(new OAuth2AuthorizationRequestRedirectFilter(clientRegistrationRepository()),
//                UsernamePasswordAuthenticationFilter.class);

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


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration client = ClientRegistrations
                .fromOidcIssuerLocation("https://accounts.google.com")
                .clientId("414401882069-mg039gt48slvbgn0migcf366itht8d62.apps.googleusercontent.com")
                .clientSecret("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")
                .redirectUri("http://localhost")
                .scope("openid", "profile", "email")
                .registrationId("google")
                .build();

        ClientRegistrationRepository clientRegistrationRepository = new InMemoryClientRegistrationRepository(client);
        return clientRegistrationRepository;
    }

}
