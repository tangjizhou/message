package net.mshome.twisted.message.service;

import net.mshome.twisted.message.exception.MessageSendingException;
import net.mshome.twisted.message.model.MessageContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

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
    @Retryable(maxAttempts = 5, value = MessageSendingException.class,
            backoff = @Backoff(delay = 2 * 1000L, multiplier = 2.0, maxDelay = 30 * 1000L))
    void execute(String content);

    /**
     * 判断是否支持当前消息类型
     *
     * @param messageType 消息类型
     * @return 是否可以处理当前消息类型
     */
    boolean acquire(MessageContext.Type messageType);


}
