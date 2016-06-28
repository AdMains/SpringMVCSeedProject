package com.zhangzhihao.SpringMVCSeedProject.Dao;


import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.In;
import java.io.Serializable;
import java.util.*;

/**
 * Query
 * 封装JPA CriteriaBuilder查询条件
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes", "null", "hiding"})
public class Query implements Serializable {

	private static final long serialVersionUID = 3366932251068926942L;

//	private static Logger log = Logger.getLogger(Query.class);

	private EntityManager entityManager;

	/**
	 * 要查询的实体类型
	 */
	private Class clazz;

	/**
	 * 查询根，定义查询的From子句中能出现的类型。
	 * Criteria查询的查询根定义了实体类型，能为将来导航获得想要的结果，它与SQL查询中的FROM子句类似。
	 * Root实例也是类型化的，且定义了查询的FROM子句中能够出现的类型。
	 * 查询根实例能通过传入一个实体类型给 AbstractQuery.from方法获得。
	 * Criteria查询，可以有多个查询根。
	 */
	private Root from;

	/**
	 * 谓词，也就是过滤条件，用CriteriaBuilder生成。
	 * 过滤条件应用到SQL语句的FROM子句中，
	 * 在Criteria 查询中，查询条件通过Predicate 或Expression 实例应用到CriteriaQuery 对象上。
	 */
	private List<Predicate> predicates;

	/**
	 * 安全查询主语句
	 */
	private CriteriaQuery criteriaQuery;

	/**
	 * 安全查询创建工厂。
	 * CriteriaBuilder是一个工厂对象，安全查询的开始，
	 * 用于构建JPA安全查询，创建CriteriaQuery，创建查询具体具体条件Predicate 等。
	 */
	private CriteriaBuilder criteriaBuilder;

	/**
	 * 排序方式列表
	 */
	private List<Order> orders;

	/**
	 * 子查询
	 */
	private Map<String, Query> subQuery;

	/**
	 * 关联查询
	 */
	private Map<String, Query> linkQuery;

	/**
	 * 子查询字段
	 */
	private String projection;

	/**
	 * 或条件
	 */
	private List<Query> orQuery;

	/**
	 * 分组条件
	 */
	private String groupBy;

	private Query() {
	}

	/**
	 * 创建查询条件
	 *
	 * @param clazz
	 * @param entityManager
	 */
	public Query(@NotNull Class clazz, @NotNull EntityManager entityManager) {
		this.clazz = clazz;
		this.entityManager = entityManager;
		this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(this.clazz);
		this.from = criteriaQuery.from(this.clazz);
		this.predicates = new ArrayList();
		this.orders = new ArrayList();
	}

	/**
	 * 增加子查询,必须设置子查询字段 projection
	 */
	private void addSubQuery(@NotNull final String propertyName, @NotNull Query query) {
		if (this.subQuery == null)
			this.subQuery = new HashMap();

		if (query.projection == null)
			throw new RuntimeException("子查询字段未设置");

		this.subQuery.put(propertyName, query);
	}

//	private void addSubQuery(@NotNull Query query) {
//		addSubQuery(query.projection, query);
//	}

	/**
	 * 增关联查询
	 */
	public void addLinkQuery(@NotNull final String propertyName, @NotNull Query query) {
		if (this.linkQuery == null)
			this.linkQuery = new HashMap();

		this.linkQuery.put(propertyName, query);
	}

	/**
	 * 相等
	 */
	public Query eq(@NotNull final String propertyName, @NotNull final Object value) {
		this.predicates.add(criteriaBuilder.equal(from.get(propertyName), value));
		return this;
	}

	/**
	 * 或
	 *
	 * @param propertyName
	 * @param value
	 */
	public Query or(@NotNull final List<String> propertyName, @NotNull final Object value) {
		Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(from.get(propertyName.get(0)), value));
		for (int i = 1; i < propertyName.size(); i++)
			predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(from.get(propertyName.get(i)), value));
		this.predicates.add(predicate);
		return this;
	}


	/**
	 * 模糊查询,或者包含
	 *
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Query orLike(@NotNull final List<String> propertyName, @NotNull String value) {
		if (!value.contains("%"))
			value = "%" + value + "%";
		Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(from.get(propertyName.get(0)), value));
		for (int i = 1; i < propertyName.size(); i++)
			predicate = criteriaBuilder.or(predicate, criteriaBuilder.like(from.get(propertyName.get(i)), value));
		this.predicates.add(predicate);
		return this;
	}

	/**
	 * 查找特定字段为空
	 */
	public Query isNull(@NotNull final String propertyName) {
		this.predicates.add(criteriaBuilder.isNull(from.get(propertyName)));
		return this;
	}

	/**
	 * 查找特定字段非空
	 */
	public Query isNotNull(@NotNull final String propertyName) {
		this.predicates.add(criteriaBuilder.isNotNull(from.get(propertyName)));
		return this;
	}

	/**
	 * 不相等
	 */
	public Query notEq(@NotNull final String propertyName, @NotNull final Object value) {
		this.predicates.add(criteriaBuilder.notEqual(from.get(propertyName), value));
		return this;
	}


	/**
	 * 模糊匹配
	 *
	 * @param propertyName 属性名称
	 * @param value        属性值
	 */
	public Query like(@NotNull final String propertyName, @NotNull String value) {
		if (!value.contains("%"))
			value = "%" + value + "%";
		this.predicates.add(criteriaBuilder.like(from.get(propertyName), value));
		return this;
	}

	/**
	 * 时间区间查询
	 *
	 * @param propertyName 属性名称
	 * @param lo           属性起始值
	 * @param go           属性结束值
	 */
	public Query between(@NotNull final String propertyName, @NotNull final Date lo, @NotNull final Date go) {
		this.predicates.add(criteriaBuilder.between(from.get(propertyName), lo, go));
		return this;
	}

	public Query between(@NotNull final String propertyName, @NotNull final Number lo, @NotNull final Number go) {
		ge(propertyName, lo)
				.le(propertyName, go);
		return this;
	}

	/**
	 * 小于等于
	 *
	 * @param propertyName 属性名称
	 * @param value        属性值
	 */
	public Query le(@NotNull final String propertyName, @NotNull final Number value) {
		this.predicates.add(criteriaBuilder.le(from.get(propertyName), value));
		return this;
	}

	/**
	 * 小于
	 *
	 * @param propertyName 属性名称
	 * @param value        属性值
	 */
	public Query lt(@NotNull final String propertyName, @NotNull final Number value) {
		this.predicates.add(criteriaBuilder.lt(from.get(propertyName), value));
		return this;
	}

	/**
	 * 大于等于
	 *
	 * @param propertyName 属性名称
	 * @param value        属性值
	 */
	public Query ge(@NotNull final String propertyName, @NotNull final Number value) {
		this.predicates.add(criteriaBuilder.ge(from.get(propertyName), value));
		return this;
	}

	/**
	 * 大于
	 *
	 * @param propertyName 属性名称
	 * @param value        属性值
	 */
	public Query gt(@NotNull final String propertyName, @NotNull final Number value) {
		this.predicates.add(criteriaBuilder.gt(from.get(propertyName), value));
		return this;
	}

	/**
	 * 包含
	 *
	 * @param propertyName 属性名称
	 * @param value        值集合
	 */
	public Query in(@NotNull final String propertyName, @NotNull final Collection value) {
		Iterator iterator = value.iterator();
		In in = criteriaBuilder.in(from.get(propertyName));
		while (iterator.hasNext()) {
			in.value(iterator.next());
		}
		this.predicates.add(in);
		return this;
	}

	/**
	 * 不包含
	 *
	 * @param propertyName 属性名称
	 * @param value        值集合
	 */
	public Query notIn(@NotNull final String propertyName, @NotNull final Collection value) {
		Iterator iterator = value.iterator();
		In in = criteriaBuilder.in(from.get(propertyName));
		while (iterator.hasNext()) {
			in.value(iterator.next());
		}
		this.predicates.add(criteriaBuilder.not(in));
		return this;
	}

	/**
	 * 直接添加JPA内部的查询条件,
	 * 用于应付一些复杂查询的情况,例如或
	 */
	public Query addCriterions(Predicate predicate) {
		this.predicates.add(predicate);
		return this;
	}

	/**
	 * 创建查询条件
	 *
	 * @return JPA离线查询
	 */
	public CriteriaQuery createCriteriaQuery() {
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		if (!isNullOrEmpty(groupBy)) {
			criteriaQuery.groupBy(from.get(groupBy));
		}
		if (this.orders != null) {
			criteriaQuery.orderBy(orders);
		}
		addLinkCondition(this);
		return criteriaQuery;
	}

	private void addLinkCondition(Query query) {

		Map subQuery = query.linkQuery;
		if (subQuery == null)
			return;

		for (Iterator queryIterator = subQuery.keySet().iterator(); queryIterator.hasNext(); ) {
			String key = (String) queryIterator.next();
			Query sub = (Query) subQuery.get(key);
			from.join(key);
			criteriaQuery.where(sub.predicates.toArray(new Predicate[0]));
			addLinkCondition(sub);
		}
	}

	public Query addOrder(@NotNull final String propertyName, @NotNull final String order) {
		if (this.orders == null)
			this.orders = new ArrayList();

		if (order.equalsIgnoreCase("asc"))
			this.orders.add(criteriaBuilder.asc(from.get(propertyName)));
		else if (order.equalsIgnoreCase("desc"))
			this.orders.add(criteriaBuilder.desc(from.get(propertyName)));
		return this;
	}

	/**
	 * @param propertyName
	 * @param order        asc desc
	 */
	public void setOrder(@NotNull final String propertyName, @NotNull final String order) {
		this.orders = null;
		addOrder(propertyName, order);
	}

	/**
	 * 工具方法
	 *
	 * @param value
	 * @return
	 */
	private boolean isNullOrEmpty(Object value) {
		if (value instanceof String) {
			return "".equals(value);
		}
		return value == null;
	}

	public Class getModelClass() {
		return this.clazz;
	}

	public String getProjection() {
		return this.projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public Root getFrom() {
		return from;
	}

	public List<Predicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<Predicate> predicates) {
		this.predicates = predicates;
	}

	public CriteriaQuery getCriteriaQuery() {
		return criteriaQuery;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

	public void setFetchModes(List<String> fetchField, List<String> fetchMode) {

	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

}