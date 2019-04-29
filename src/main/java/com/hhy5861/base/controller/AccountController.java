package com.hhy5861.base.controller;

import com.hhy5861.base.model.Account;
import com.hhy5861.base.service.account.IAccountService;
import com.hhy5861.common.tools.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping(value = "/list")
    public Response<?> list() {

        return new Response<>(accountService.getAllAccount());
    }

    @GetMapping(value = "/info")
    public Response<Account> info(int id) {
        return new Response<>(accountService.getAccountById(id));
    }
}
