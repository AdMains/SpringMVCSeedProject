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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.StringUtils.getRandomUUID;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;


@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class BaseDaoTest extends BaseTest {

    @Autowired
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<Teacher> teacherDao;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 对contains的单元测试
     */
    @Test
    public void containsExistTest() {
        User user = getRandomUser();
        userDao.save(user);
        assertTrue(userDao.contains(user));
    }

    /**
     * 对contains的单元测试
     */
    @Test
    public void containsNotExistTest() {
        User user = getRandomUser();
        assertFalse(userDao.contains(user));
    }

    /**
     * 对contains的单元测试 莫名其妙，用gradle能跑过的话idea内置的测试跑不过，idea内置测试跑得过的话gradle跑不过
     */
    /*@SuppressWarnings("ConstantConditions")
    @Test
    public void containsNullTest() {
        assertFalse(userDao.contains(null));
    }*/

    /**
     * 对detach的单元测试
     */
    @Test
    public void detachExistTest() {
        User user = getRandomUser();
        userDao.save(user);
        userDao.detach(user);
        boolean contains = userDao.contains(user);
        assertFalse(contains);
    }

    /**
     * 对detach的单元测试
     */
    @Test
    public void detachNotExistTest() {
        User user = getRandomUser();
        userDao.detach(user);
    }

    /**
     * 对detach的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void detachNullTest() {
        userDao.detach(null);
    }

    /**
     * 对save的单元测试
     */
    @Test
    public void saveNewTest() {
        userDao.save(getRandomUser());
    }

    /**
     * 对save的单元测试
     */
    @Test
    public void saveExistTest() {
        final User user = getRandomUser();
        userDao.save(user);
        userDao.save(user);
    }

    /**
     * 对save的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void saveNullTest() {
        userDao.save(null);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllNewTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userDao.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @Test
    public void saveAllExistTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userDao.saveAll(userList);
        userDao.saveAll(userList);
    }

    /**
     * 对saveAll的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveAllNullTest() {
        userDao.saveAll(null);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteExistTest() {
        User user = getRandomUser();
        userDao.save(user);
        userDao.delete(user);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteNotExistTest() {
        userDao.delete(getRandomUser());
    }

    /**
     * 对delete的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void deleteNullTest() {
        userDao.delete(null);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteAllExistTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userDao.saveAll(userList);
        userDao.deleteAll(userList);
    }

    /**
     * 对delete的单元测试
     */
    @Test
    public void deleteAllNotExistTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userDao.deleteAll(userList);
    }

    /**
     * 对delete的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void deleteAllNullTest() {
        userDao.deleteAll(null);
    }

    /**
     * 对deleteById的单元测试
     */
    public void deleteByExistIdTest() {
        User user = getRandomUser();
        userDao.save(user);
        userDao.deleteById(User.class, user.getUserName());
    }

    /**
     * 对deleteById的单元测试
     */
    public void deleteByNotExistIdTest() {
        userDao.deleteById(User.class, getRandomUUID());
    }

    /**
     * 对deleteById的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void deleteByNullIdTest() {
        userDao.deleteById(User.class, null);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @Test
    public void saveOrUpdate_SaveTest() {
        User user = getRandomUser();
        userDao.saveOrUpdate(user);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @Test
    public void saveOrUpdate_UpdateTest() {
        User user = getRandomUser();
        userDao.save(user);
        user.setPassWord("changed!");
        userDao.saveOrUpdate(user);
    }

    /**
     * 对saveOrUpdate的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void saveOrUpdate_NullTest() {
        userDao.saveOrUpdate(null);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_SaveTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userDao.saveOrUpdateAll(userList);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_UpdateTest() {
        List<User> userList = asList(getRandomUser()
                , getRandomUser()
                , getRandomUser()
        );
        userDao.saveAll(userList);
        userDao.saveOrUpdateAll(userList);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = Exception.class)
    public void saveOrUpdateAll_NullTest() {
        userDao.saveOrUpdateAll(null);
    }

    /**
     * 对saveOrUpdateAll的单元测试
     */
    @Test
    public void saveOrUpdateAll_NullListTest() {
        userDao.saveOrUpdateAll(new ArrayList<>());
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByExistIntegerIdTest() {
        Teacher teacher = new Teacher("name", "password");
        teacherDao.save(teacher);
        assertEquals(teacher.getId(), teacherDao.getById(Teacher.class, teacher.getId()).getId());
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByNotExistIntegerIdTest() {
        Teacher byId = teacherDao.getById(Teacher.class, new Random().nextInt());
        assertNull(byId);
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByExistStringIdTest() {
        User admin = getRandomUser();
        userDao.save(admin);
        User byId = userDao.getById(User.class, admin.getUserName());
        assertEquals(byId, admin);
    }

    /**
     * 对getById的单元测试
     */
    @Test
    public void getByNotExistStringIdTest() {
        User byId = userDao.getById(User.class, getRandomUUID());
        assertNull(byId);
    }

    /**
     * 对getAll的单元测试
     */
    @Test
    public void getAllTest() {
        List<User> userList = userDao.getAll(User.class);
        System.out.println(userList);
        if (userList.size() > 0) {
            userList.forEach(System.out::println);
        }
        assertNotNull(userList);
    }

    /**
     * 对getCount的单元测试
     */
    @Test
    public void getCountTest() {
        int count = userDao.getCount(User.class);
        System.out.println(count);
    }

    /**
     * 对getListByPage的单元测试
     */
    @Test
    public void getListByPageTest() {
        PageResults<User> userPageResults = userDao.getListByPage(User.class, -7, 2);
        List<User> listByPage = userPageResults.getResults();
        if (listByPage.size() > 0) {
            listByPage.forEach(System.out::println);
        }
        System.out.println(userPageResults);
        assertNotNull(listByPage);
    }

    /**
     * 对getListByPageAndQuery的单元测试
     */
    @Test
    public void getListByPageAndQueryTest() throws Exception {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("userName", "admin");
        PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(1, 3, query);
        System.out.println(listByPageAndQuery);
        List<User> results = listByPageAndQuery.getResults();
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        }
        assertNotNull(results);
    }

    /**
     * 对getListByPageAndQuery的单元测试
     */
    @Test
    public void getListByPageAndQueryTest2() throws Exception {
        Query query = new Query(entityManager);

        query.from(User.class)
                .whereIsNotNull("userName");

        PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(2, 5, query);
        List<User> results = listByPageAndQuery.getResults();
        System.out.println(listByPageAndQuery);
        assertNotNull(results);
    }

    /**
     * 对getPageResultsByQuery的单元测试
     */
    @Test
    public void getPageResultsByQueryTest() throws Exception {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("userName", "admin");
        PageResults<User> listByPageAndQuery = userDao.getListByPageAndQuery(1, 5, query);
        List<User> results = listByPageAndQuery.getResults();
        assertNotNull(results);
    }


    /**
     * 对getAllByQuery的单元测试
     */
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
            resultList.forEach(System.out::println);
        }
    }


    /**
     * 对getCountByQuery的单元测试
     */
    @Test
    public void getCountByQueryTest() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("authorityType", AuthorityType.College_Level_Admin);
        int countByQuery = userDao.getCountByQuery(query);
        System.out.println(countByQuery);
    }

    /**
     * 对executeSql的单元测试
     */
    @Test
    public void executeSqlTest() {
        String sql = "insert into Teacher (id,name,password) values (?,?,?)";
        Random random = new Random();
        int i = teacherDao.executeSql(sql, random.nextInt(), "admin", "admin");//这里数据库名要和大小写一致！！！
        assertEquals(1, i);
    }

    /**
     * 对queryByJpql的单元测试
     */
    @Test
    public void queryByJpqlTest() {
        String jpql = "select o from User o where o.passWord = ?0 ";
        PageResults<Object> results = userDao.getListByPageAndJpql(2, 5, jpql, "BaseDao");
        results.getResults().forEach(System.out::println);
    }

    /**
     * 对queryByJpql的单元测试
     */
    @Test
    public void queryByJpqlTest2() {
        String jpql = "select o from User o where 1=1 ";
        PageResults<Object> results = userDao.getListByPageAndJpql(2, 5, jpql);
        results.getResults().forEach(System.out::println);
    }

    /**
     * 对getCountByJpql的单元测试
     */
    @Test
    public void getCountByJpqlTest() {
        String jpql = "select COUNT(o) from User o where o.userName = ?0 ";
        int result = userDao.getCountByJpql(jpql, "admin");
        assertEquals(result, 1);
    }

    /**
     * 对getListByPageAndJpql的单元测试
     */
    @Test
    public void getListByPageAndJpqlTest() {
        String jpql = "select o from User o where o.userName = ?0 ";
        PageResults<Object> pageResults = userDao.getListByPageAndJpql(1, 5, jpql, "admin");
        assertNotNull(pageResults);
    }

    /**
     * 对executeJpql的单元测试
     */
    @Test
    public void executeJpqlTest() {
        String jpql = "update User u set u.passWord=?0 where u.userName = ?1 ";
        int result = userDao.executeJpql(jpql, "admin", "admin");
        assertEquals(result, 1);
    }

    /**
     * 对refresh的单元测试
     */
    @Test(expected = Exception.class)
    public void refreshExistTest() {
        User randomUser = getRandomUser();
        userDao.save(randomUser);
        userDao.refresh(randomUser);
    }

    /**
     * 对refresh的单元测试
     */
    @Test(expected = Exception.class)
    public void refreshNotExistTest() {
        userDao.refresh(getRandomUser());
    }

    /**
     * 对flush的单元测试
     */
    @Test
    public void flushTest() {
        userDao.flush();
    }
}
