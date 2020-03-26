package net.mshome.twisted.message.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 校验工具类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/18
 */
@UtilityClass
public class Assert {

    public static void notBlank(String value, String message) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> value, String message) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

}
