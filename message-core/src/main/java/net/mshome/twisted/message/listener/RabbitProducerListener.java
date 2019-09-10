package net.mshome.twisted.message.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Component
public class RabbitProducerListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info("message listener: {}", new String(message.getBody()));
    }

    @Override
    public void containerAckMode(AcknowledgeMode mode) {

    }

}
