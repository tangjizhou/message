package net.mshome.twisted.message.configuration;

import net.mshome.twisted.message.exception.MessageSendingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Map;

/**
 * 重试机制
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/18
 */
@EnableRetry
@Configuration
public class RetryConfiguration {


    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        RetryPolicy retryPolicy = new SimpleRetryPolicy(5, Map.of(MessageSendingException.class, true));
        retryTemplate.setRetryPolicy(retryPolicy);
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(2 * 1000L);
        backOffPolicy.setMultiplier(2);
        backOffPolicy.setMaxInterval(30 * 1000L);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }

}
