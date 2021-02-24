package com.gwh.aes_test.service;

import com.alibaba.fastjson.JSONObject;
import com.gwh.aes_test.entity.TestCustomer;
import com.gwh.aes_test.vo.CustomerVo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void save() {
        TestCustomer testCustomer=new TestCustomer();
        testCustomer.setName("李四");
        testCustomer.setIdNumber("410381198407155412");
        testCustomer.setPhone("15236770399");
        testCustomer.setCreateDate(new Date());
        testCustomer.setUpdateDate(new Date());
        TestCustomer customer = customerService.save(testCustomer);
        Assert.assertNotNull(customer);
        System.out.println(JSONObject.toJSONString(customer));
    }

    @Test
    void findById() {
        CustomerVo customerVo = this.customerService.findById(2);
        Assert.assertNotNull(customerVo);
        System.out.println(JSONObject.toJSONString(customerVo));

    }
}