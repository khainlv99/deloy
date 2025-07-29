package com.example.dncinema.service.customer.impl;

import com.example.dncinema.model.TypeCustomer;
import com.example.dncinema.repository.ICustomerTypeRepository;
import com.example.dncinema.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository customerTypeRepository;


    @Override
    public List<TypeCustomer> findAllTypeCustomer() {
        return customerTypeRepository.findAll();
    }
}
