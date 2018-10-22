package services;

import entities.Account;

public interface IWithdrawService {
    Account withdraw(String accountId, Integer value);
}
