package com.zhangzhihao.SpringMVCSeedProject.Service;


import com.zhangzhihao.SpringMVCSeedProject.Dao.BaseDao;
import com.zhangzhihao.SpringMVCSeedProject.Dao.Query;
import com.zhangzhihao.SpringMVCSeedProject.Utils.PageResults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * BaseService作为所有Service的基类，需要使用的话，需要先编写一个继承自此类的类
 *
 * @param <T> 实体类型
 */
@SuppressWarnings({"rawtypes", "unchecked"})
class BaseService<T> {
	@Autowired
	private BaseDao<T> baseDao;
	private Class<T> modelClass;

	public BaseService() {
		modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 保存对象
	 *
	 * @param model 需要添加的对象
	 */
	public void save(@NotNull final T model) {
		baseDao.save(model);
	}

	/**
	 * 批量保存对象
	 *
	 * @param modelList 需要增加的对象的集合
	 *                  失败会抛异常
	 */
	public void saveAll(final @NotNull List<T> modelList) {
		baseDao.saveAll(modelList);
	}

	/**
	 * 删除对象
	 *
	 * @param model 需要删除的对象
	 *              失败会抛异常
	 */
	public void delete(@NotNull final T model) {
		baseDao.delete(model);
	}

	/**
	 * 批量删除对象
	 *
	 * @param modelList 需要删除的对象的集合
	 *                  失败会抛异常
	 */
	public void deleteAll(@NotNull final List<T> modelList) {
		baseDao.deleteAll(modelList);
	}

	/**
	 * 按照id删除对象
	 *
	 * @param id 需要删除的对象的id
	 *           失败抛出异常
	 */
	public void deleteById(@NotNull Serializable id) {
		baseDao.deleteById(modelClass, id);
	}

	/**
	 * 更新或保存对象
	 *
	 * @param model 需要更新的对象
	 *              失败会抛出异常
	 */
	public void saveOrUpdate(@NotNull final T model) {
		baseDao.saveOrUpdate(model);
	}

	/**
	 * 批量更新或保存对象
	 *
	 * @param modelList 需要更新或保存的对象
	 *                  失败会抛出异常
	 */
	public void saveOrUpdateAll(@NotNull final List<T> modelList) {
		baseDao.saveOrUpdateAll(modelList);
	}

	/**
	 * 通过主键, 查询对象
	 *
	 * @param id 主键(Serializable)
	 * @return model
	 */
	public T getById(@NotNull final Serializable id) {
		return baseDao.getById(modelClass, id);
	}

	/**
	 * 获得全部
	 *
	 * @return List
	 */
	public List<T> getAll() {
		return baseDao.getAll(modelClass);
	}


	/**
	 * 分页查询
	 *
	 * @param currentPageNumber 页码
	 * @param pageSize          每页数量
	 * @return 查询结果
	 */
	public List<T> getListByPage(@NotNull final Integer currentPageNumber,
	                             @NotNull final Integer pageSize) {
		return baseDao.getListByPage(modelClass, currentPageNumber, pageSize);
	}

	/**
	 * 按条件分页
	 *
	 * @param currentPageNumber 页码
	 * @param pageSize          每页数量
	 * @param query             封装的查询条件
	 * @return 查询结果
	 */
	public PageResults<T> getListByPageAndQuery(@NotNull Integer currentPageNumber,
	                                            @NotNull Integer pageSize,
	                                            @NotNull Query query) {
		return baseDao.getListByPageAndQuery(modelClass, currentPageNumber, pageSize, query);
	}


	/**
	 * 获得符合对应条件的数量 利用Count(*)实现
	 *
	 * @param query 查询条件
	 * @return 数量
	 */
	public int getCountByQuery(@NotNull final Query query) {
		return baseDao.getCountByQuery(modelClass, query);
	}

	/**
	 * 获得统计结果
	 *
	 * @param query 查询条件
	 * @return 结果
	 */
	public List getStatisticsByQuery(@NotNull final Query query) {
		return baseDao.getStatisticsByQuery(modelClass, query);
	}


	/**
	 * 执行Sql语句
	 *
	 * @param sql    sql
	 * @param values 不定参数数组
	 * @return 受影响的行数
	 */
	public int executeSql(@NotNull String sql, @NotNull Object... values) {
		return baseDao.executeSql(sql, values);
	}

	/**
	 * refresh 刷新实体状态
	 *
	 * @param model 实体
	 */
	public void refresh(@NotNull T model) {
		baseDao.refresh(model);
	}
}
