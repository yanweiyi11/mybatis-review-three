package com.mybatis.bank.service.impl;

import com.mybatis.bank.dao.AccountDao;
import com.mybatis.bank.dao.impl.AccountDaoImpl;
import com.mybatis.bank.exceptions.MoneyNotEnoughException;
import com.mybatis.bank.exceptions.TransferException;
import com.mybatis.bank.pojo.Account;
import com.mybatis.bank.service.AccountService;
import com.mybatis.bank.utils.GenerateDaoProxy;
import com.mybatis.bank.utils.SqlSessionUtil;
import com.mybatis.bank.web.AccountServlet;
import org.apache.ibatis.session.SqlSession;

/**
 * Package: com.mybatis.bank.service.impl
 * Date: 2023/8/2 10:18
 * Description: null
 */
public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = new AccountDaoImpl();
    // private AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);

    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);


    /**
     * 账户转账业务
     *
     * @param fromActno 转出账号
     * @param toActno   转入账号
     * @param money     转账金额
     */
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            throw new MoneyNotEnoughException("对不起，余额不足");
        }
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        int count = accountDao.updateByActno(fromAct);
        count += accountDao.updateByActno(toAct);

        if (count != 2){
            throw new TransferException("转账异常，未知原因");
        }

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
