package net.mshome.twisted.message.controller;

import net.mshome.twisted.message.model.MessageContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台控制器，供feign调用
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void send(@RequestBody MessageContext messageContext) {
        rabbitTemplate.convertAndSend("t_message", messageContext);
    }

}
