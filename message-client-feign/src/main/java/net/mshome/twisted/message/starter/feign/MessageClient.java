package net.mshome.twisted.message.starter.feign;

import net.mshome.twisted.message.starter.constant.ApiConstant;
import net.mshome.twisted.message.model.MessageContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 短信发送客户端
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@FeignClient(path = ApiConstant.APP_ROOT_PATH, name = ApiConstant.APP_NAME)
public interface MessageClient {

    /**
     * 消息发送接口
     *
     * @param messageContext 消息体
     */
    @PostMapping(ApiConstant.API_PATH)
    void send(@RequestBody MessageContext messageContext);


}
