package net.mshome.twisted.message.controller;

import com.alibaba.fastjson.JSON;
import net.mshome.twisted.message.constant.Constants;
import net.mshome.twisted.message.model.MessageContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对外暴露的rest接口
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@RestController
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void send(@RequestBody MessageContext messageContext) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(messageContext.getMessageId());
        messageProperties.setConsumerQueue(Constants.queueName);
        Message message = new Message(JSON.toJSONBytes(messageContext), messageProperties);
        rabbitTemplate.send(message);
    }

    @GetMapping("/health")
    public String health() {
        rabbitTemplate.convertAndSend("health-check");
        return "UP";
    }

}
