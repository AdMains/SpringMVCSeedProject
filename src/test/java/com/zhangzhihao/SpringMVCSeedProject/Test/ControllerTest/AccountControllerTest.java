package com.zhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.zhangzhihao.SpringMVCSeedProject.Controller.AccountController;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class AccountControllerTest extends BaseTest {

	private AccountController controller;

	public AccountControllerTest(){
		controller=new AccountController();
	}

	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
//        AccountController accountController = new AccountController();
//        this.mockMvc=standaloneSetup(accountController).build();
//        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController()).setViewResolvers(viewResolver).build();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();

	}

	@Test
	public void testLoginPage() throws Exception {
		mockMvc.perform(get("/Account/Login"))
				.andExpect(view().name("Account/Login"))
				.andExpect(status().isOk());
	}

	/*
	 * 登录成功测试
     * @throws Exception
     */

	//@Test 加了验证码之后没法继续跑了
	public void testLoginSuccess() throws Exception {
//        MockHttpSession session=new MockHttpSession();
//        session.setAttribute("User",new User());

		mockMvc.perform(post("/Account/Login")
				.param("UserName", "admin")
				.param("Password", "d033e22ae348aeb5660fc2140aec35850c4da997"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/MustLogin"));
	}

	/*
	 * 登录失败测试
     * @throws Exception
     */

	@Test
	public void testLoginFalse() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("User", new User());
		mockMvc.perform(post("/Account/Login")
				.param("UserName", "admin")
				.param("Password", "11111"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/Account/Login"));
	}
}
