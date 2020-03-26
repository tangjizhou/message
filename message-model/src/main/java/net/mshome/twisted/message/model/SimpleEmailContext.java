package net.mshome.twisted.message.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mshome.twisted.message.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 简单邮件消息体
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleEmailContext extends MessageContext {

    private String subject;
    private List<String> cc;
    private List<String> bcc;
    private List<Attachment> attachments;

    @Builder
    public SimpleEmailContext(List<String> to, String content, String subject, List<String> cc, List<String> bcc) {
        super(to, content);
        this.subject = subject;
        this.cc = cc;
        this.bcc = bcc;
    }

    public List<String> getCc() {
        return Optional.ofNullable(this.cc).orElse(Collections.emptyList());
    }

    public List<String> getBcc() {
        return Optional.ofNullable(this.bcc).orElse(Collections.emptyList());
    }

    public List<Attachment> getAttachments() {
        return Optional.ofNullable(this.attachments).orElse(Collections.emptyList());
    }

    @Override
    public void validate() {
        Assert.notBlank(this.subject, "邮件主题不能为空");
        Assert.notEmpty(this.to, "邮件收件人不能为空");
    }

    @Override
    public Type acquireMessageType() {
        return Type.SIMPLE_EMAIL;
    }

    @Data
    public static class Attachment {

        private String fileName;
        private String url;

    }

}
