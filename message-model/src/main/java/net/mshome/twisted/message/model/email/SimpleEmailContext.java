package net.mshome.twisted.message.model.email;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.model.MessageType;

import java.util.List;

/**
 * 简单邮件消息体
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleEmailContext extends MessageContext {

    private List<String> cc;
    private List<String> bcc;

    @Builder
    public SimpleEmailContext(String from, List<String> to, String content, List<String> cc, List<String> bcc) {
        super(MessageType.SIMPLE_EMAIL, from, to, content);
        this.cc = cc;
        this.bcc = bcc;
    }

}
