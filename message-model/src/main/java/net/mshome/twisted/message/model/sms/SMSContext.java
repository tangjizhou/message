package net.mshome.twisted.message.model.sms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mshome.twisted.message.model.MessageContext;
import net.mshome.twisted.message.model.MessageType;

import java.util.List;

/**
 * 短信发送
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SMSContext extends MessageContext {


    public SMSContext(MessageType messageType, String from, List<String> to, String content) {
        super(messageType, from, to, content);
    }

}
