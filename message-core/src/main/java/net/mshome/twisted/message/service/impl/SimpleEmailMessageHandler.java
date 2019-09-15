package net.mshome.twisted.message.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.model.MessageType;
import net.mshome.twisted.message.model.email.SimpleEmailContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Optional;

/**
 * 简单邮件发送实现类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Slf4j
@Service
public class SimpleEmailMessageHandler implements MessageHandler {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;


    @Override
    public void execute(String content) {
        SimpleEmailContext context = JSON.parseObject(content, SimpleEmailContext.class);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.displayName());
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(Optional.ofNullable(context.getTo()).orElse(Collections.emptyList()).toArray(String[]::new));
            messageHelper.setSubject(context.getSubject());
            messageHelper.setText(context.getContent(), true);
            messageHelper.setBcc(Optional.ofNullable(context.getBcc()).orElse(Collections.emptyList()).toArray(String[]::new));
            messageHelper.setCc(Optional.ofNullable(context.getCc()).orElse(Collections.emptyList()).toArray(String[]::new));
            mailSender.send(message);

        } catch (MessagingException e) {
            log.error("email [{}] send failed", context.getMessageId(), e);
        }
    }

    @Override
    public boolean acquire(MessageType messageType) {
        return MessageType.SIMPLE_EMAIL == messageType;
    }

}
