package com.zhangzhihao.SpringMVCSeedProject.Dao;


import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.generic.GenericDao;
import org.springframework.stereotype.Repository;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.HibernateUtil.getSession;


@Repository
public class AccountDao extends BaseDao implements GenericDao<User,String>  {
    /**
     * 插入对象
     *
     * @param user 对象
     */
    @Override
    public int insert(User user) {
        int flag=-1;
        try {
            session = getSession();
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
            session.close();
            flag=1;
        }catch (Exception ex){
            System.out.println(ex.getCause());
        }
        return flag;
    }

    /**
     * 更新对象
     *
     * @param user 对象
     */
    @Override
    public int update(User user) {
        return 0;
    }

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    @Override
    public int delete(String id) {
        return 0;
    }

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    @Override
    public User select(String id) {
        session = getSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }
}
