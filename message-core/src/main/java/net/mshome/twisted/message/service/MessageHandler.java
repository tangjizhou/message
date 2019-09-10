package net.mshome.twisted.message.service;

import net.mshome.twisted.message.model.MessageType;

/**
 * 消息处理接口
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
public interface MessageHandler {

    /**
     * 执行发送消息
     *
     * @param content 消息体
     */
    void execute(String content);

    /**
     * 判断是否支持当前消息类型
     *
     * @param messageType 消息类型
     * @return 是否可以处理当前消息类型
     */
    boolean acquire(MessageType messageType);


}
