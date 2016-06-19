/*
package com.zhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.zhangzhihao.SpringMVCSeedProject.Controller.AccountController;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:Spring.xml" })
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//记得要在XML文件中声明事务哦~~~我是采用注解的方式
//@Transactional
public class AccountControllerTest {


    @Autowired
    private AccountService accountService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
//        AccountController accountController = new AccountController();
//        this.mockMvc=standaloneSetup(accountController).build();
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController()).setViewResolvers(viewResolver).build();
    }

    @Test
    public void AutowiredNotNull(){
        System.out.println(accountService);
        Assert.assertNotNull(accountService);
    }

    @Test
    public void testLoginPage() throws  Exception{
        mockMvc.perform(get("/Account/Login"))
                .andExpect(view().name("Account/Login"))
                .andExpect(status().isOk());
    }

    */
/**
     * 登录成功测试
     * @throws Exception
     *//*

    @Test
    public void testLoginSuccess() throws Exception{
        MockHttpSession session=new MockHttpSession();
        session.setAttribute("User",new User());

        mockMvc.perform(post("/Account/Login")
                .param("UserName","admin")
                .param("Password","admin"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/MustLogin"));
    }

    */
/**
     * 登录失败测试
     * @throws Exception
     *//*

    @Test
    public void testLoginFalse() throws Exception{
        MockHttpSession session=new MockHttpSession();
        session.setAttribute("User",new User());

//        Map<String, Object> sessionAttrs = new HashMap<>();
//        sessionAttrs.put("sessionAttrName", "sessionAttrValue");

        mockMvc.perform(post("/Account/Login")
                .param("UserName","admin")
                .param("Password","11111"))
                .andExpect(status().is(200))
                .andExpect(view().name("/Account/Login"));
    }
}
*/
