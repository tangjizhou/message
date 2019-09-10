package net.mshome.twisted.message;

import net.mshome.twisted.message.model.email.SimpleEmailContext;
import net.mshome.twisted.message.service.impl.SimpleEmailMessageHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageCoreApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {

        SimpleEmailContext context = SimpleEmailContext.builder().from("tangjizhou").to(List.of("tangjizhou"))
                .content("你好").build();
        rabbitTemplate.convertAndSend("t_message", context);
    }

}
