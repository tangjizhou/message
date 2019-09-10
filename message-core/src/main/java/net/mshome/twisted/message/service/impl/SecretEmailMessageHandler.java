package net.mshome.twisted.message.service.impl;

import net.mshome.twisted.message.model.MessageType;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.stereotype.Service;

/**
 * 加密邮件处理器
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Service
public class SecretEmailMessageHandler implements MessageHandler {

    @Override
    public void execute(String content) {

    }

    @Override
    public boolean acquire(MessageType messageType) {
        return MessageType.SECRET_EMAIL == messageType;
    }

}
