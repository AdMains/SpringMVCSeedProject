package com.zhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Listener;


import com.zhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Service.ShiroSessionService;
import com.zhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Repository.CachingShiroSessionDao;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

@Slf4j
public class ShiroSessionListener implements SessionListener {

    @Setter
    private ShiroSessionService shiroSessionService;

    @Setter
    private CachingShiroSessionDao sessionDao;

    @Override
    public void onStart(Session session) {
        // 会话创建时触发
        log.info("session {} onStart", session.getId());
    }

    @Override
    public void onStop(Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        log.info("session {} onStop", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        log.info("session {} onExpiration", session.getId());
    }

}