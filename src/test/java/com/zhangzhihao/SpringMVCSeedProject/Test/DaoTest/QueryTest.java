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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.ParameterExpression;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings({"unchecked"})
public class QueryTest extends BaseTest {

	@Autowired
	private BaseDao<User> userDao;

	@Autowired
	private BaseDao<Teacher> teacherDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void getAllByQueryTest() {
		Query query = new Query(entityManager);
		query.from(User.class)
				.whereEqual("authorityType", AuthorityType.Admin)
				.whereLike("passWord", "BaseDao")
				.createTypedQuery()
				.getResultList()
				.forEach(System.out::println);
	}

	@Test
	public void getListByPageAndQueryTest() {
		Query query = new Query(entityManager);
		TypedQuery typedQuery = query.from(User.class)
				.whereEqual("authorityType", AuthorityType.School_Level_Admin)
				.createTypedQuery();
		PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(2, 3, typedQuery);
		System.out.println(listByPageAndQuery);
		List<User> results = listByPageAndQuery.getResults();
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
		}
		assertNotNull(results);
	}


	@Test
	public void getListByPageAndQueryTest2() {
		Query query = new Query(entityManager);

		TypedQuery typedQuery = query.from(User.class)
				.whereLike("passWord", "BaseDao")
				.createTypedQuery();

		PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(2, 3, typedQuery);
		List<User> results = listByPageAndQuery.getResults();
		System.out.println(listByPageAndQuery);
		if (!results.isEmpty()) {
			results.forEach(System.out::println);
		}
		assertNotNull(results);
	}

	@Test
	public void getCountByQueryTest() {
		Query query = new Query(entityManager);
		TypedQuery typedQuery = query.from(User.class)
				.whereEqual("authorityType", AuthorityType.Admin)
				.createTypedQuery();
		int countByQuery = userDao.getCountByQuery(typedQuery);
		System.out.println(countByQuery);
	}

	@Test
	public void getStatisticsByQueryTest() {
		Query query = new Query(entityManager);
		query.from(User.class)
				.whereNotEqual("authorityType", AuthorityType.Admin)
				.whereIsNotNull("userName")
				.groupBy("passWord")
				.createTypedQuery()
				.getResultList()
				.stream()
				.forEach(System.out::println);

	}

	@Test
	public void getStatisticsByQueryTestSuccess() {
		Query query = new Query(entityManager);
		query.from(User.class)
				.whereNotEqual("authorityType", AuthorityType.Admin)
				.whereEqual("passWord","BaseDao")
				.whereIsNotNull("userName")
				.groupBy("passWord")
				.createTypedQuery()
				.getResultList()
				.stream()
				.forEach(System.out::println);

	}

	@Test
	public void getStatisticsByQueryTest2() {
		Query query = new Query(entityManager);
		ParameterExpression parameter = query.createParameter(String.class);
		query.from(Teacher.class)
				.selectMax("id")
				.whereNotEqual("name", parameter)
				.whereIsNotNull("name")
				.groupBy("passWord")
				.createTypedQuery()
				.setParameter(parameter, "name")
				.getResultList()
				.stream()
				.forEach(System.out::println);
	}

	@Test
	public void getStatisticsByQueryTest3() {
		Query query = new Query(entityManager);
		query.from(Teacher.class)
				.selectMax("id")
				.whereNotEqual("name", "name")
				.whereIsNotNull("name")
				.groupBy("passWord")
				.createTypedQuery()
				.getResultList()
				.stream()
				.forEach(System.out::println);
	}

	@Test
	public void getStatisticsByQueryTest4() {
		Query query = new Query(entityManager);
		query.from(User.class)
				.select()
				.whereValueIn("authorityType",asList(AuthorityType.Teacher, AuthorityType.Expert, AuthorityType.College_Level_Admin))
				.createTypedQuery()
				.getResultList()
				.forEach(System.out::println);
	}
}
