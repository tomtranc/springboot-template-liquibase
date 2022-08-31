package com.ttran.example.service;

import com.ttran.example.model.dto.Customer;
import com.ttran.example.model.entity.CustomerEntity;
import com.ttran.example.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> list() {
        return customerRepo.findAll().stream()
                .map(this::getCustomerEntityToDto)
                .collect(Collectors.toList());
    }

    public Customer create(Customer dto) {
        return getCustomerEntityToDto(customerRepo.save(getCustomerDtoToEntity(dto)));
    }

    public Customer update(Customer dto) {
        return getCustomerEntityToDto(customerRepo.save(getCustomerDtoToEntity(dto)));
    }

    public void delete(String id) {
        customerRepo.deleteById(id);
    }

    private Customer getCustomerEntityToDto(CustomerEntity entity) {
        Customer dto = new Customer();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private CustomerEntity getCustomerDtoToEntity(Customer dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
