package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;


import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.zhangzhihao.SpringMVCSeedProject.Dao.AccountDao;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring.xml")
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class
})
public class AccountDaoTest {

	@Autowired
	private AccountDao accountDao;

	@Test
	public void insertTest() {
		User user=new User(UUID.randomUUID().toString(),UUID.randomUUID().toString(), AuthorityType.Admin);
		int result = accountDao.insert(user);
		Assert.assertEquals(1,result);
	}

	@Test
	public void selectTest() {
		User admin = accountDao.select("admin");
		Assert.assertEquals("admin",admin.getUserName());
	}
}
