package com.zhangzhihao.SpringMVCSeedProject.Dao;


import com.zhangzhihao.SpringMVCSeedProject.Utils.PageResults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;


/**
 * 只需要一个BaseDao就可以，需要访问数据库的地方，比如BaseService<T>只需要private BaseDao<T> baseDao即可
 *
 * @param <T> 实体类型
 */
@SuppressWarnings({"unchecked"})
@Transactional(timeout = 5)
@Repository
@Primary
public class BaseDao<T> {

	//获取到和当前事务关联的 EntityManager 对象
	//通过 @PersistenceContext 注解来标记成员变量!
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 保存对象
	 *
	 * @param model 需要添加的对象
	 */
	public void save(@NotNull final T model) {
		entityManager.persist(model);
	}

	/**
	 * 批量保存对象
	 *
	 * @param modelList 需要增加的对象的集合
	 *                  失败会抛异常
	 */
	public void saveAll(@NotNull final List<T> modelList) {
		modelList.stream().forEach(entityManager::persist);
	}

	/**
	 * 删除对象
	 *
	 * @param model 需要删除的对象
	 *              失败会抛异常
	 */
	public void delete(@NotNull final T model) {
		entityManager.remove(entityManager.contains(model) ? model : entityManager.merge(model));
	}

	/**
	 * 批量删除对象
	 *
	 * @param modelList 需要删除的对象的集合
	 *                  失败会抛异常
	 */
	public void deleteAll(@NotNull final List<T> modelList) {
		modelList.stream().forEach(this::delete);
	}

	/**
	 * 按照id删除对象
	 *
	 * @param modelClass 类型，比如User.class
	 * @param id         需要删除的对象的id
	 *                   失败抛出异常
	 */
	public void deleteById(final Class<T> modelClass, @NotNull Serializable id) {
		entityManager.remove(this.getById(modelClass, id));
	}

	/**
	 * 更新或保存对象
	 *
	 * @param model 需要更新的对象
	 *              失败会抛出异常
	 */
	public void saveOrUpdate(@NotNull final T model) {
		entityManager.merge(model);
	}

	/**
	 * 批量更新或保存对象
	 *
	 * @param modelList 需要更新或保存的对象
	 *                  失败会抛出异常
	 */
	public void saveOrUpdateAll(@NotNull final List<T> modelList) {
		modelList.stream().forEach(entityManager::merge);
	}

	/**
	 * 通过主键, 查询对象
	 *
	 * @param modelClass 类型，比如User.class
	 * @param id         主键(Serializable)
	 * @return model
	 */
	@Transactional(readOnly = true)
	public T getById(Class<T> modelClass, @NotNull final Serializable id) {
		return entityManager.find(modelClass, id);
	}

	/**
	 * 获得全部
	 *
	 * @param modelClass 类型，比如User.class
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List<T> getAll(Class<T> modelClass) {
		Query query = Query.forClass(modelClass, entityManager);
		return entityManager.createQuery(query.newCriteriaQuery()).getResultList();
	}


	/**
	 * 分页查询
	 *
	 * @param modelClass        类型，比如User.class
	 * @param currentPageNumber 页码
	 * @param pageSize          每页数量
	 * @return 查询结果
	 */
	@Transactional(readOnly = true)
	public List<T> getListByPage(Class<T> modelClass,
	                             @NotNull final Integer currentPageNumber,
	                             @NotNull final Integer pageSize) {
		if (currentPageNumber <= 0 || pageSize <= 0) {
			return null;
		}
		Query query = Query.forClass(modelClass, entityManager);
		return entityManager.createQuery(query.newCriteriaQuery())
				.setFirstResult((currentPageNumber - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
	}

	/**
	 * 按条件分页
	 *
	 * @param modelClass        类型，比如User.class
	 * @param currentPageNumber 页码
	 * @param pageSize          每页数量
	 * @param query             封装的查询条件
	 * @return 查询结果
	 */
	@Transactional(readOnly = true)
	public PageResults<T> getListByPageAndQuery(Class<T> modelClass,
	                                            @NotNull Integer currentPageNumber,
	                                            @NotNull Integer pageSize,
	                                            @NotNull Query query) {
		//参数验证
		int totalCount = getCountByQuery(modelClass, query);
		int pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;

		if (currentPageNumber > pageCount && pageCount != 0) {
			currentPageNumber = pageCount;
		}
		TypedQuery typedQuery = entityManager.createQuery(query.newCriteriaQuery());
		//查看是否要分页
		if (currentPageNumber > 0 && pageSize > 0) {
			typedQuery
					.setFirstResult((currentPageNumber - 1) * pageSize)
					.setMaxResults(pageSize);
		}
		List<T> list = typedQuery.getResultList();
		return new PageResults<>(currentPageNumber + 1, currentPageNumber, pageSize, totalCount, pageCount, list);
	}


	/**
	 * 获得符合对应条件的数量 利用Count(*)实现
	 *
	 * @param modelClass 类型，比如User.class
	 * @param query      查询条件
	 * @return 数量
	 */
	@Transactional(readOnly = true)
	public int getCountByQuery(Class<T> modelClass, @NotNull final Query query) {
		return entityManager
				.createQuery(query.newCriteriaQuery())
				.getResultList()
				.size();
	}

	/**
	 * 获得统计结果
	 *
	 * @param modelClass 类型，比如User.class
	 * @param query      查询条件
	 * @return 结果
	 */
	@Transactional(readOnly = true)
	public List getStatisticsByQuery(Class<T> modelClass,
	                                 @NotNull final Query query) {
		return entityManager.createQuery(query.newCriteriaQuery()).getResultList();
	}


	/**
	 * 执行Sql语句
	 *
	 * @param sql    sql
	 * @param values 不定参数数组
	 * @return 受影响的行数
	 */
	public int executeSql(@NotNull String sql, @NotNull Object... values) {
		javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql);
		for (int i = 0; i < values.length; i++) {
			nativeQuery.setParameter(i, values[i]);
		}
		return nativeQuery.executeUpdate();
	}

	/**
	 * refresh 刷新实体状态
	 *
	 * @param model 实体
	 */
	public void refresh(@NotNull T model) {
		entityManager.refresh(model);
	}
}

