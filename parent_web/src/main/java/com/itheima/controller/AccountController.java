package com.itheima.controller;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        accountService.saveAccount(account);
        return "success";
    }

    /**
     * 转账
     * @return
     */
    @RequestMapping("/transfer")
    public String transfer(Integer old_id, Integer new_id, Double money, Model model){
        //转账
        accountService.transfer(old_id,new_id,money);
        //查询转出用户
        Account old_Account = accountService.findOne(old_id);
        //加入session域
        model.addAttribute("old_Account",old_Account);
        //查询转入用户
        Account new_Account = accountService.findOne(new_id);
        model.addAttribute("new_Account",new_Account);
        model.addAttribute("money",money);

        return "success";
    }
}
