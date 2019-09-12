package net.mshome.twisted.message.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * 邮件发送测试类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/12
 */
@SpringBootApplication
public class MessageSampleApplication implements CommandLineRunner {

    @Autowired
    private JavaMailSender mailSender;


    public static void main(String[] args) {
        SpringApplication.run(MessageSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("tangjizhouchn@qq.com");
        helper.setTo("tangjizhouchn@foxmail.com");
        helper.setSubject("测试邮件");
        helper.setText("测试邮件", true);
        mailSender.send(mimeMessage);
        System.out.println(123123);
    }

}
