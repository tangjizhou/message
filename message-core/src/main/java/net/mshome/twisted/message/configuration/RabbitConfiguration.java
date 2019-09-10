package net.mshome.twisted.message.configuration;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Configuration
public class RabbitConfiguration {

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    //@Bean
    //public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
    //    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    //    factory.setConnectionFactory(connectionFactory);
    //    factory.setMessageConverter(new MessageConverter() {
    //        @Override
    //        public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
    //            return null;
    //        }
    //
    //        @Override
    //        public Object fromMessage(Message message) throws MessageConversionException {
    //            try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(message.getBody()))){
    //                return ois.readObject();
    //            }catch (Exception e){
    //                e.printStackTrace();
    //                return null;
    //            }
    //        }
    //    });
    //
    //    return factory;
    //}

}
