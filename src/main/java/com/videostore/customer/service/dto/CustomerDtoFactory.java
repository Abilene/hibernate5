package com.videostore.customer.service.dto;

import com.videostore.customer.data.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoFactory {

    public CustomerDtoImpl createDtoFromEntity(Customer entity){

        return new CustomerDtoImpl.Builder(entity.getName()).id(entity.getId())
                .age(entity.getAge()).registrationDate(entity.getRegistrationDate()).build();
    }

}
