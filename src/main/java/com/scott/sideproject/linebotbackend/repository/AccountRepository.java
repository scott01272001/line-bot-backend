package com.scott.sideproject.linebotbackend.repository;

import com.scott.sideproject.linebotbackend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    public Optional<Account> findByUsername(String username);

}
