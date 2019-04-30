package com.hhy5861.base.controller;

import com.hhy5861.base.model.Account;
import com.hhy5861.base.service.account.IAccountService;
import com.hhy5861.common.tools.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "/account/redis")
public class AccountRedisController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private RedisTemplate<String, Account> redisTemplate;

    final static String AccountKey = "account_key";

    @GetMapping(value = "/info")
    public Response<Account> info(int id) {
        Account data = redisTemplate.opsForValue().get(AccountKey);

        if (data == null) {
            data = accountService.getAccountById(id);
            redisTemplate.opsForValue().set(AccountKey, data, 60, TimeUnit.SECONDS);
        }

        return new Response<>(data);
    }
}
