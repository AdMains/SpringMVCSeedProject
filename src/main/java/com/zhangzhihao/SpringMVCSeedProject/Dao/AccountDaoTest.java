/*
package com.zhangzhihao.SpringMVCSeedProject.Dao;


import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.generic.GenericDao;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AccountDaoTest
        extends HibernateDaoSupport
        implements GenericDao<User,String>  {

    */
/**
     * 插入对象
     *
     * @param user 对象
     *//*

    @Override
    public int insert(User user) {

        return 0;
    }

    */
/**
     * 更新对象
     *
     * @param user 对象
     *//*

    @Override
    public int update(User user) {
        return 0;
    }

    */
/**
     * 通过主键, 删除对象
     *
     * @param id 主键
     *//*

    @Override
    public int delete(String id) {
        return 0;
    }

    */
/**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     *//*

    @Override
    public User select(String id) {
        return null;
    }

    public List<User> getList(){
        Session se=this.getSessionFactory().openSession();
        return (List<User>) getHibernateTemplate().find("from User");
    }
}
*/
