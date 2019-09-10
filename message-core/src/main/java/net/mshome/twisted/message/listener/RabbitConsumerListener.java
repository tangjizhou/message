package net.mshome.twisted.message.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息处理类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Component
@RabbitListener(queues = "t_message")
public class RabbitConsumerListener {

    @Autowired
    private List<MessageHandler> messageHandlerList;

    @RabbitHandler
    public void handleMessage(Message message) {
        String messageContent = new String(message.getBody());
        MessageContext messageContext = JSON.parseObject(messageContent, MessageContext.class);
        try {
            messageHandlerList.stream()
                    .filter(messageHandler -> messageHandler.acquire(messageContext.getMessageType()))
                    .forEach(messageHandler -> messageHandler.execute(messageContent));
            log.info("message {} resolved", messageContext.getMessageId());
        } catch (Exception e) {
            log.error("message {} resolve failed", messageContext.getMessageId(), e);
            // fixme 放入延时队列
        }
    }

}
