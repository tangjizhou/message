package net.mshome.twisted.message.configuration;

import net.mshome.twisted.message.constant.Constants;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

/**
 * 队列配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Configuration
public class RabbitConfiguration {

    @Autowired
    private RetryTemplate retryTemplate;


    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setRetryTemplate(retryTemplate);
        return factory;
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(Constants.queueName, true);
    }

}
