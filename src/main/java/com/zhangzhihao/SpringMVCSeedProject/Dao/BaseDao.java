package com.zhangzhihao.SpringMVCSeedProject.Dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.HibernateUtil.getSession;

@Repository
public class BaseDao<T> {
	private static Session session;
	private static Transaction transaction;

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
	 * 按照Integer id删除对象
	 *
	 * @param id 需要删除的对象的id
	 * @return 是否删除成功
	 */
	public boolean deleteByIntegerId(Class<T> clazz, Integer id) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			T t = (T) session.get(clazz, id);
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
	public boolean deleteByStringId(Class<T> clazz, String id) {
		boolean flag = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			T t = (T) session.get(clazz, id);
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
	public T selectByIntegerId(Class<T> clazz, int id) {
		T model = null;
		try {
			session = getSession();
			model = (T) session.get(clazz, id);
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
	public T selectByStringId(Class<T> clazz, String id) {
		T model = null;
		try {
			session = getSession();
			Query q = session.createQuery("from " + clazz.getName() + " where id = :id");
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
	 * @param clazz 获得列表的对象类型
	 * @return List
	 */
	public List<T> findAll(Class<T> clazz) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(clazz);
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
	 * @param clazz    要查询类的类型
	 * @param likeRule 将查询条件拼接为map
	 * @return 查询结果
	 */
	public List<T> findByLike(Class<T> clazz, Map<String, Object> likeRule) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(clazz);
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
	 * @param orRule   or条件的map
	 * @param andRule  and条件的map
	 * @return 封装好的Criteria
	 */
	private Criteria makeCriteriaByRule(Criteria criteria, Map<String, Object> likeRule, Map<String, Object> orRule, Map<String, Object> andRule) {
		if (likeRule == null) {
			likeRule = new HashMap<>();
		}
		if (orRule == null) {
			orRule = new HashMap<>();
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
		if (!orRule.isEmpty()) {
			Set<Map.Entry<String, Object>> entrySet = orRule.entrySet();
			Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> next = iterator.next();
				//criteria1.add(Restrictions.or(Restrictions.eq("className","一年一班"),Restrictions.eq("className","一年二班")));
				criteria.add(Restrictions.or(Restrictions.eq(next.getKey(), next.getValue())));
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
	 * @param clazz    要查询的类型
	 * @param likeRule like条件的map
	 * @param orRule   or条件的map
	 * @param andRule  and条件的map
	 * @return 查询结果
	 */
	public List<T> multiRuleQuery(Class<T> clazz, Map<String, Object> likeRule, Map<String, Object> orRule, Map<String, Object> andRule) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(clazz);
			criteria = makeCriteriaByRule(criteria, likeRule, orRule, andRule);
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
	 * @param clazz      要查询的类型
	 * @param pageNumber 页码
	 * @param pageSize   每页数量
	 * @return 查询结果
	 */
	public List<T> getListByPage(Class<T> clazz, Integer pageNumber, Integer pageSize) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(clazz);
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
	 * @param clazz      要查询的类型
	 * @param pageNumber 页码
	 * @param pageSize   每页数量
	 * @param likeRule   like条件的map
	 * @param orRule     or条件的map
	 * @param andRule    and条件的map
	 * @return 查询结果
	 */
	public List<T> getListByPageAndRule(Class<T> clazz, Integer pageNumber, Integer pageSize, Map<String, Object> likeRule, Map<String, Object> orRule, Map<String, Object> andRule) {
		List<T> ListModel = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(clazz);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			criteria = makeCriteriaByRule(criteria, likeRule, orRule, andRule);
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


}

