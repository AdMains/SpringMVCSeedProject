package com.zhangzhihao.SpringMVCSeedProject.Controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MustLoginController {
    /**
     * 必须登录才可以进入
     */
    //@AuthByRole()
    //@Auth
    @RequiresRoles("administrator")
    //@RequiresAuthentication  需要登录
    @RequestMapping("/MustLogin")
    public String MustLogin(){
        return "/MustLogin/MustLogin";
    }
}
