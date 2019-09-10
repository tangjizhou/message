package net.mshome.twisted.message.model;

/**
 * 消息类别
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
public enum MessageType {
    /**
     * 简单邮件
     */
    SIMPLE_EMAIL,
    /**
     * 加密邮件
     */
    SECRET_EMAIL,
    /**
     * 短信
     */
    SMS;

    /**
     * 消息类型的字符串类别 {@link MessageType#name()}
     */
    public static class StringType {

        static final String SIMPLE_EMAIL = "SIMPLE_EMAIL";
        static final String SECRET_EMAIL = "SECRET_EMAIL";
        static final String SMS = "SMS";

    }

}
