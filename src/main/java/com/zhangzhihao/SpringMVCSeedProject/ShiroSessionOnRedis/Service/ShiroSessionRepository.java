package com.zhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Service;


import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;


@SuppressWarnings("unused")
public interface ShiroSessionRepository {

    /**
     * 保存会话
     */
    void saveSession(Session session);

    /**
     * 更新会话
     */
    void updateSession(Session session);

    /**
     * 刷新缓存重新计算过期时间
     */
    void refreshSession(Serializable sessionId);

    /**
     * 删除会话
     */
    void deleteSession(Serializable sessionId);

    /**
     * 获取会话
     */
    Session getSession(Serializable sessionId);

    /**
     * 获取所有会话
     */
    Collection<Session> getAllSessions();
}
