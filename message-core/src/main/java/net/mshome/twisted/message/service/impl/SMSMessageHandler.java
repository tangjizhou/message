package net.mshome.twisted.message.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.stereotype.Service;

/**
 * 短信发送服务
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Service
public class SMSMessageHandler implements MessageHandler {

    @Override
    public void execute(String content) {
        // todo 短信，没钱不支持
    }

    @Override
    public boolean supportsMessageType(MessageContext.Type messageType) {
        return MessageContext.Type.SMS == messageType;
    }

}
