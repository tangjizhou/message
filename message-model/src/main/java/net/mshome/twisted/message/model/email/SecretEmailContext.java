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

    @Builder
    public SecretEmailContext(MessageType messageType, String from, List<String> to, String content) {
        super(messageType, from, to, content);
    }

}
