package com.zhangzhihao.SpringMVCSeedProject.generic;

/**
 * 所有自定义Dao的顶级接口, 封装常用的增删查改操作,
 * 可以通过Mybatis Generator 插件自动生成Dao
 * Model : 代表数据库中的表 映射的Java对象类型
 * PK :代表对象的主键类型
 */
public interface GenericDao<Model, PK> {
}
