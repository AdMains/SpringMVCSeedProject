package com.zhangzhihao.SpringMVCSeedProject.Dao;

import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> {
	public UserDao(){
		this.modelClass=User.class;
	}
}
