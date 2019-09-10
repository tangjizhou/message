package net.mshome.twisted.message.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.model.MessageType;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Service
public class SimpleEmailMessageHandler implements MessageHandler {

    @Override
    public void execute(String content) {

    }

    @Override
    public boolean acquire(MessageType messageType) {
        return MessageType.SIMPLE_EMAIL == messageType;
    }

}
