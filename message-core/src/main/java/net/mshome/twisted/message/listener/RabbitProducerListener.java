package net.mshome.twisted.message.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 队列监听
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Component
public class RabbitProducerListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info("收到消息[{}]", message.getMessageProperties().getMessageId());
    }


}
