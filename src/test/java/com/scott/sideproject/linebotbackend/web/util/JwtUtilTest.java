package com.scott.sideproject.linebotbackend.web.util;

import com.scott.sideproject.linebotbackend.LineBotBackendApplicationTests;
import com.scott.sideproject.linebotbackend.entity.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtUtilTest extends LineBotBackendApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void genTokenTest() {
        UserDetails userDetails = Account.builder().username("test").build();
        String token = jwtUtil.generateToken(userDetails);
        System.out.println(token);
    }

}
