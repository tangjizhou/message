package net.mshome.twisted.message.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 消息体父类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleEmailContext.class, name = MessageContext.Type.StringType.SIMPLE_EMAIL),
        @JsonSubTypes.Type(value = SMSContext.class, name = MessageContext.Type.StringType.SMS)
})
public abstract class MessageContext {

    /**
     * 消息类型
     */
    protected Type type;

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

    public abstract void validate();

    public abstract Type acquireMessageType();

    public List<String> getTo() {
        return Optional.ofNullable(to).orElse(Collections.emptyList());
    }

    public MessageContext(List<String> to, String content) {
        this.type = acquireMessageType();
        this.to = to;
        this.content = content;
    }

    public enum Type {
        /**
         * 简单邮件
         */
        SIMPLE_EMAIL,
        /**
         * 短信
         */
        SMS;

        /**
         * 消息类型的字符串类别 {@link Type#name()}
         */
        public static class StringType {

            static final String SIMPLE_EMAIL = "SIMPLE_EMAIL";
            static final String SMS = "SMS";

        }

    }


}
