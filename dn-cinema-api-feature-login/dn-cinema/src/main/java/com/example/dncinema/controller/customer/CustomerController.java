package com.example.dncinema.controller.customer;

import com.example.dncinema.model.Customer;
import com.example.dncinema.model.Ticket;
import com.example.dncinema.service.customer.ICustomerService;
import com.example.dncinema.service.ticket.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/employee/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITicketService iTicketService;

    /**
     * @return a Page object containing the requested subset of customers.
     * The method used to display the list of members
     * @author ThanhNV
     * Retrieves a paginated list of customers.
     * <p>
     * This method is used to display the list of customers in a paginated manner. It returns a Page object containing
     * a subset of customers based on the provided pagination parameters.
     * <p>
     * the pagination parameters for retrieving the list of customers.
     */

    @GetMapping("")
    public ResponseEntity<List<Customer>> showAll(@RequestParam(defaultValue = "", required = false) String nameSearch) {

        List<Customer> customerList = customerService.findAllAndSearch(nameSearch);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }


    /**
     * Search and return customer information based on id.
     *
     * @param id
     * @return ResponseEntity<Customer>;
     * @author ThanhNV
     */

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findByCustomerId(@PathVariable Integer id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Created by: ThanhNV
     * Date created: 05/25/2023
     * <p>
     * Function: edit customer data if ID is not found then return HttpStatus.NOT_FOUND,
     * if found ID then edit data in DB and return HttpStatus.OK
     *
     * @return if has errors then return HttpStatus.Not_FOUND else ResponseEntity<>(HttpStatus.OK);
     * Phương thức sử dụng để chỉnh sửa danh sách thành viên
     */

    @PatchMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer.getNameCustomer(),
                customer.getPhone(), customer.getAddress(), customer.getEmail(), customer.getIdCustomer(), customer.getIdentityCard());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailCustomer(@PathVariable Integer id){
        Ticket ticket = iTicketService.findTicketById(id);
        Customer customer = customerService.findById(ticket.getCustomer().getIdCustomer());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}