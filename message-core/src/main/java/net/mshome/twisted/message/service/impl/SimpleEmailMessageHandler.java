package net.mshome.twisted.message.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.model.SimpleEmailContext;
import net.mshome.twisted.message.service.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.MalformedURLException;
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

    @Autowired
    private RetryTemplate retryTemplate;

    @Override
    public void execute(String content) {
        SimpleEmailContext context = JSON.parseObject(content, SimpleEmailContext.class);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.displayName());
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(context.getTo().toArray(String[]::new));
            messageHelper.setSubject(context.getSubject());
            messageHelper.setText(context.getContent(), true);
            messageHelper.setBcc(context.getBcc().toArray(String[]::new));
            messageHelper.setCc(context.getCc().toArray(String[]::new));
            context.getAttachments().forEach(attachment -> {
                try {
                    messageHelper.addAttachment(attachment.getFileName(), new FileUrlResource(attachment.getUrl()));
                } catch (MessagingException | MalformedURLException e) {
                    log.error("消息[{}]添加附件失败", context.getMessageId(), e);
                }
            });
            sendWithRetry(message);
        } catch (MessagingException e) {
            log.error("邮件发送失败[{}]", context.getMessageId(), e);
        }
    }

    private void sendWithRetry(MimeMessage message) {
        retryTemplate.execute(retryContext -> {
            mailSender.send(message);
            return null;
        }, fail -> {
            log.error("邮件发送失败", fail.getLastThrowable());
            return null;
        });

    }

    @Override
    public boolean supportsMessageType(MessageContext.Type messageType) {
        return MessageContext.Type.SIMPLE_EMAIL == messageType;
    }

}
