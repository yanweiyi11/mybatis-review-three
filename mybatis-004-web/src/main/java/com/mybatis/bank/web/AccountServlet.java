package com.mybatis.bank.web;

import com.mybatis.bank.exceptions.MoneyNotEnoughException;
import com.mybatis.bank.exceptions.TransferException;
import com.mybatis.bank.service.AccountService;
import com.mybatis.bank.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: com.mybatis.bank.web
 * Date: 2023/8/2 10:10
 * Description: null
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formActno = request.getParameter("formActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        try {
            accountService.transfer(formActno, toActno, money);
            response.sendRedirect(request.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/error1.html");
        } catch (TransferException e) {
            response.sendRedirect(request.getContextPath() + "/error2.html");
        } catch (Exception e){
            response.sendRedirect(request.getContextPath() + "/error2.html");
        }
    }
}
