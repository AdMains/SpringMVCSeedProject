package com.zhangzhihao.SpringMVCSeedProject.Test.ServiceTest;

import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Service.UserService;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {
	@Autowired
	private UserService service;

	@Test
	public void getByIdTest() {
		User admin = service.getById("admin");
		System.out.println(admin);
	}
}
