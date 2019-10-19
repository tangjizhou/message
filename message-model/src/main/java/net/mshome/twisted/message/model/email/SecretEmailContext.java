package net.mshome.twisted.message.model.email;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.model.MessageType;

import java.util.List;

/**
 * 加密邮件
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecretEmailContext extends MessageContext {

    private String subject;
    private List<String> cc;
    private List<String> bcc;

    @Builder
    public SecretEmailContext(String from, List<String> to, String content,
                              String subject, List<String> cc, List<String> bcc) {
        super(MessageType.SECRET_EMAIL, from, to, content);
        this.subject = subject;
        this.cc = cc;
        this.bcc = bcc;
    }

}
