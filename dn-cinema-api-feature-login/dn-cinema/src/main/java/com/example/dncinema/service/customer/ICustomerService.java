package com.example.dncinema.service.customer;

import com.example.dncinema.dto.customerDTO.CustomerDTO;
import com.example.dncinema.model.AccountUser;
import com.example.dncinema.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import java.time.LocalDate;

public interface ICustomerService {

    Page<Customer> findAllCustomerTicket(Pageable pageable);

    Page<Customer> findAllCustomerPointHistory(Pageable pageable);

    Page<Customer> searchPlusPoint(Pageable pageable , LocalDate dateStart,LocalDate dateEnd);

    Page<Customer> searchUsePoint(Pageable pageable , LocalDate dateStart , LocalDate dateEnd);


    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * function: update customer
     *
     * @param customerDTO
     */

    void createCustomer(CustomerDTO customerDTO);
    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * function: create customer
     *
     * @param customerDTO
     * @param id
     */

    void updateRegisterCustomer(CustomerDTO customerDTO, Integer id);
    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * function: find customer by id
     *
     * @param id
     */
    Customer findById(int id);
    List<Customer> findAll();
    Customer findByCustomerId(Integer id);

    Boolean existByEmail(String email);
    Boolean existByPhone(String phone);
    Boolean existByIdentity(String identity);
    Customer findCustomerByEmail(String email);

    List<Customer> findAllAndSearch(String nameSearch);


    void updateCustomer(
            String nameCustomer,
            String phone,
            String address,
            String email,
            Integer idCustomer,
            String identityCard);
    Customer findCustomerByNameAccount(String nameAccount);
}
