package com.scott.sideproject.linebotbackend;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@SpringBootTest
public class LineBotBackendApplicationTests {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Test
    public void test() {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("google");
        System.out.println(clientRegistration.getRegistrationId());
        System.out.println(clientRegistration.getProviderDetails().getAuthorizationUri());
        System.out.println(clientRegistration.getAuthorizationGrantType().getValue());
        System.out.println(clientRegistration.getScopes());
    }
}
