package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.dao.AccountMapper;
import org.csu.mypetstore.dao.LogMapper;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Record;
import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    LogMapper logMapper;
    @Override
    public Account getAccount(String username) {
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.getAccountByUsernameAndPassword(account);
    }

    @Override
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        accountMapper.insertSignon(account);
        accountMapper.insertProfile(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    @Override
    public void addRecord(String username,String record) {
        logMapper.addRecord(new Record(username,record));
    }

    @Override
    public List<Record> searchRecord(String username) {
        return logMapper.searchRecord(username);
    }
}
