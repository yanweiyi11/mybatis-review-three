package com.mybatis.bank.dao;

import com.mybatis.bank.pojo.Account;

/**
 * Package: com.mybatis.bank.dao
 * Date: 2023/8/2 10:26
 * Description: 账户持久层
 */
public interface AccountDao {

    Account selectByActno(String actno);

    int updateByActno(Account account);

}
