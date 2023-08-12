package com.mybatis.bank.service;

import com.mybatis.bank.exceptions.MoneyNotEnoughException;
import com.mybatis.bank.exceptions.TransferException;

/**
 * Package: com.mybatis.bank.service
 * Date: 2023/8/2 10:18
 * Description: 账户业务类
 */
public interface AccountService {

    /**
     * 账户转账业务
     *
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转账金额
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;

}
