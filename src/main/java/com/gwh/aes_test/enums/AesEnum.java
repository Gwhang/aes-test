package com.gwh.aes_test.enums;

/**
 * 注解对应的枚举
 */
public enum AesEnum {
    // 加密
    ENCRYPTION("01"),
    // 解密
    DECRYPT("02"),
    // 脱敏
    DESENSITIZATION("03"),
    ;
    private String code;

    AesEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static AesEnum getAesEnum(String code){
        for (AesEnum e:AesEnum.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

}
