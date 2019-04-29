package com.hhy5861.base.service.account.impl;

import com.hhy5861.base.mapper.AccountMapper;
import com.hhy5861.base.model.Account;
import com.hhy5861.base.service.account.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements IAccountService, Serializable {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccountById(Integer id) {
        Account account = accountMapper.selectByPrimaryKey(id);

        log.info("service: {}, account: {}", id, account);

        return account;
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> account = accountMapper.selectAll();

        log.info("service: {}", account);
        return account;
    }
}
