package com.gwh.aes_test.service;

import com.gwh.aes_test.dao.CustomerDao;
import com.gwh.aes_test.entity.TestCustomer;
import com.gwh.aes_test.handler.Base64Handler;
import com.gwh.aes_test.vo.CustomerVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 保存客户信息
     * @param testCustomer
     * @return
     */
    public TestCustomer save(TestCustomer testCustomer){
        Base64Handler.encryptBase64Handler(testCustomer);
        TestCustomer customer = this.customerDao.save(testCustomer);
        return customer;
    }

    /**
     * 根据客户Id查询客户信息
     * @param id
     * @return
     */
    public CustomerVo findById(Integer id){
        CustomerVo customerVo=new CustomerVo();
        Optional<TestCustomer> testCustomer = this.customerDao.findById(id);
        if(testCustomer.isPresent()){
            BeanUtils.copyProperties(testCustomer.get(),customerVo);
            Base64Handler.encryptBase64Handler(customerVo);
        }
        return customerVo;
    }

}
