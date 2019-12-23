package com.videostore;

import com.videostore.customer.data.entity.Customer;
import com.videostore.customer.service.CustomerService;
import com.videostore.customer.service.dto.CustomerDtoFactory;
import com.videostore.customer.service.dto.CustomerDtoImpl;
import com.videostore.customer.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Fast example of the hibernate create, update, delete methods
 * @author Abilene Franquez
 */
@Component
public class App {

    private static CustomerService customerService;

    private static CustomerDtoFactory dtoFactory;

    public App(
            @Autowired
            CustomerService pCustomerService,
            @Autowired
            CustomerDtoFactory pDtoFactory){
        customerService = pCustomerService;
        dtoFactory = pDtoFactory;
    }

    public static void main(String args[]) throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Hugo");
        customer.setAge(6);
        customer.setRegistrationDate("12/21/2019");

        CustomerDtoImpl customerDtoImpl = dtoFactory.createDtoFromEntity(customer);

        int custoId = customerService.createCustomer(customerDtoImpl);

        System.out.println("Customer Id:" + custoId);

    }
}
