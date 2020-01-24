package net.mshome.twisted.message.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.constant.Constants;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.amqp.core.AcknowledgeMode;
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
    private List<MessageHandler> messageHandlers;

    @RabbitHandler
    public void handleMessage(byte[] message) {
        String messageBody = new String(message);
        JSONObject messageContext = JSON.parseObject(messageBody);

        MessageContext.Type type = MessageContext.Type.valueOf(messageContext.getString("type"));
        String messageId = messageContext.getString("messageId");
        messageHandlers.stream()
                .filter(messageHandler -> messageHandler.acquire(type))
                .forEach(messageHandler -> messageHandler.execute(messageBody));
        log.info("message {} resolved", messageId);
    }

}
