package com.nhnacademy.backendserver.controller;

import com.nhnacademy.backendserver.DeleteResponse;
import com.nhnacademy.backendserver.service.Account;
import com.nhnacademy.backendserver.service.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED) //왜 이렇게 해줘야 하지?
    public Account createAccount(@RequestBody Account account) { //request response model 이렇게 따로 만들어서 리턴하는 것이 일반적
        return accountService.createAccount(account);
    }

    @DeleteMapping("/accounts/{id}")
    public DeleteResponse deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return new DeleteResponse("OK");
    }
}
