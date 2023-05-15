package com.scott.sideproject.linebotbackend.web.controller;

import com.scott.sideproject.linebotbackend.entity.Account;
import com.scott.sideproject.linebotbackend.exception.ResourceNotFound;
import com.scott.sideproject.linebotbackend.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/accounts")
@Log4j2
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping()
    public List<Account> getAccounts() {
        log.debug("hi");
        return accountRepository.findAll();
    }

    @GetMapping("/{username}")
    public Account getAccountByUsername(@PathVariable(name = "username") String username) throws ResourceNotFound {
        return accountRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFound("account not found"));
    }


}
