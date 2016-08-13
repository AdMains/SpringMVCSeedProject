package com.zhangzhihao.SpringMVCSeedProject.ShiroSessionOnRedis.Service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;


public abstract class ShiroSessionMessageListener implements MessageListener {

    @Override
    public final void onMessage(Message message, byte[] bytes) {

        onMessage(new ShiroSessionMessage(message.getChannel(),message.getBody()));

    }

    public abstract void onMessage(ShiroSessionMessage message) ;

}
