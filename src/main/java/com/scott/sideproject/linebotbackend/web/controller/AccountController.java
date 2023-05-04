package com.scott.sideproject.linebotbackend.web.controller;

import com.scott.sideproject.linebotbackend.entity.Account;
import com.scott.sideproject.linebotbackend.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping()
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }


}
