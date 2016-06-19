/*
package com.zhangzhihao.SpringMVCSeedProject.Controller;

import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    AccountService accountService;

//    @Autowired
//    HttpSession session;
    */
/**
     * 转到登录界面
     * @return
     *//*

    @RequestMapping(value = "/Login",method = RequestMethod.GET)
    public String LoginPage(){
        return "Account/Login";
    }

    */
/**
     * 接收用户登录传参，判断是否登陆成功
     * @return
     *//*

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String Login(@RequestParam("UserName") String UserName, @RequestParam("Password") String Password,HttpSession session){
        String userName=UserName.trim();
        User LoginUser = accountService.select(userName);
        if(LoginUser.getPassWord().equals(Password.trim())){
            session.setAttribute("User",LoginUser);
            return "redirect:/MustLogin";

        }else {
            return "/Account/Login";
        }
    }
}
*/
