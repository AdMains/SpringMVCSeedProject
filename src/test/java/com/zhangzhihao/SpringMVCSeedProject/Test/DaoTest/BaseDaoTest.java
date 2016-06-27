package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;


import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Dao.Query;
import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import com.zhangzhihao.SpringMVCSeedProject.Utils.PageResults;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SuppressWarnings({"unchecked"})
public class BaseDaoTest extends BaseTest {

	@Autowired
	private BaseDao<User> userDao;

	@Autowired
	private BaseDao<Teacher> teacherDao;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 测试@NotNull 注解
	 */
	@Test(expected = IllegalArgumentException.class)
	public void saveAllTest() {
		userDao.saveAll(null);
	}

	@Test
	public void saveTest() {
		userDao.save(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		teacherDao.save(new Teacher("name", "BaseDao"));
	}

	@Test
	public void deleteTest() {
		userDao.save(new User("66666", "ppppp", AuthorityType.Admin));
		userDao.deleteById(User.class, "66666");
	}

	@Test
	public void deleteAllTest() {
		List<User> all = userDao.getAll(User.class);
		userDao.deleteAll(all);
	}

	@Test
	public void updateTest() {
		User user = userDao.getById(User.class, "admin");
		if (user == null) {
			userDao.save(new User("admin", "admin", AuthorityType.Admin));
		}
		user = userDao.getById(User.class, "admin");
		user.setAuthorityType(AuthorityType.School_Level_Admin);
		userDao.saveOrUpdate(user);
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

		userDao.saveOrUpdate(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
	}

	@Test
	public void getByIntegerIdTest() {
		Teacher teacher = new Teacher("name", "password");
		teacherDao.save(teacher);
		assertEquals(teacher.getId(),teacherDao.getById(Teacher.class,teacher.getId()).getId());
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
		List<User> userList = userDao.getAll(User.class);
		System.out.println(userList);
		if (userList.size() > 0) {
			userList.stream().forEach(System.out::println);
		}
		assertNotNull(userList);
	}

	@Test
	public void getListByPageTest() {
		List<User> listByPage = userDao.getListByPage(User.class, 2, 2);
		if (listByPage.size() > 0) {
			listByPage.stream().forEach(System.out::println);
		}
		assertNotNull(listByPage);
	}

	@Test
	public void getListByPageAndRuleTest() {
		Query query=Query.forClass(User.class,entityManager);
		query.eq("authorityType",4);
		PageResults<User> listByPageAndRule = userDao.getListByPageAndQuery(User.class,2,3,query);
		List<User> results = listByPageAndRule.getResults();
		System.out.println(listByPageAndRule);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
		}
		assertNotNull(results);
	}


	@Test
	public void getListByPageAndRuleTest2() {
//		PageResults<Teacher> listByPageAndRule = teacherDao.getListByPageAndQuery
//		List<Teacher> results = listByPageAndRule.getResults();
//		System.out.println(listByPageAndRule);
//		if (!results.isEmpty()) {
//			results.forEach(System.out::println);
//		}
//		assertNotNull(results);
	}

	@Test
	public void getListByPageAndRuleTest3() {
//		PageResults<Teacher> listByPageAndRule = teacherDao.getListByPageAndRule(Teacher.class, 1, 2
//				, new Criterion[]{}
//				, new Order[]{Order.asc("name")}, new Projection[]{Projections.projectionList()
//						.add(Property.forName("passWord").as("passWord"))
//						.add(Property.forName("name").as("name"))
//				});
//		List<Teacher> results = listByPageAndRule.getResults();
//		System.out.println(listByPageAndRule);
//		if (!results.isEmpty()) {
//			results.forEach(System.out::println);
//		}
//		assertNotNull(results);
	}

	@Test
	public void getCountByRuleTest() {
		Query query = Query.forClass(User.class, entityManager);
		query.eq("authorityType",1);
		int countByRule = userDao.getCountByQuery(User.class,query);
		System.out.println(countByRule);
	}

	@Test
	public void getStatisticsByRuleTest() {
//		List result = teacherDao.getStatisticsByRule(Teacher.class, new Criterion[]{}, new Projection[]{Projections.projectionList()
//				.add(Projections.groupProperty("passWord"))
//				.add(Projections.count("id"))
//		});
//		for (Object item : result) {
//			Object[] objects = (Object[]) item;
//			for (int i = 0; i < objects.length; i++) {
//				System.out.print(objects[i] + "           ");
//			}
//			System.out.println();
//		}
	}

	@Test
	public void executeSqlTest() {
		String sql = "insert into Teacher (id,name,password) values (?,?,?)";
		Random random = new Random();
		int i = teacherDao.executeSql(sql, random.nextInt(), "admin", "admin");//这里数据库名要和大小写一致！！！
		assertEquals(1, i);
	}

}
