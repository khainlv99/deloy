package com.example.dncinema.service.employee.impl;

import com.example.dncinema.dto.EmployeeDTO;
import com.example.dncinema.model.AccountUser;
import com.example.dncinema.model.Employee;
import com.example.dncinema.repository.IAccountUserRepository;
import com.example.dncinema.repository.IEmployeeRepository;
import com.example.dncinema.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;
    @Autowired
    private IAccountUserRepository iAccountUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<EmployeeDTO> searchEmployee(Pageable pageable, String search) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        Page<Employee> employeePage = iEmployeeRepository
                .searchEmployeeInfo(pageable, search);
        EmployeeDTO employeeDTO;
        for (Employee employee : employeePage) {
            employeeDTO = new EmployeeDTO();
            employeeDTO.setAccountUser(new AccountUser());
            BeanUtils.copyProperties(employee.getAccountUser(), employeeDTO.getAccountUser());
            BeanUtils.copyProperties(employee, employeeDTO);
            employeeDTOList.add(employeeDTO);
        }
        return new PageImpl<>(employeeDTOList, pageable, employeePage.getTotalElements());
    }
        /**
         * Created by: NghiaTT
         * Date created: 24/05/2023
         * function: Update employee
         *
         * @param employeeDTO
         * @param id
         */
        @Override
        public void updateEmployee (EmployeeDTO employeeDTO, Integer id){
            AccountUser accountUser = iAccountUserRepository
                    .findAccountUserByNameAccount(employeeDTO.getAccountUser().getNameAccount());
            iAccountUserRepository.updateAccount(employeeDTO.getAccountUser().getNameAccount(), passwordEncoder.encode(employeeDTO.getAccountUser().getPasswordAccount()), accountUser.getId());
            Employee employee = iEmployeeRepository.findByIdEmployee(id);
            BeanUtils.copyProperties(employeeDTO, employee);
            iEmployeeRepository.updateEmployeeWithAccount(employeeDTO.getNameEmployee()
                    , employee.getPhone()
                    , employee.getAddress()
                    , employee.getGender()
                    , employee.getDateOfBirth()
                    , employee.getImgEmployee()
                    , employee.getEmail()
                    , employee.getIdentityCard()
                    , accountUser.getId()
                    , employee.getIdEmployee());
        }

        @Override
        public void deleteEmployee (Integer id){
            Employee employee = iEmployeeRepository.findByIdEmployee(id);
            employee.setDelete(true);
            iEmployeeRepository.save(employee);
        }

        /**
         * Created by: NghiaTT
         * Date created: 24/05/2023
         * function: Create employee
         *
         */
//        @Override
//        public Employee findByEmployeeId (Integer id){
//            return iEmployeeRepository.findByEmployeeId(id);
//        }

        @Override
        public void create (EmployeeDTO employeeDTO){
            iAccountUserRepository.createAccountUser(employeeDTO.getAccountUser().getNameAccount(),
                    passwordEncoder.encode(employeeDTO.getAccountUser().getPasswordAccount()));
            AccountUser accountUser = iAccountUserRepository
                    .findAccountUserByNameAccount(employeeDTO.getAccountUser().getNameAccount());
            Employee employee = new Employee();
            employee.setAccountUser(accountUser);
            BeanUtils.copyProperties(employeeDTO, employee);
            iEmployeeRepository.saveEmployee(
                    employee.getNameEmployee(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getGender(),
                    employee.getDateOfBirth(),
                    employee.getImgEmployee(),
                    employee.getEmail(),
                    employee.getIdentityCard(),
                    accountUser.getId()
            );
        }

        /**
         * Created by: NghiaTT
         * Date created: 24/05/2023
         * function: Find employee by id
         *
         * @param id
         * @return
         */
        @Override
        public Employee findById ( int id){
            return iEmployeeRepository.findByIdEmployee(id);
        }
    }

