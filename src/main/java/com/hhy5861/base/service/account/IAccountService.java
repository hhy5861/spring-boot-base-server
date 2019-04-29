package com.hhy5861.base.service.account;

import com.hhy5861.base.model.Account;

import java.util.List;

public interface IAccountService {
    Account getAccountById(Integer id);

    List<Account> getAllAccount();
}
