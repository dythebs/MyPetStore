package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    public Account getAccount(String username) ;

    public Account getAccount(String username, String password) ;
    @Transactional
    public void insertAccount(Account account) ;

    @Transactional
    public void updateAccount(Account account) ;

    public void addRecord(String username,String record);
}
