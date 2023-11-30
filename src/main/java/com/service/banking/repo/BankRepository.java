package com.service.banking.repo;

import com.service.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Account, Integer > {
   Account findByAccountNumber(Long accountNumber);
}
