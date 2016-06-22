package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;


import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Utils.PageResults;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
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
	public void saveTest() {
		Boolean result = userDao.save(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		Boolean add = teacherDao.save(new Teacher("name", "BaseDao"));
		System.out.println(add);
		assertEquals(true, result);
	}

	@Test
	public void saveAndGetStringIDTest() {
		String uuid = UUID.randomUUID().toString();
		String id = userDao.saveAndGetStringID(new User(uuid, "BaseDao", AuthorityType.Admin));
		assertEquals(uuid, id);
	}

	@Test
	public void addAndGetIntegerIDTest() {
		String uuid = UUID.randomUUID().toString();
		int id = teacherDao.saveAndGetIntegerID(new Teacher(uuid, "password"));
		System.out.println(id);
		Assert.assertNotNull(id);
	}

	@Test
	public void deleteTest() {
		Integer integerID = teacherDao.saveAndGetIntegerID(new Teacher("name", "pwd"));
		Teacher teacher = teacherDao.getById(Teacher.class, integerID);
		teacherDao.delete(teacher);//不抛异常就是成功了
	}

	@Test
	public void deleteAllTest() {
		List<User> all = userDao.loadAll(User.class);
		userDao.deleteAll(all);//不抛异常就是成功了
	}

	@Test
	public void deleteByIntegerIdTest() {
		Integer integerID = teacherDao.saveAndGetIntegerID(new Teacher("name", "pwd"));
		teacherDao.deleteById(Teacher.class, integerID);//不抛异常就是成功了
	}

	@Test
	public void deleteByStringIdTest() {
		String stringId = userDao.saveAndGetStringID(new User(UUID.randomUUID().toString(), "test", AuthorityType.Admin));
		userDao.deleteById(User.class, stringId);//不抛异常就是成功了
	}

	@Test
	public void updateTest() {
		User user = userDao.getById(User.class, "admin");
		if (user == null) {
			userDao.save(new User("admin", "admin", AuthorityType.Admin));
		}
		user = userDao.getById(User.class, "admin");
		user.setAuthorityType(AuthorityType.School_Level_Admin);
		userDao.update(user);//不抛异常就是成功了
	}

	@Test
	public void saveOrUpdateTest() {
		User user = userDao.getById(User.class, "admin");
		if (user == null) {
			user = new User("admin", "admin", AuthorityType.Admin);
			userDao.save(user);
		}
		user.setAuthorityType(AuthorityType.Admin);
		userDao.saveOrUpdate(user);

		Boolean result = userDao.save(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		assertEquals(true, result);
	}

	@Test
	public void getByIntegerIdTest() {
		Teacher teacher = new Teacher("name", "password");
		final int resultId = teacherDao.saveAndGetIntegerID(teacher);
		assertEquals(teacher.getId(), resultId);
	}

	@Test
	public void getByStringIdTest() {
		String username = UUID.randomUUID().toString();
		User admin = new User(username, "password", AuthorityType.Admin);
		userDao.save(admin);
		User byId = userDao.getById(User.class, username);
		assertEquals(byId.getUserName(), username);
	}

	@Test
	public void findAllTest() {
		List<User> userList = userDao.loadAll(User.class);
		if (userList.size() > 0) {
			userList.stream().forEach(System.out::println);
			assertNotNull(userList);
		}
	}

	@Test
	public void getListByPageTest() {
		List<User> listByPage = userDao.getListByPage(User.class, 2, 2);
		if (listByPage.size() > 0) {
			listByPage.stream().forEach(System.out::println);
			assertNotNull(listByPage);
		}
	}

	@Test
	public void getListByPageAndRuleTest() {
		PageResults<User> listByPageAndRule = userDao.getListByPageAndRule(User.class, 2, 2
				, new Criterion[]{Restrictions.like("passWord", "BaseDao")}, new Order[]{}, new Projection[]{});
		List<User> results = listByPageAndRule.getResults();
		System.out.println(listByPageAndRule);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
			assertNotNull(results);
		}
	}

	@Test
	public void getCountByRuleTest() {
		int countByRule = userDao.getCountByRule(User.class, new Criterion[]{Restrictions.like("passWord", "BaseDao")});
		System.out.println(countByRule);
	}

	@Test
	public void getListByPageAndRuleTest2() {
		PageResults<Teacher> listByPageAndRule = teacherDao.getListByPageAndRule(Teacher.class, 2, 2
				, new Criterion[]{Restrictions.like("passWord", "BaseDao")}
				, new Order[]{}, new Projection[]{});
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
