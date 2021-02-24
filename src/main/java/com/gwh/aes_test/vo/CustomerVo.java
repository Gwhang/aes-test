package com.gwh.aes_test.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.gwh.aes_test.annotation.AesAnnotation;

import java.util.Date;

public class CustomerVo {

    @AesAnnotation(DECRYPT = true,DESENSITIZATION = true,STYLE = "*")
    private String name;
    @AesAnnotation(DECRYPT = true,DESENSITIZATION = true,STYLE = "*")
    private String idNumber;
    @AesAnnotation(DECRYPT = true,DESENSITIZATION = true,STYLE = "*")
    private String phone;

    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
