package net.mshome.twisted.message;

import com.alibaba.fastjson.JSON;
import net.mshome.twisted.message.model.SimpleEmailContext;
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

        SimpleEmailContext context = SimpleEmailContext.builder().subject("测试邮件")
                .to(List.of("tangjizhouchn@foxmail.com"))
                .content("你好").build();

        String body = JSON.toJSONString(context);
        rabbitTemplate.convertAndSend("t_message", body.getBytes());
    }

}
