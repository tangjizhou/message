package net.mshome.twisted.message.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mshome.twisted.message.model.util.Assert;

import java.util.List;

/**
 * 短信发送
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SMSContext extends MessageContext {


    @Builder
    public SMSContext(List<String> to, String content) {
        super(to, content);
    }

    @Override
    public void validate() {
        Assert.notEmpty(this.to, "短信收件人不能为空");
        Assert.notBlank(this.content, "短信内容不能为空");
    }

    @Override
    public Type acquireMessageType() {
        return Type.SMS;
    }

}
