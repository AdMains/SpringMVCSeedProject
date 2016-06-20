package com.zhangzhihao.SpringMVCSeedProject.Service;


import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Model.PageResults;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;


public class BaseService<T> {
	private BaseDao<T> baseDao;
	private Class<T> modelClass;

	public BaseService() {
		modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		baseDao=new BaseDao<T>(modelClass);
	}

	/**
	 * 保存对象
	 *
	 * @param model 需要添加的对象
	 * @return 是否添加成功
	 */
	public Boolean add(T model) {
		return baseDao.add(model);
	}

	/**
	 * 添加并且返回Integer类型的ID
	 *
	 * @param model 需要添加的对象
	 * @return Integer类型的ID, 返回-1表示失败
	 */
	public Integer addAndGetIntegerID(T model) {
		return baseDao.addAndGetIntegerID(model);
	}

	/**
	 * 添加并且返回String类型的ID
	 *
	 * @param model 需要添加的对象
	 * @return String类型的ID, 返回NULL表示失败
	 */
	public String addAndGetStringID(T model) {
		return baseDao.addAndGetStringID(model);
	}


	/**
	 * 删除对象
	 *
	 * @param model 需要删除的对象
	 * @return 是否删除成功
	 */
	public boolean delete(T model) {
		return baseDao.delete(model);
	}

	/**
	 * 批量删除对象
	 *
	 * @param modelList 需要删除的对象的集合
	 * @return 是否删除成功
	 */
	public boolean deleteAll(List<T> modelList) {
		return baseDao.deleteAll(modelList);
	}

	/**
	 * 按照Integer id删除对象
	 *
	 * @param id 需要删除的对象的id
	 * @return 是否删除成功
	 */
	public boolean deleteByIntegerId(Integer id) {
		return baseDao.deleteByIntegerId(id);
	}

	/**
	 * 按照String id删除对象
	 *
	 * @param id 需要删除的对象的id
	 * @return 是否删除成功
	 */
	public boolean deleteByStringId(String id) {
		return baseDao.deleteByStringId(id);
	}

	/**
	 * 更新对象
	 *
	 * @param model 需要更新的对象
	 * @return 是否更新成功
	 */
	public boolean update(T model) {
		return baseDao.update(model);
	}


	/**
	 * 添加或者更新
	 *
	 * @param model 需要更新或添加的对象
	 * @return 是否成功
	 */
	public boolean addOrUpdate(T model) {
		return baseDao.addOrUpdate(model);
	}

	/**
	 * 通过Integer主键, 查询对象
	 *
	 * @param id 主键(Integer)
	 * @return model
	 */
	public T selectByIntegerId(int id) {
		return baseDao.selectByIntegerId(id);
	}

	/**
	 * 通过String主键, 查询对象
	 *
	 * @param id 主键(Integer)
	 * @return model
	 */
	@Deprecated
	public T selectByStringId(String id) {
		return baseDao.selectByStringId(id);
	}

	/**
	 * 获得全部
	 *
	 * @return List
	 */
	public List<T> findAll() {
		return baseDao.findAll();
	}

	/**
	 * 模糊查询
	 *
	 * @param likeRule 将查询条件拼接为map
	 * @return 查询结果
	 */
	public List<T> findByLike(Map<String, Object> likeRule) {
		return baseDao.findByLike(likeRule);
	}


	/**
	 * 多条件查询
	 *
	 * @param likeRule like条件的map
	 * @param andRule  and条件的map
	 * @return 查询结果
	 */
	public List<T> multiRuleQuery(Map<String, Object> likeRule, Map<String, Object> andRule) {
		return baseDao.multiRuleQuery(likeRule,andRule);
	}


	/**
	 * 分页查询
	 *
	 * @param currentPageNumber 页码
	 * @param pageSize   每页数量
	 * @return 查询结果
	 */
	public List<T> getListByPage(Integer currentPageNumber, Integer pageSize) {
		return baseDao.getListByPage(currentPageNumber,pageSize);
	}


	/**
	 * 按条件分页
	 *
	 * @param currentPageNumber 页码
	 * @param pageSize          每页数量l
	 * @param likeRule          like条件的map
	 * @param andRule           and条件的map
	 * @return 查询结果
	 */
	public PageResults<T> getListByPageAndRule(Integer currentPageNumber, Integer pageSize, Map<String, Object> likeRule, Map<String, Object> andRule) {
		return baseDao.getListByPageAndRule(currentPageNumber,pageSize,likeRule,andRule);
	}


	/**
	 * 执行Sql语句
	 *
	 * @param sqlString sql
	 * @param values    不定参数数组
	 * @return 受影响的行数
	 */
	@Deprecated
	public int executeSql(String sqlString, Object... values) {
		return baseDao.executeSql(sqlString,values);
	}

	/**
	 * refresh 刷新实体状态
	 *
	 * @param t 实体
	 */
	public void refresh(T t) {
		baseDao.refresh(t);
	}
}
