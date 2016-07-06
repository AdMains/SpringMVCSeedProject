package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;

import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class LinkQueryTest extends BaseTest {
    @Autowired
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<Teacher> teacherDao;

    @Autowired
    private BaseDao<BankCard> bankCardDao;

    @PersistenceContext
    private EntityManager entityManager;

    //@Test
    public void addUser() {
        User user = new User("admin", "admin", AuthorityType.Admin);
        userDao.save(user);
    }

    //@Test
    public void addUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userList.add(new User(i + "", i + "", AuthorityType.Admin));
        }
        userDao.saveAll(userList);
        List<BankCard> bankCardList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            User currentUser = userList.get(i);
            BankCard currentCard = new BankCard(random.nextLong() + "", "建行", currentUser.getUserName(), currentUser);
            bankCardList.add(currentCard);
            currentUser.setBankCard(currentCard);
        }
        userDao.saveOrUpdateAll(userList);
        bankCardDao.saveOrUpdateAll(bankCardList);
    }

    //@Test
    public void addBankCard() {
        User admin = userDao.getById(User.class, "admin");
        BankCard bankCard = new BankCard("8888", "建行", admin.getUserName(), admin);
        userDao.saveOrUpdate(admin);
        bankCardDao.saveOrUpdate(bankCard);
    }

    @Test
    public void NormalQueryTest() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        Join<User, BankCard> join = from.join("bankCard");
        query.where(criteriaBuilder.equal(join.get("user").get("userName"), "admin"));

        TypedQuery typeQuery = entityManager.createQuery(query);
        typeQuery
                .getResultList()
                .forEach(System.out::println);
    }

    @Test
    public void QueryTest() {
        Query query = new Query(User.class, entityManager);
        query
                .whereIsNotNull("userName")
                .join("bankCard")
                .whereEqual(query.getFrom().get("user").get("userName"), "admin")
                .whereNotEqual("cardNumber", "test")
                .createTypedQuery()
                .getResultList()
                .forEach(System.out::println);
    }
}
