package com.zhangzhihao.SpringMVCSeedProject.Test.DaoTest;

import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Dao.Query;
import com.zhangzhihao.SpringMVCSeedProject.Model.BankCard;
import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("ALL")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:Spring.xml", "classpath:SpringMVC.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
public class FillTestData {
    private MockMvc mockMvc;
    @Autowired
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<Teacher> teacherDao;

    @Autowired
    private BaseDao<BankCard> bankCardDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void addUser() {
        User user = new User("admin", "admin", AuthorityType.Admin);
        userDao.save(user);
    }

    @Test
    public void addBankCard() {
        User admin = userDao.getById(User.class, "admin");
        BankCard bankCard = new BankCard("8888", "建行", admin.getUserName(), admin);
        userDao.saveOrUpdate(admin);
        bankCardDao.saveOrUpdate(bankCard);
    }

    @Test
    public void LinkQueryTest() {
        Query query = new Query(User.class, entityManager);
        query.addLinkQuery("bankCard", new Query(BankCard.class, entityManager));
        List<User> resultList = query.createTypedQuery().getResultList();
        System.out.println(resultList.get(0));
        System.out.println(resultList.get(0).getBankCard());
    }
}
