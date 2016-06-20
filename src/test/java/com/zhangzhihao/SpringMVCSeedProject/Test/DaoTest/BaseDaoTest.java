package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;


import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Utils.PageResults;
import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring.xml")
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class
})
public class BaseDaoTest {

	@Autowired
	private BaseDao<User> userDao;

	@Autowired
	private BaseDao<Teacher> teacherDao;

	@Test
	public void addTest() {
		Boolean result = userDao.add(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		assertEquals(true, result);
	}

	@Test
	public void addAndGetStringIDTest() {
		String uuid = UUID.randomUUID().toString();
		String id = userDao.addAndGetStringID(new User(uuid, "BaseDao", AuthorityType.Admin));
		assertEquals(uuid, id);
	}

	@Test
	public void addAndGetIntegerIDTest() {
		String uuid = UUID.randomUUID().toString();
		int id = teacherDao.addAndGetIntegerID(new Teacher(uuid, "password"));
		System.out.println(id);
		Assert.assertNotNull(id);
	}

	@Test
	public void deleteTest() {
		Integer integerID = teacherDao.addAndGetIntegerID(new Teacher("name", "pwd"));
		Teacher teacher = teacherDao.selectByIntegerId(integerID);
		boolean result = teacherDao.delete(teacher);
		assertEquals(true, result);
	}

	@Test
	public void deleteAllTest() {
		List<User> all = userDao.findAll();
		boolean b = userDao.deleteAll(all);
		assertTrue(b);
	}

	@Test
	public void deleteByIntegerIdTest() {
		Integer integerID = teacherDao.addAndGetIntegerID(new Teacher("name", "pwd"));
		boolean result = teacherDao.deleteByIntegerId(integerID);
		assertEquals(result, true);
	}

	@Test
	public void deleteByStringIdTest() {
		String stringId = userDao.addAndGetStringID(new User(UUID.randomUUID().toString(), "test", AuthorityType.Admin));
		boolean result = userDao.deleteByStringId(stringId);
		assertEquals(result, true);
	}

	@Test
	public void updateTest() {
		User user = userDao.selectByStringId("admin");
		if (user == null) {
			userDao.add(new User("admin", "admin", AuthorityType.Admin));
		}
		user = userDao.selectByStringId("admin");
		user.setAuthorityType(AuthorityType.School_Level_Admin);
		boolean result = userDao.update(user);
		assertEquals(result, true);
	}

	@Test
	public void addOrUpdateTest() {
		User user = userDao.selectByStringId("admin");
		if (user == null) {
			user = new User("admin", "admin", AuthorityType.Admin);
			userDao.add(user);
		}
		user.setAuthorityType(AuthorityType.Admin);
		userDao.addOrUpdate(user);

		Boolean result = userDao.add(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		assertEquals(true, result);
	}

	@Test
	public void selectByIntegerIdTest() {
		Teacher teacher = new Teacher("name", "password");
		final int resultId = teacherDao.addAndGetIntegerID(teacher);
		assertEquals(teacher.getId(), resultId);
	}

	@Test
	public void selectByStringIdTest() {
		String username = UUID.randomUUID().toString();
		User admin = new User(username, "password", AuthorityType.Admin);
		assertEquals(admin.getUserName(), username);
	}

	@Test
	public void findAllTest() {
		List<User> userList = userDao.findAll();
		if (userList.size() > 0) {
			userList.stream().forEach(System.out::println);
			assertNotNull(userList);
		}
	}


	@Test
	public void getListByPageTest() {
		List<User> listByPage = userDao.getListByPage(2, 2);
		if (listByPage.size() > 0) {
			listByPage.stream().forEach(System.out::println);
			assertNotNull(listByPage);
		}
	}


	@Test
	public void getListByPageAndRuleTest() {
		/*PageResults<User> listByPageAndRule = userDao.getListByPageAndRule(2, 2
				, Restrictions.like("passWord", "BaseDao")
				, Restrictions.or(Restrictions.eq("userName", "admin"), Restrictions.eq("authorityType", AuthorityType.Admin))
		);*/
		PageResults<User> listByPageAndRule = userDao.getListByPageAndRule(2, 2
				, Restrictions.like("passWord", "BaseDao"));
		List<User> results = listByPageAndRule.getResults();
		System.out.println(listByPageAndRule);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
			assertNotNull(results);
		}
	}


	@Test
	public void getListByPageAndRuleTest2() {
		PageResults<Teacher> listByPageAndRule = teacherDao.getListByPageAndRule(2, 2
				, Restrictions.like("name", "BaseDao")
				, Restrictions.or(Restrictions.eq("passWord", "admin"), Restrictions.eq("name", "name"))
		);
		List<Teacher> results = listByPageAndRule.getResults();
		System.out.println(listByPageAndRule);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
			assertNotNull(results);
		}
	}

	@Test
	public void executeSqlTest() {
		String sql = "insert into Teacher (id,name,password) values (?,?,?)";
		Random random = new Random();
		int i = teacherDao.executeSql(sql, random.nextInt(), "admin", "admin");//这里数据库名要和大小写一致！！！
		assertEquals(1, i);
	}

}
