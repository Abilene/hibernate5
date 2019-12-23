package com.videostore.customer.service.impl;

import com.videostore.customer.data.entity.Customer;
import com.videostore.customer.data.entity.CustomerFactory;
import com.videostore.customer.data.repository.CustomerRepository;
import com.videostore.customer.service.CustomerService;
import com.videostore.customer.service.dto.CustomerDto;
import com.videostore.customer.service.dto.CustomerDtoFactory;
import com.videostore.customer.service.dto.CustomerDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerFactory entityFactory;

    @Autowired
    private CustomerDtoFactory dtoFactory;

    //@Override
    public List<CustomerDto> findAll() {

        final List<CustomerDto> customerDtos = new ArrayList<>();

        System.out.println("Started FIND operation for CUSTOMER");
        try{
            Iterable<Customer> foundCustomers = repository.findAll();


            for (Customer customer : foundCustomers) {

                CustomerDtoImpl dto = dtoFactory.createDtoFromEntity(customer);
                customerDtos.add(dto);
            }

        } catch(DataAccessException ex) {
            System.out.println("Error processing the FIND operation for CUSTOMER, error: "+ex.getMostSpecificCause().getMessage());
        }
        System.out.println("Finished FIND operation for CUSTOMER");


        return customerDtos;
    }


    public Integer createCustomer(CustomerDtoImpl customer)
            throws Exception {
        Integer customerId = null;

        System.out.println("Started CREATE operation for CUSTOMER");
        try {
            Customer newCustomer = entityFactory.createEntityFromDto(customer);
            Customer savedCustomer = repository.save(newCustomer);
            customerId = savedCustomer.getId();

        } catch (DataAccessException ex) {
            System.out.println("Error processing the CREATE operation for CUSTOMER, error: "
                    + ex.getMostSpecificCause().getMessage());
            throw new Exception(
                    "Error Processing CREATE operation: " + ex.getMostSpecificCause().getMessage());
        }
        System.out.println("Finished CREATE operation for CUSTOMER");

        return customerId;
    }

    @Override
    public Integer updateCustomer(CustomerDtoImpl customer) throws Exception {
        Customer updatedCustomer = null;

        System.out.println("Started UPDATE operation for CUSTOMER");
        try {
            Customer newestCustomer = entityFactory.createEntityFromDto(customer);
            Customer oldestCustomer = repository.findById(customer.getId()).orElse(null);

            if (oldestCustomer == null) {
                throw new Exception(
                        "Error processing the UPDATE operation for CUSTOMER, error: Record not found");
            }

            newestCustomer.setId(oldestCustomer.getId());
            updatedCustomer = repository.save(newestCustomer);

        } catch (DataAccessException ex) {
            System.out.println("Error processing the UPDATE operation for CUSTOMER, error: "
                    + ex.getMostSpecificCause().getMessage());
            throw new Exception(
                    "Error Processing UPDATE operation: " + ex.getMostSpecificCause().getMessage());
        }
        System.out.println("Finished UPDATE operation for CUSTOMER");

        return updatedCustomer.getId();
    }

    @Override
    public CustomerDto findCustomer(Integer id) {
        Customer foundCustomer = null;

        System.out.println("Started FIND operation for CUSTOMER");
        try {
            foundCustomer = repository.findById(id).orElse(null);
        } catch (DataAccessException ex) {
            System.out.println("Error processing the FIND operation for CUSTOMER, error: "
                    + ex.getMostSpecificCause().getMessage());
        }
        System.out.println("Finished FIND operation for CUSTOMER");

        return dtoFactory.createDtoFromEntity(foundCustomer);
    }

    public void deleteCustomer(Integer id)
            throws Exception {
        Customer deletableCustomer = null;

        System.out.println("Started DELETE operation for CUSTOMER");
        try {
            deletableCustomer = repository.findById(id).orElse(null);
            if(deletableCustomer == null) {
                throw new Exception("Error processing the DELETE operation for CUSTOMER, error: Record not found");
            }
            repository.delete(deletableCustomer);

        } catch (DataAccessException ex) {
            System.out.println("Error processing the DELETE operation for CUSTOMER, error: "
                    + ex.getMostSpecificCause().getMessage());
            throw new Exception(
                    "Error Processing DELETE operation: " + ex.getMostSpecificCause().getMessage());
        }
        System.out.println("Finished DELETE operation for CUSTOMER");

    }

}
