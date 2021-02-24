package com.gwh.aes_test.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 */
public class ReflectionUtil {

    /**
     * 递归所有Field
     *
     * @param clazz
     * @return
     */
    public static List<Field> getFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        Class current = clazz;
        while (!current.getName().equals(Object.class.getName())) {
            getFields(fields, current);
            current = current.getSuperclass();
        }
        return fields;
    }

    private static void getFields(List<Field> fields, Class clazz) {
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
    }

    /**
     * 通过反射，设置指定对象中拥有指定注解的值
     */
    public static void setFieldValueByAnnotation(final Object obj, final Class annotation, final Object value) {
        List<Field> fields = getFields(obj.getClass());
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotation)) {
                // 存在此注解，赋值
                setFieldValue(obj, field.getName(), value);
            }
        }
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("没有找到 [" + fieldName + "] 属性。对象为 [" + obj + "]");
        }

        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
     * <p>
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getAccessibleField(final Object obj, final String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
     */
    public static void makeAccessible(Field field) {
        boolean flag = (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                || Modifier.isFinal(field.getModifiers())) && !field.isAccessible();
        if (flag) {
            field.setAccessible(true);
        }
    }

}
