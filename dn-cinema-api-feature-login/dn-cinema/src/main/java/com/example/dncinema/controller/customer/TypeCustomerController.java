package com.example.dncinema.controller.customer;

import com.example.dncinema.model.TypeCustomer;
import com.example.dncinema.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/type")
@CrossOrigin("*")
public class TypeCustomerController {
    @Autowired
    private ICustomerTypeService customerTypeService;

    @GetMapping("")
    public ResponseEntity<List<TypeCustomer>> showList() {
        List<TypeCustomer> customerTypeList = customerTypeService.findAllTypeCustomer();
        if (customerTypeList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customerTypeList, HttpStatus.OK);
        }
    }
}