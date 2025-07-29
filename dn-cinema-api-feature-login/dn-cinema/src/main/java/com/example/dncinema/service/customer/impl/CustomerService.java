package com.example.dncinema.service.customer.impl;

import com.example.dncinema.dto.customerDTO.CustomerDTO;
import com.example.dncinema.model.AccountUser;
import com.example.dncinema.model.Customer;
import com.example.dncinema.model.Roles;
import com.example.dncinema.repository.IAccountUserRepository;
import com.example.dncinema.repository.ICustomerRepository;
import com.example.dncinema.repository.IRolesRepository;
import com.example.dncinema.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private IAccountUserRepository iAccountUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRolesRepository rolesRepository;
    @Override
    public Page<Customer> findAllCustomerTicket(Pageable pageable) {
        return iCustomerRepository.findAllCustomerTicket(pageable);
    }

    @Override
    public Page<Customer> findAllCustomerPointHistory(Pageable pageable) {
        return iCustomerRepository.findAllCustomerPointHistory(pageable);
    }

    @Override
    public Page<Customer> searchPlusPoint(Pageable pageable, LocalDate dateStart, LocalDate dateEnd) {
        return iCustomerRepository.findAllPlusPoint(pageable,dateStart,dateEnd);
    }

    @Override
    public Page<Customer> searchUsePoint(Pageable pageable, LocalDate dateStart, LocalDate dateEnd) {
        return iCustomerRepository.findAllUsePoint(pageable,dateStart,dateEnd);
    }

    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * function: create customer
     *
     * @param customerDTO
     */
    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        iAccountUserRepository.createAccountUser(customerDTO.getAccountUser().getNameAccount(),
                passwordEncoder.encode(customerDTO.getAccountUser().getPasswordAccount()));
        AccountUser accountUser = iAccountUserRepository
                .findAccountUserByNameAccount(customerDTO.getAccountUser().getNameAccount());
        Set<Roles> roles = new HashSet<>();
        roles.add(rolesRepository.findByNameRoles("USER"));
        accountUser.setRoles(roles);
        Customer customer = new Customer();
        customer.setAccountUser(accountUser);
        BeanUtils.copyProperties(customerDTO, customer);
        iCustomerRepository.saveCustomer(
                customer.getNameCustomer(),
                customer.getDateOfBirth(),
                customer.getPointCustomer(),
                customer.getGender(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getIdentityCard(),
                customer.getImgCustomer(),
                customer.getTypeCustomer().getIdTypeCustomer(),
                accountUser.getId()
        );
    }
    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * function: Update customer
     *
     * @param customerDTO
     * @param id
     */
    @Override
    public void updateRegisterCustomer(CustomerDTO customerDTO, Integer id) {
        AccountUser accountUser = iAccountUserRepository
                .findAccountUserByNameAccount(customerDTO.getAccountUser().getNameAccount());
        Customer customer = iCustomerRepository.findByIdCustomer(id);
        iAccountUserRepository.updateAccount(customerDTO.getAccountUser().getNameAccount()
                , passwordEncoder.encode(customerDTO.getAccountUser().getPasswordAccount()), accountUser.getId());
        BeanUtils.copyProperties(customerDTO, customer);
        iCustomerRepository.updateCustomerAccount(
                customerDTO.getNameCustomer(),
                customerDTO.getDateOfBirth(),
                customerDTO.getPointCustomer(),
                customerDTO.getGender(),
                customerDTO.getPhone(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getIdentityCard(),
                customerDTO.getImgCustomer(),
                customerDTO.getTypeCustomer().getIdTypeCustomer(),
                accountUser.getId(),
                customerDTO.getIdCustomer()
        );
    }

    /**
     * Created by: TruongNN
     * Date created: 24/05/2023
     * function: Find customer by id
     *
     * @param id
     * @return
     */
    @Override
    public Customer findById(int id) {
        return iCustomerRepository.findByIdCustomer(id);
    }
    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Customer findByCustomerId(Integer id) {
        return iCustomerRepository.findById((id)).get();
    }

    @Override
    public Boolean existByEmail(String email) {
        Customer customer = iCustomerRepository.findCustomersByEmail(email);
        if (customer != null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean existByPhone(String phone) {
        Customer customer = iCustomerRepository.findCustomersByPhone(phone);
        if (customer != null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean existByIdentity(String identity) {
        Customer customer = iCustomerRepository.findCustomersByIdentityCard(identity);
        if (customer != null) {
            return true;
        }
        return false;
    }
    @Override
    public Customer findCustomerByEmail(String email) {
        return iCustomerRepository.findCustomersByEmail(email);
    }

    @Override
    public List<Customer> findAllAndSearch(String nameSearch) {
        return iCustomerRepository.findAllAndSearch(nameSearch);
    }

    @Override
    public void updateCustomer(String nameCustomer, String phone, String address, String email, Integer idCustomer, String identityCard) {
        iCustomerRepository.updateCustomer(nameCustomer, phone, address, email, idCustomer, identityCard);
    }

    @Override
    public Customer findCustomerByNameAccount(String nameAccount) {
        return iCustomerRepository.findByAccountUser_NameAccount(nameAccount);
    }


}
