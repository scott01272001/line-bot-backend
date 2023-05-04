package com.scott.sideproject.linebotbackend.repository;

import com.scott.sideproject.linebotbackend.LineBotBackendApplicationTests;
import com.scott.sideproject.linebotbackend.entity.Account;
import com.scott.sideproject.linebotbackend.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AccountRepositoryTest extends LineBotBackendApplicationTests {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findByUsernameTest() {
        List<Account> account = accountRepository.findAll();
        System.out.println(account);

        Role role = roleRepository.findByName("admin");
        System.out.println("role: " + role);

        Account account1 = Account.builder().username("test").password("test").role(role).build();
        accountRepository.save(account1);

    }

    @Test
    public void test() {
       String code = passwordEncoder.encode("password");
        System.out.println(code);
    }

}
