package com.nhnacademy.api.controller;

import com.nhnacademy.api.DeleteResponse;
import com.nhnacademy.api.domain.Account;
import com.nhnacademy.api.domain.AccountClientService;
import com.nhnacademy.api.domain.AccountResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external-api")
public class AccountController {
    private final AccountClientService accountClientService;

    @GetMapping("/accounts")
    public List<Account> getAccount() {
        return accountClientService.getAccounts();
    }
    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        return accountClientService.getAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED) //왜 이렇게 해줘야 하지?
    public Account createAccount(@RequestBody Account account) { //request response model 이렇게 따로 만들어서 리턴하는 것이 일반적
        return accountClientService.createAccount(account);
    }

    @DeleteMapping("/accounts/{id}")
    public DeleteResponse deleteAccount(@PathVariable("id") Long id) {
        accountClientService.deleteAccount(id);
        return new DeleteResponse("OK");
    }
}
