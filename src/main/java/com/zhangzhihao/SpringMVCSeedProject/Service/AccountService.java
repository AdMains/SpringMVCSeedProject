package com.zhangzhihao.SpringMVCSeedProject.Service;


import com.zhangzhihao.SpringMVCSeedProject.Dao.AccountDao;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements GenericService<User,String> {


	@Autowired
	AccountDao accountDao;

	/**
	 * 插入对象
	 *
	 * @param user 对象
	 */
	@Override
	public int insert(User user) {
		return accountDao.insert(user);
	}

	/**
	 * 更新对象
	 *
	 * @param user 对象
	 */
	@Override
	public int update(User user) {
		return accountDao.update(user);
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
	 * @return model 对象
	 */
	@Override
	public User select(String id) {
		return accountDao.select(id);
	}

	/**
	 * 查询单个对象
	 *
	 * @return 对象
	 */
	@Override
	public User selectOne() {
		return null;
	}

	/**
	 * 查询多个对象
	 *
	 * @return 对象集合
	 */
	@Override
	public List<User> selectList() {
		return null;
	}
}
