package net.mshome.twisted.message.client;

import net.mshome.twisted.message.autoconfigure.MessageClientProperties;
import net.mshome.twisted.message.model.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 消息客户端
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/27
 */
public class MessageClient {

    @Autowired
    private MessageClientProperties properties;

    @Autowired
    private RestTemplate restTemplate;


    public void send(MessageContext messageContext) {
        messageContext.validate();
        restTemplate.postForEntity(properties.getRootUrl().concat(ApiDefinition.MESSAGE_SEND_URI), Void.class,
                String.class);
    }

}
