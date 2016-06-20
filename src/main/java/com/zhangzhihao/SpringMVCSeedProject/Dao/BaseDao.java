package com.zhangzhihao.SpringMVCSeedProject.Dao;


import com.zhangzhihao.SpringMVCSeedProject.Model.PageResults;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.HibernateUtil.getSession;


@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseDao<T> {
	private static Session session;
	private static Transaction transaction;


	private Class<T> modelClass;

	public BaseDao() {
		modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public BaseDao(Class<T> clazz){
		modelClass=clazz;
	}

	/**
	 * 保存对象
	 *
	 * @param model 需要添加的对象
	 * @return 是否添加成功
	 */
	public Boolean add(T model) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			session.save(model);

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 添加并且返回Integer类型的ID
	 *
	 * @param model 需要添加的对象
	 * @return Integer类型的ID, 返回-1表示失败
	 */
	public Integer addAndGetIntegerID(T model) {
		boolean flag = false;
		int result = -1;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			result = Integer.parseInt(session.save(model).toString());

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (flag) {
			return result;
		} else {
			return -1;
		}
	}

	/**
	 * 添加并且返回String类型的ID
	 *
	 * @param model 需要添加的对象
	 * @return String类型的ID, 返回NULL表示失败
	 */
	public String addAndGetStringID(T model) {
		boolean flag = false;
		String result = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			result = session.save(model).toString();

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (flag) {
			return result;
		} else {
			return null;
		}
	}


	/**
	 * 删除对象
	 *
	 * @param model 需要删除的对象
	 * @return 是否删除成功
	 */
	public boolean delete(T model) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			session.delete(model);

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 批量删除对象
	 *
	 * @param modelList 需要删除的对象的集合
	 * @return 是否删除成功
	 */
	public boolean deleteAll(List<T> modelList) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			for (T model : modelList) {
				session.delete(model);
			}

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 按照Integer id删除对象
	 *
	 * @param id 需要删除的对象的id
	 * @return 是否删除成功
	 */
	public boolean deleteByIntegerId(Integer id) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			T t = (T) session.get(modelClass, id);
			session.delete(t);
			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 按照String id删除对象
	 *
	 * @param id 需要删除的对象的id
	 * @return 是否删除成功
	 */
	public boolean deleteByStringId(String id) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			T t = (T) session.get(modelClass, id);
			session.delete(t);
			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 更新对象
	 *
	 * @param model 需要更新的对象
	 * @return 是否更新成功
	 */
	public boolean update(T model) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			session.update(model);

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}


	/**
	 * 添加或者更新
	 *
	 * @param model 需要更新或添加的对象
	 * @return 是否成功
	 */
	public boolean addOrUpdate(T model) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(model);

			transaction.commit();
			flag = true;
		} catch (Exception ex) {
			flag = false;
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 通过Integer主键, 查询对象
	 *
	 * @param id 主键(Integer)
	 * @return model
	 */
	public T selectByIntegerId(int id) {
		T model = null;
		try {
			session = getSession();
			model = (T) session.get(modelClass, id);
		} catch (Exception ex) {
			model = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return model;
	}

	/**
	 * 通过String主键, 查询对象
	 *
	 * @param id 主键(Integer)
	 * @return model
	 */
	@Deprecated
	public T selectByStringId(String id) {
		T model = null;
		try {
			session = getSession();
			Query q = session.createQuery("from " + modelClass.getName() + " where id = :id");
			q.setParameter("id", id);
			T result = (T) q.uniqueResult();
			model = result;
		} catch (Exception ex) {
			model = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return model;
	}

	/**
	 * 获得全部
	 *
	 * @return List
	 */
	public List<T> findAll() {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(modelClass);
			ListModel = criteria.list();
		} catch (Exception ex) {
			ListModel = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return ListModel;
	}

	/**
	 * 模糊查询
	 *
	 * @param likeRule 将查询条件拼接为map
	 * @return 查询结果
	 */
	public List<T> findByLike(Map<String, Object> likeRule) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(modelClass);
			if (likeRule == null) {
				likeRule = new HashMap<>();
			}
			if (!likeRule.isEmpty()) {
				Set<Map.Entry<String, Object>> entrySet = likeRule.entrySet();
				Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, Object> next = iterator.next();
					criteria.add(Restrictions.like(next.getKey(), next.getValue()));
				}
			}
			ListModel = criteria.list();
		} catch (Exception ex) {
			ListModel = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return ListModel;
	}


	/**
	 * 处理多条件查询的封装
	 *
	 * @param criteria Criteria
	 * @param likeRule like条件的map
	 * @param andRule  and条件的map
	 * @return 封装好的Criteria
	 */
	private Criteria makeCriteriaByRule(Criteria criteria, Map<String, Object> likeRule, Map<String, Object> andRule) {
		if (likeRule == null) {
			likeRule = new HashMap<>();
		}
		if (andRule == null) {
			andRule = new HashMap<>();
		}
		if (!likeRule.isEmpty()) {
			Set<Map.Entry<String, Object>> entrySet = likeRule.entrySet();
			Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> next = iterator.next();
				criteria.add(Restrictions.like(next.getKey(), next.getValue()));
			}
		}
		if (!andRule.isEmpty()) {
			Set<Map.Entry<String, Object>> entrySet = andRule.entrySet();
			Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> next = iterator.next();
				criteria.add(Restrictions.eq(next.getKey(), next.getValue()));
			}
		}
		return criteria;
	}

	/**
	 * 多条件查询
	 *
	 * @param likeRule like条件的map
	 * @param andRule  and条件的map
	 * @return 查询结果
	 */
	public List<T> multiRuleQuery(Map<String, Object> likeRule, Map<String, Object> andRule) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(modelClass);
			criteria = makeCriteriaByRule(criteria, likeRule, andRule);
			ListModel = criteria.list();
		} catch (Exception ex) {
			ListModel = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return ListModel;
	}


	/**
	 * 分页查询
	 *
	 * @param pageNumber 页码
	 * @param pageSize   每页数量
	 * @return 查询结果
	 */
	public List<T> getListByPage(Integer pageNumber, Integer pageSize) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(modelClass);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			ListModel = criteria.list();
		} catch (Exception ex) {
			ListModel = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return ListModel;
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
		if (currentPageNumber <= 0 || currentPageNumber <= 0) {
			return null;
		}
		List<T> ListModel = null;
		int totalCount = 0;
		int pageCount = 0;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(modelClass);
			criteria = makeCriteriaByRule(criteria, likeRule, andRule);
			totalCount = criteria.list().size();
			pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
					: totalCount / pageSize + 1;
			if (currentPageNumber > pageCount) {
				currentPageNumber = 1;
			}
			criteria.setFirstResult((currentPageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			ListModel = criteria.list();
		} catch (Exception ex) {
			ListModel = null;
			throw ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		PageResults<T> pageResults = new PageResults<T>(currentPageNumber - 1, currentPageNumber, pageSize, totalCount, pageCount, ListModel);
		return pageResults;
	}


	/**
	 * 执行Sql语句
	 *
	 * @param sqlString sql
	 * @param values    不定参数数组
	 * @return 受影响的行数
	 */
	public int executeSql(String sqlString, Object... values) {
		session = getSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					sqlQuery.setParameter(i, values[i]);
				}
			}
			result = sqlQuery.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			result = 0;
			transaction.rollback();
			throw ex;
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * refresh 刷新实体状态
	 *
	 * @param t 实体
	 */
	public void refresh(T t) {
		getSession().refresh(t);
	}
}

