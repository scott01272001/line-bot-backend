package com.scott.sideproject.linebotbackend.repository;

import com.scott.sideproject.linebotbackend.LineBotBackendApplicationTests;
import com.scott.sideproject.linebotbackend.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountRepositoryTest extends LineBotBackendApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByUsernameTest() {
        List<Account> account = accountRepository.findAll();
        System.out.println(account);
    }

}
