package com.mybatis.bank.exceptions;

/**
 * Package: com.mybatis.bank.exceptions
 * Date: 2023/8/2 10:59
 * Description: 余额不足异常
 */
public class MoneyNotEnoughException extends Exception{

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public MoneyNotEnoughException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
