package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;


import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		Teacher teacher = teacherDao.selectByIntegerId(Teacher.class, integerID);
		boolean result = teacherDao.delete(teacher);
		assertEquals(true, result);
	}

	@Test
	public void deleteByIntegerIdTest() {
		Integer integerID = teacherDao.addAndGetIntegerID(new Teacher("name", "pwd"));
		boolean result = teacherDao.deleteByIntegerId(Teacher.class, integerID);
		assertEquals(result, true);
	}

	@Test
	public void deleteByStringIdTest() {
		String stringId = userDao.addAndGetStringID(new User(UUID.randomUUID().toString(), "test", AuthorityType.Admin));
		boolean result = userDao.deleteByStringId(User.class, stringId);
		assertEquals(result, true);
	}

	@Test
	public void updateTest() {
		User user = userDao.selectByStringId(User.class, "admin");
		user.setAuthorityType(AuthorityType.School_Level_Admin);
		boolean result = userDao.update(user);
		assertEquals(result,true);
	}

	@Test
	public void addOrUpdateTest() {
		User user = userDao.selectByStringId(User.class, "admin");
		if(user==null){
			user=new User("admin","admin",AuthorityType.Admin);
			userDao.add(user);
		}
		user.setAuthorityType(AuthorityType.Admin);
		userDao.addOrUpdate(user);

		Boolean result = userDao.add(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		assertEquals(true, result);
	}

	@Test
	public void selectByIntegerIdTest() {
		Teacher teacher= new Teacher("name","password");
		final int resultId = teacherDao.addAndGetIntegerID(teacher);
		assertEquals(teacher.getId(), resultId);
	}

	@Test
	public void selectByStringIdTest() {
		String username=UUID.randomUUID().toString();
		User admin = new User(username,"password",AuthorityType.Admin);
		assertEquals(admin.getUserName(),username);
	}

	@Test
	public void findAllTest() {
		List<User> userList = userDao.findAll(User.class);
		if (userList.size() > 0) {
			userList.stream().forEach(System.out::println);
			assertNotNull(userList);
		}
	}

	@Test
	public void findByLikeTest() {
		Map<String, Object> rule = new HashMap<>();
		rule.put("passWord", "BaseDao");
		rule.put("authorityType", AuthorityType.College_Level_Admin);
		List<User> userList = userDao.findByLike(User.class, rule);
		if (!userList.isEmpty()) {
			userList.forEach(System.out::println);
			assertNotNull(userList);
		}
	}

	@Test
	public void multiRuleQueryTest() {
		Map<String, Object> likerule = new HashMap<>();
		likerule.put("passWord", "BaseDao");

		Map<String, Object> orrule = new HashMap<>();
		orrule.put("userName", "dae7eaa3-f493-411e-9ed1-304734b0754b");

		Map<String, Object> andrule = new HashMap<>();
		andrule.put("authorityType", AuthorityType.Admin);
		List<User> userList = userDao.multiRuleQuery(User.class, likerule, null, andrule);
		if (!userList.isEmpty()) {
			userList.forEach(System.out::println);
			assertNotNull(userList);
		}
	}

	@Test
	public void getListByPageTest() {
		List<User> listByPage = userDao.getListByPage(User.class, 2, 2);
		if(listByPage.size()>0){
			listByPage.stream().forEach(System.out::println);
			assertNotNull(listByPage);
		}
	}

	@Test
	public void getListByPageAndRuleTest() {
		Map<String, Object> likerule = new HashMap<>();
		likerule.put("passWord", "BaseDao");

		Map<String, Object> orrule = new HashMap<>();
		orrule.put("userName", "dae7eaa3-f493-411e-9ed1-304734b0754b");

		Map<String, Object> andrule = new HashMap<>();
		andrule.put("authorityType", AuthorityType.Admin);
		List<User> userList = userDao.getListByPageAndRule(User.class, 2, 2, likerule, null, andrule);
		if (!userList.isEmpty()) {
			userList.forEach(System.out::println);
			assertNotNull(userList);
		}
	}
}
