package net.mshome.twisted.message.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/27
 */
@Data
@ConfigurationProperties(prefix = "message.service")
public class MessageClientProperties {

    /**
     * 服务路径
     */
    private String rootUrl = "http://127.0.0.1:3310/message";
    /**
     * 服务servlet-path
     */
    private String servletUri = "/api/v1/send";


}
