package com.zhangzhihao.SpringMVCSeedProject.Dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDao {
	static Session session;
	static Transaction transaction;
}
