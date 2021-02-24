package com.gwh.aes_test.handler;

import com.gwh.aes_test.annotation.AesAnnotation;
import com.gwh.aes_test.enums.AesEnum;
import com.gwh.aes_test.utils.AesUtil;
import com.gwh.aes_test.utils.ReflectionUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * base64 加解密
 */
public class Base64Handler {


    /**
     * 批量加解密
     * @param objectList
     */
    public static void encryptBase64HandlerList(List<Object> objectList){
        for (Object o:objectList){
            encryptBase64Handler(o);
        }
    }

    /**
     * 加密
     * @param obj 要加密的实体
     */
    public static void encryptBase64Handler(Object obj){
        //获取对象Class
        Class<?> objClass = obj.getClass();
        //获取属性字段
        List<Field> fields = ReflectionUtil.getFields(objClass);
        //设置注解值
        getAllAesField(fields,obj);

    }


    /**
     * 设置有注解的属性的值
     *
     * @param fields
     */
    private static void getAllAesField(List<Field> fields,Object obj) {
        for (Field field : fields) {
            AesAnnotation aesAnnotation = field.getAnnotation(AesAnnotation.class);
            if (aesAnnotation != null) {
                // 有注解，根据注解中的属性值设置字段的值
                setFieldAes(field.getName(),obj,aesAnnotation);
            }
        }
    }

    /**
     * 设置属性值
     * @param fieldName
     * @param obj
     * @param aesAnnotation
     */
    private static void setFieldAes(String fieldName,Object obj,AesAnnotation aesAnnotation){
        // 是否加密
        boolean encryption = aesAnnotation.ENCRYPTION();
        // 是否解密
        boolean decrypt = aesAnnotation.DECRYPT();
        // 是否脱敏
        boolean desensitization = aesAnnotation.DESENSITIZATION();
        // 脱敏样式
        String style = aesAnnotation.STYLE();
        if(encryption){
            setByFiled(fieldName,obj, AesEnum.ENCRYPTION.getCode(),style);
        }
        if(decrypt){
            setByFiled(fieldName,obj,AesEnum.DECRYPT.getCode(),style);
            if(desensitization){
                setByFiled(fieldName,obj,AesEnum.DESENSITIZATION.getCode(), style);
            }
        }


    }

    /**
     * 字段加解密
     * @param fieldName 字段名称
     * @param obj 实体
     * @param code 操作类型
     * @param style 脱敏样式
     */
    private static void setByFiled(String fieldName,Object obj,String code,String style){
        Field field = ReflectionUtil.getAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("没有找到 [" + fieldName + "] 属性。对象为 [" + obj + "]");
        }
        try {
            switch (AesEnum.getAesEnum(code)){
                case ENCRYPTION:
                    field.set(obj, AesUtil.encryptBase64(String.valueOf(field.get(obj))));
                    break;
                case DECRYPT:
                    field.set(obj,AesUtil.decryptBase64(String.valueOf(field.get(obj))));
                    break;
                case DESENSITIZATION:
                    field.set(obj,desensitizationByFiled(String.valueOf(field.get(obj)),style));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 脱敏
     * @param fieldvalue
     * @return
     */
    private static String desensitizationByFiled(String fieldvalue,String style){
        if (StringUtils.isEmpty(fieldvalue)) {
            return "";
        }
        return fieldvalue.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", style);
    }

}
