package net.mshome.twisted.message.autoconfigure;

import net.mshome.twisted.message.starter.MessageClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 消息服务自动配置类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/27
 */
@Configuration
@EnableConfigurationProperties(MessageClientProperties.class)
public class MessageClientAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public MessageClient messageClient() {
        return new MessageClient();
    }


    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().setReadTimeout(Duration.of(20, ChronoUnit.SECONDS))
                .setConnectTimeout(Duration.of(25, ChronoUnit.SECONDS)).build();

    }


}
