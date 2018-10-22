package services;

import entities.Account;

public interface IWithdrawService {
    public Account withdraw(String accountId, Integer value);
}
