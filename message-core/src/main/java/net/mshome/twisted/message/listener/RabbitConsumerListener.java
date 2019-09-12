package net.mshome.twisted.message.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.constant.Constants;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消费者
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Component
@RabbitListener(queues = Constants.queueName)
public class RabbitConsumerListener {

    @Autowired
    private List<MessageHandler> messageHandlerList;

    @RabbitHandler
    public void handleMessage(byte[] message) {
        String messageContent = new String(message);
        MessageContext messageContext = JSON.parseObject(messageContent, MessageContext.class, Feature.IgnoreNotMatch);
        try {
            messageHandlerList.stream()
                    .filter(messageHandler -> messageHandler.acquire(messageContext.getMessageType()))
                    .forEach(messageHandler -> messageHandler.execute(messageContent));
            log.info("message {} resolved", messageContext.getMessageId());
        } catch (Exception e) {
            log.error("message {} resolve failed", messageContext.getMessageId(), e);
        }
    }

}
