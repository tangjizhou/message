package net.mshome.twisted.message.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.constant.Constants;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
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
    public void consume(Message message) {
        log.info("开始消费消息[{}]", message.getMessageProperties().getMessageId());
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);

        JSONObject messageJsonObject = JSON.parseObject(messageBody);
        MessageContext.Type type = MessageContext.Type.valueOf(messageJsonObject.getString("type"));
        String messageId = messageJsonObject.getString("messageId");

        messageHandlers.stream()
                .filter(messageHandler -> messageHandler.supportsMessageType(type))
                .forEach(messageHandler -> messageHandler.execute(messageBody));
        log.info("消息[{}]发送成功", messageId);
    }

}
