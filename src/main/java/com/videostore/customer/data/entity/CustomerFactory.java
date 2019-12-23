package com.videostore.customer.data.entity;

import com.videostore.customer.service.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {

    public Customer createEntityFromDto(CustomerDto dto){
        Customer entity = new Customer();

        if(dto.getId() != null){
            entity.setId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setRegistrationDate(dto.getRegistrationDate());

        return entity;
    }

}
