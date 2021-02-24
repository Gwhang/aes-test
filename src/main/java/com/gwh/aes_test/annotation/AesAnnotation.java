package com.gwh.aes_test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Aes加解密方法
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AesAnnotation {

    /**
     * 加密
     * @return
     */
    boolean ENCRYPTION() default false;

    /**
     * 解密
     * @return
     */
    boolean DECRYPT() default false;

    /**
     * 脱敏
     * @return
     */
    boolean DESENSITIZATION() default false;

    /**
     * 脱敏样式
     * @return
     */
    String STYLE() default "*";


}
