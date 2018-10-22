package services.impl;

import entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;
import services.IAccountService;
import services.IWithdrawService;

import java.util.Optional;

@Service
public class WithdrawService implements IWithdrawService {

    @Autowired
    private IAccountService accountService;

    @Override
    public Account withdraw(String accountId, Integer value) {

        Optional<Account> account = accountService.findById(accountId);

        verifyIfAccountExists(account);

        verifyIfBalanceIsEnough(value, account);

        getNewBalance(value, account);

        return accountService.update(account.get());
    }

    private void getNewBalance(Integer value, Optional<Account> account) {
        Integer saldoAnterior = account.get().getSaldo();
        Integer saldoAtual = saldoAnterior - value;

        account.get().setSaldo(saldoAtual);
    }

    private void verifyIfBalanceIsEnough(Integer value, Optional<Account> account) {
        if(value > account.get().getSaldo())
            throw new IllegalArgumentException("Balance insufficient");
    }

    private void verifyIfAccountExists(Optional<Account> account) {
        if(!account.isPresent())
            throw new IllegalArgumentException("Account not found");
    }
}
