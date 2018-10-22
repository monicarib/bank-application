package services;

import entities.Account;

import java.util.Optional;

public interface IAccountService {
    Account update(Account account);

    Optional<Account> findById(String accountId);
}
