package com.nhnacademy.backendserver.service;
import com.nhnacademy.backendserver.repository.AccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultAccountService implements AccountService {
    private final AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        boolean present = accountRepository.findById(account.getId()).isPresent();
        if(present) {
            throw new IllegalStateException("already exist " + account.getId());
        }
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void deleteAccount(Long id) {
        if(!accountRepository.existsById(id)) {
            throw new IllegalStateException("account not found");
        }
        accountRepository.deleteById(id);
    }
}
