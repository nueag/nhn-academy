package com.nhnacademy.api.domain;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountClientService {

    private final AccountAdaptor accountAdaptor;

    public AccountClientService(AccountAdaptor accountAdaptor) {
        this.accountAdaptor = accountAdaptor;
    }

    public List<Account> getAccounts() {
        return accountAdaptor.getAccounts();
    }

    public Account getAccount(Long id) {
        return accountAdaptor.getAccount(id);
    }

    public Account createAccount(Account account) {
        return accountAdaptor.createAccount(account);
    }

    public void deleteAccount(Long id) {
        accountAdaptor.deleteAccount(id);
    }
}
