package com.zhangzhihao.SpringMVCSeedProject.Test.ServiceTest;

import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Service.UserService;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.zhangzhihao.SpringMVCSeedProject.Service.UserService.makeSHA256PasswordWithSalt;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class UserServiceTest extends BaseTest {
	@Autowired
	private UserService service;

	@Test
	public void makeSHA256PasswordWithSaltTest(){
		User user=new User("admin","admin");
		User SHAuser = makeSHA256PasswordWithSalt(user);
		assertEquals(SHAuser.getPassWord(),"d82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37537b7892");
	}

	@Test
	public void getByIdTest() {
		User admin = null;
		try {
			admin = service.getById("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(admin);
	}
}
