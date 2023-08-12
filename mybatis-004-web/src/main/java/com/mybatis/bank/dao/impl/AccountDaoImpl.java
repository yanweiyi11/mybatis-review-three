package com.mybatis.bank.dao.impl;

import com.mybatis.bank.dao.AccountDao;
import com.mybatis.bank.pojo.Account;
import com.mybatis.bank.service.AccountService;
import com.mybatis.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Package: com.mybatis.bank.dao.impl
 * Date: 2023/8/2 10:27
 * Description: null
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return (Account) sqlSession.selectOne("account.selectByActno", actno);
    }

    @Override
    public int updateByActno(Account account) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.update("account.updateByActno", account);
    }
}
