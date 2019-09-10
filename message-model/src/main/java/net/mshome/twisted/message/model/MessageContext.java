package net.mshome.twisted.message.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * 消息体父类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Data
public class MessageContext {

    /**
     * 消息类型
     */
    protected MessageType messageType;
    /**
     * 消息发送者
     */
    protected String from;
    /**
     * 消息接受者
     */
    protected List<String> to;
    /**
     * 消息内容
     */
    protected String content;
    /**
     * 消息ID
     */
    private String messageId = UUID.randomUUID().toString();

    public MessageContext(MessageType messageType, String from, List<String> to, String content) {
        this.messageType = messageType;
        this.from = from;
        this.to = to;
        this.content = content;
    }

}
