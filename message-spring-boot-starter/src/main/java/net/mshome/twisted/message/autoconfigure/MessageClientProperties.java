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
    private String rootUrl = "http://message.cad-t4/message";


}
