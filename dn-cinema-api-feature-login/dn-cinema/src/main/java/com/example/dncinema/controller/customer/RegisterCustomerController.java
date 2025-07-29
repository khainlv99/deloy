package com.example.dncinema.controller.customer;

import com.example.dncinema.dto.accounUserDTO.AccountUserDTO;
import com.example.dncinema.dto.customerDTO.CustomerDTO;
import com.example.dncinema.model.AccountUser;
import com.example.dncinema.model.Customer;
import com.example.dncinema.repository.IAccountUserRepository;
import com.example.dncinema.security.response.ResponseMessage;
import com.example.dncinema.service.accountUser.IAccountUserService;
import com.example.dncinema.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RegisterCustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountUserService accountUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAccountUserRepository iAccountUserRepository;


    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * Function: add data customer  into Database
     *
     * @param customerDTO
     * @return
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/public/create")
    public ResponseEntity<?> createCustomerAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        if (accountUserService.existByNameAccount(customerDTO.getAccountUser().getNameAccount())) {
            return new ResponseEntity<>(new ResponseMessage("The account existed !!, Try again"), HttpStatus.OK);
        }
        if (customerService.existByEmail(customerDTO.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("The email existed!!, Try again"), HttpStatus.OK);
        }
        customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * Function: Update data customer  into Database
     *
     * @param customerDTO
     * @param id
     * @param bindingResult
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/user/update/{id}")
    public ResponseEntity<?> updateCustomerAccount(@Valid @RequestBody CustomerDTO customerDTO,
                                                   @PathVariable("id") Integer id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.findById(id);
        BeanUtils.copyProperties(customerDTO, customer);
        customerService.updateRegisterCustomer(customerDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/user/find-account/{id}")
    public ResponseEntity<Customer> findByCustomerId(@PathVariable Integer id) {
        Customer customer = customerService.findByCustomerId(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/public/check-account/{nameAccount}")
    public ResponseEntity<Boolean> checkAccountExistence(@PathVariable("nameAccount") String nameAccount) {
        boolean exists = accountUserService.existByNameAccount(nameAccount);
        return ResponseEntity.ok(exists);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/public/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExistence(@PathVariable("email") String email) {
        boolean exists = customerService.existByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/public/check-identity/{identity}")
    public ResponseEntity<Boolean> checkIdentityExistence(@PathVariable("identity") String identity) {
        boolean exists = customerService.existByIdentity(identity);
        return ResponseEntity.ok(exists);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/public/check-phone/{phone}")
    public ResponseEntity<Boolean> checkPhoneExistence(@PathVariable("phone") String phone) {
        boolean exists = customerService.existByPhone(phone);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/public/find-customer")
    public ResponseEntity<?> findCustomerByNameAccount(@RequestParam String name) {
        Customer customer = customerService.findCustomerByNameAccount(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}


