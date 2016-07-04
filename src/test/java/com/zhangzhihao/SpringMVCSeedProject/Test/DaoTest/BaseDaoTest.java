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
import javax.persistence.criteria.ParameterExpression;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;


@SuppressWarnings("ALL")
public class BaseDaoTest extends BaseTest {

    @Autowired
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<Teacher> teacherDao;

    @PersistenceContext
    private EntityManager entityManager;

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
        Teacher teacher = new Teacher("name", "BaseDao");
        System.out.println(teacher);
        teacherDao.save(teacher);
        System.out.println(teacher);
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
        ParameterExpression<Enum> parameter1 = query.createParameter(Enum.class);
        ParameterExpression<String> parameter2 = query.createParameter(String.class);
        List resultList = query.whereEqual("authorityType", parameter1)
                .whereLike("passWord", parameter2)
                .createTypedQuery()
                .setParameter(parameter1, AuthorityType.College_Level_Admin)
                .setParameter(parameter2, "BaseDao")
                .getResultList();
        if (resultList != null) {
            resultList.stream().forEach(System.out::println);
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
    public void getListByPageAndQueryTest() throws Exception {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.College_Level_Admin);
        /*TypedQuery typedQuery = query.from(User.class)
                .whereEqual("authorityType", parameter1)
				.createTypedQuery()
				.setParameter(parameter1, AuthorityType.Admin);*/
        PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(2, 3, query);
        System.out.println(listByPageAndQuery);
        List<User> results = listByPageAndQuery.getResults();
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        }
        assertNotNull(results);
    }


    @Test
    public void getListByPageAndQueryTest2() throws Exception {
        Query query = new Query(entityManager);
        //ParameterExpression<String> parameter = query.createParameter(String.class);

		/*TypedQuery typedQuery = query.from(User.class)
				.whereLike("passWord", parameter)
				.createTypedQuery()
				.setParameter(parameter, "BaseDao");*/

        query.from(User.class)
                .whereLike("passWord", "BaseDao");

        PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(2, 3, query);
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
        Query query = new Query(entityManager);
		/*ParameterExpression<Enum> parameter = query.createParameter(Enum.class);
		TypedQuery typedQuery = query.from(User.class)
				.whereEqual("authorityType", parameter)
				.createTypedQuery()
				.setParameter(parameter, AuthorityType.Admin);*/
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.College_Level_Admin);

        int countByQuery = userDao.getCountByQuery(query);
        System.out.println(countByQuery);
    }

    @Test
    public void getStatisticsByQueryTest() {
        Query query = new Query(entityManager);
        ParameterExpression<Enum> parameter = query.createParameter(Enum.class);
        query.from(User.class)
                .whereNotEqual("authorityType", parameter)
                .whereIsNotNull("userName")
                .groupBy("passWord")
                .createTypedQuery()
                .setParameter(parameter, AuthorityType.Admin)
                .getResultList()
                .stream()
                .forEach(System.out::println);

    }

    @Test
    public void getStatisticsByQueryTestSuccess() {
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
    public void getStatisticsByQueryTest4() {
        Query query = new Query(entityManager);
        ParameterExpression parameter = query.createParameter(Enum.class);
        ParameterExpression parameter2 = query.createParameter(Enum.class);
        ParameterExpression parameter3 = query.createParameter(Enum.class);
        query.from(User.class)
                .select()
                .whereIn("authorityType", asList(parameter, parameter2, parameter3))
                .createTypedQuery()
                .setParameter(parameter, AuthorityType.Teacher)
                .setParameter(parameter2, AuthorityType.Expert)
                .setParameter(parameter3, AuthorityType.College_Level_Admin)
                .getResultList()
                .forEach(System.out::println);
    }

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
