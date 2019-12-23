package com.videostore.customer.service;

import com.videostore.customer.service.dto.CustomerDto;
import com.videostore.customer.service.dto.CustomerDtoImpl;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    Integer createCustomer(CustomerDtoImpl customer) throws Exception;

    Integer updateCustomer(CustomerDtoImpl customer) throws Exception;

    CustomerDto findCustomer(Integer id);

    void deleteCustomer(Integer id) throws Exception;

}
