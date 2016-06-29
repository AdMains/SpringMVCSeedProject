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

import static java.util.Arrays.asList;
import static org.junit.Assert.*;


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
	public void containsTest() {
		User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), AuthorityType.School_Level_Admin);
		boolean contains = userDao.contains(user);
		assertFalse(contains);
	}

	@Test
	public void detachTest() {
		User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), AuthorityType.School_Level_Admin);
		userDao.save(user);
		userDao.detach(user);
		boolean contains = userDao.contains(user);
		assertFalse(contains);
	}

	@Test
	public void saveTest() {
		userDao.save(new User(UUID.randomUUID().toString(), "BaseDao", AuthorityType.Admin));
		teacherDao.save(new Teacher("name", "BaseDao"));
	}

	@Test
	public void deleteTest() {
		userDao.save(new User("66666", UUID.randomUUID().toString(), AuthorityType.Admin));
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
		assertEquals(teacher.getId(), teacherDao.getById(Teacher.class, teacher.getId()).getId());
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
	public void getAllTest() {
		List<User> userList = userDao.getAll(User.class);
		System.out.println(userList);
		if (userList.size() > 0) {
			userList.stream().forEach(System.out::println);
		}
		assertNotNull(userList);
	}

	@Test
	public void getAllByQueryTest() {
		Query query = new Query(User.class, entityManager);
		query.whereEqual("authorityType", 0)
				.whereLike("passWord", "admin");
		List<User> allByQuery = userDao.getAllByQuery(query);
		if (allByQuery != null) {
			allByQuery.stream().forEach(System.out::println);
		}
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
	public void getListByPageAndQueryTest() {
		Query query = new Query(User.class, entityManager)
				.whereEqual("authorityType", 4);
		PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(User.class, 2, 3, query);
		List<User> results = listByPageAndQuery.getResults();
		System.out.println(listByPageAndQuery);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
		}
		assertNotNull(results);
	}


	@Test
	public void getListByPageAndQueryTest2() {
		Query query = new Query(User.class, entityManager);
		query.whereLike("passWord", "BaseDao");
		PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(User.class, 2, 3, query);
		List<User> results = listByPageAndQuery.getResults();
		System.out.println(listByPageAndQuery);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
		}
		assertNotNull(results);
	}

	@Test
	public void getCountTest() {
		int count = userDao.getCount(User.class);
		System.out.println(count);
	}

	@Test
	public void getCountByQueryTest() {
		Query query = new Query(User.class, entityManager);
		query.whereEqual("authorityType", 1);
		int countByQuery = userDao.getCountByQuery(query);
		System.out.println(countByQuery);
	}

	@Test
	public void getStatisticsByQueryTest() {
		Query query = new Query(User.class, entityManager);

		query.whereNotEqual("authorityType", 4)
				.whereIsNotNull("userName")
				.groupBy("passWord");
		Object statisticsByQuery = userDao.getStatisticsByQuery(query);
		System.out.println(statisticsByQuery);
	}

	@Test
	public void getStatisticsByQueryTest2() {
		Query query = new Query(Teacher.class, entityManager);

		query.selectMax("id")
				.whereNotEqual("name", "admin")
				.whereIsNotNull("name")
				.groupBy("passWord");
		Object statisticsByQuery = teacherDao.getStatisticsByQuery(query);
		System.out.println(statisticsByQuery);
	}

	@Test
	public void getStatisticsByQueryTest3() {
		Query query = new Query(entityManager);
		//更加语义化
		query.from(Teacher.class)
				.selectMax("id")
				.whereNotEqual("name", "admin")
				.whereIsNotNull("name")
				.groupBy("passWord");
		Object statisticsByQuery = teacherDao.getStatisticsByQuery(query);
		System.out.println(statisticsByQuery);
	}

	@Test
	public void getStatisticsByQueryTest4() {
		Query query = new Query(entityManager);
		query.from(User.class)
				.select()
				.whereIn("authorityType", asList(1, 2, 3));
		List<User> allByQuery = userDao.getAllByQuery(query);
		allByQuery.stream().forEach(System.out::println);
	}

	/*@Test
	public void getStatisticsByQueryTest5() {
		Query query = new Query(entityManager);
		Query cardQuery=new Query(entityManager);
		query.from(User.class)
				.select()
				.whereIn("authorityType",asList(1,2,3))
				.addLinkQuery("userCard",cardQuery);
		List<User> allByQuery = userDao.getAllByQuery(query);
		allByQuery.stream().forEach(System.out::println);
	}*/

	@Test
	public void queryByJpqlTest() {
		String jpql = "select o from User o where o.passWord = ?0 ";
		PageResults<Object> results = userDao.getListByPageAndJpql(2, 5, jpql, "BaseDao");
		results.getResults().stream().forEach(System.out::println);
	}

	@Test
	public void getListByPageAndJpqlTest() {
		String jpql = "select o from User o where o.userName = ?0 ";
		List<User> users = (List<User>) userDao.queryByJpql(jpql, "admin");
		users.stream().forEach(System.out::println);
	}

	@Test
	public void executeSqlTest() {
		String sql = "insert into Teacher (id,name,password) values (?,?,?)";
		Random random = new Random();
		int i = teacherDao.executeSql(sql, random.nextInt(), "admin", "admin");//这里数据库名要和大小写一致！！！
		assertEquals(1, i);
	}

}
