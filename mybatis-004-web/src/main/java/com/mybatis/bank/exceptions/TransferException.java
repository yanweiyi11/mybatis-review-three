package com.mybatis.bank.exceptions;

/**
 * Package: com.mybatis.bank.exceptions
 * Date: 2023/8/2 11:06
 * Description: 转账异常
 */
public class TransferException extends Exception{

    public TransferException() {
    }

    public TransferException(String message) {
        super(message);
    }
}
