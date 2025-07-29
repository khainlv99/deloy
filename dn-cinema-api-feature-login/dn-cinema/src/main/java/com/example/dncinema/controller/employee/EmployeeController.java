
package com.example.dncinema.controller.employee;

import com.example.dncinema.dto.EmployeeDTO;
import com.example.dncinema.model.Employee;
import com.example.dncinema.repository.IAccountUserRepository;
import com.example.dncinema.repository.IEmployeeRepository;
import com.example.dncinema.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;



@RestController
@RequestMapping("api/admin/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    private IAccountUserRepository iAccountUserRepository;
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<Page<EmployeeDTO>> search(@PageableDefault(sort = {"id"},direction = Sort.Direction.DESC, size = 10) Pageable pageable,
                                                    @RequestParam(required = false) String search) {

        Page<EmployeeDTO> employeeDTOS = this.iEmployeeService.searchEmployee(pageable, search);

        if (employeeDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        iEmployeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<?> createEmployeeWithAccount(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        iEmployeeService.create(employeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: NghiaTT
     * Date created: 24/05/2023
     * Function: Update data employee  into Database
     *
     * @param employeeDTO
     * @param id
     * @param bindingResult
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployeeWithAccount(@Valid @RequestBody EmployeeDTO employeeDTO,
                                                       @PathVariable("id") Integer id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Employee employee = iEmployeeService.findById(id);
        BeanUtils.copyProperties(employeeDTO, employee);
        iEmployeeService.updateEmployee(employeeDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check-account/{nameAccount}")
    public ResponseEntity<Boolean> checkAccountExistence(@PathVariable("nameAccount") String nameAccount) {
        boolean exists = iAccountUserRepository.existsByNameAccount(nameAccount);
        return ResponseEntity.ok(exists);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") int id) {
        return iEmployeeService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExistence(@PathVariable("email") String email) {
        boolean exists = iEmployeeRepository.existsEmployeeByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check-identityCard/{identityCard}")
    public ResponseEntity<Boolean> checkIdentityCardExistence(@PathVariable("identityCard") String identityCard) {
        boolean exists = iEmployeeRepository.existsEmployeeByIdentityCard(identityCard);
        return ResponseEntity.ok(exists);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check-phone/{phone}")
    public ResponseEntity<Boolean> checkPhoneExistence(@PathVariable("phone") String phone) {
        boolean exists = iEmployeeRepository.existsEmployeeByPhone(phone);
        return ResponseEntity.ok(exists);
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> findByCustomerId(@PathVariable Integer id) {
//        Employee employee = iEmployeeService.findByEmployeeId(id);
//        if (employee == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(employee, HttpStatus.OK);
//    }

}

