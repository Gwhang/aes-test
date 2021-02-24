package com.gwh.aes_test.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.gwh.aes_test.annotation.AesAnnotation;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "test_customer")
public class TestCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @AesAnnotation(ENCRYPTION = true)
    private String name;
    @AesAnnotation(ENCRYPTION = true)
    private String idNumber;
    @AesAnnotation(ENCRYPTION = true)
    private String phone;

    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd")
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }



}
