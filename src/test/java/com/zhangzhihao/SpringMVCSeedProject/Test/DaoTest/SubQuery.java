package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;


import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Dao.Query;
import com.zhangzhihao.SpringMVCSeedProject.Model.BankCard;
import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class SubQuery extends BaseTest {
    /*  // create CriteriaQuery instance, with root Employee
        CriteriaQuery<Employee> q = cb.createQuery(Employee.class);
        Root<Employee> emp = q.from(Employee.class);
        // create Subquery instance, with root Employee
        Subquery<Employee> sq = q.subquery(Employee.class);
        Root<Employee> spouseEmp = sq.from(Employee.class);
        // the subquery references the root of the containing query
        sq.where(cb.equal(spouseEmp, emp.get(Employee_.spouse)))
                .select(spouseEmp);
        // an exists condition is applied to the subquery result:
        q.where(cb.exists(sq));
        q.select(emp).distinct(true);*/
    @Autowired
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<Teacher> teacherDao;

    @Autowired
    private BaseDao<BankCard> bankCardDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void NorMalSubQuery(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Subquery<User> subQuery = criteriaQuery.subquery(User.class);
        Root<User> subRoot = subQuery.from(User.class);

        subQuery.where(criteriaBuilder.equal(subRoot.get("userName"),"admin")).select(subRoot.get("userName"));//
        //criteriaQuery.where(criteriaBuilder.exists(subquery));
        criteriaQuery.where(criteriaBuilder.equal(root.get("passWord"),subQuery));
        //CriteriaQuery<User> passWord = criteriaQuery.select(root.get("passWord")).distinct(true);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.getResultList().forEach(System.out::println);
    }
    @Test
    public void SubQueryTest(){
        Query query=new Query(User.class,entityManager);
        Query subQuery = query.subQuery(User.class);
        subQuery.whereEqual("userName","admin");
        User singleResult = (User) subQuery.createTypedQuery().getSingleResult();
        query.whereEqual("passWord",singleResult.getUserName()).createTypedQuery().getResultList().forEach(System.out::println);
    }

}
