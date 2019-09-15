package net.mshome.twisted.message.client.configuration;

import net.mshome.twisted.message.client.feign.MessageClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * 客户端配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/15
 */
@Configuration
@EnableFeignClients(clients = MessageClient.class)
public class FeignConfiguration {

}
