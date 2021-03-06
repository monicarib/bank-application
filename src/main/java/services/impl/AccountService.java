package services.impl;

import entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;
import services.IAccountService;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(String accountId) {
        return accountRepository.findById(accountId);
    }


}
