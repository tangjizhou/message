package net.mshome.twisted.message.exception;

/**
 * 消息发送异常
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/19
 */
public class MessageSendingException extends RuntimeException {

    public MessageSendingException(String message) {
        super(message);
    }

    public MessageSendingException(String message, Throwable cause) {
        super(message, cause);
    }

}
