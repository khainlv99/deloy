package com.example.dncinema.controller.ticketManagement;
import com.example.dncinema.dto.ICustomerPoint;
import com.example.dncinema.dto.ITicketManagement;
import com.example.dncinema.model.Customer;
import com.example.dncinema.model.Ticket;
import com.example.dncinema.repository.ICustomerRepository;
import com.example.dncinema.repository.ITicketRepositoryDong;
import com.example.dncinema.service.tickketManagement.ITicketManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class TicketManagementController {

    @Autowired
    private ITicketManagementService iTicketManagementService;

    @Autowired
    private ITicketRepositoryDong iTicketRepositoryDong;
    @Autowired
    private ICustomerRepository iCustomerRepository;

    /**
     * @param pageable
     * @return list customer , status OK
     * @author DongPV
     */
    @GetMapping("/ticket-customer")
    public ResponseEntity<Page<ITicketManagement>> findAllCustomerTicket(@PageableDefault(size = 3) Pageable pageable) {
        Page<ITicketManagement> ticketManagementDTOS = iTicketManagementService.findAllCustomerTicket(pageable);
        if (ticketManagementDTOS.isEmpty()) {
            return new ResponseEntity<>(ticketManagementDTOS, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketManagementDTOS, HttpStatus.OK);
    }

    /**
     * @param pageable
     * @return list customer point history , status OK
     * @author DongPV
     */
    @GetMapping("/ticket-customer/history")
    public ResponseEntity<Page<ICustomerPoint>> findAllCustomerPointHistory(@PageableDefault(size = 3) Pageable pageable) {
        Page<ICustomerPoint> customerPointDTOS = iTicketManagementService.findAllCustomerPoint(pageable);
        if (customerPointDTOS.isEmpty()) {
            return new ResponseEntity<>(customerPointDTOS, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerPointDTOS, HttpStatus.OK);
    }

    /**
     * @param pageable
     * @param dateStart
     * @param dateEnd
     * @return list plus point , status OK
     * @author DongPV
     */
        @GetMapping("/ticket-customer/plus-point")
    public ResponseEntity<Page<ICustomerPoint>> findAllPlusPoint(@PageableDefault(size = 3) Pageable pageable,@RequestParam("dateStart") String dateStart,@RequestParam("dateEnd") String dateEnd) {
        LocalDate start = LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(dateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Page<ICustomerPoint> customerPointDTOS = iTicketManagementService.searchPlusPoint(pageable,start, end);
        if (customerPointDTOS.isEmpty()) {
            return new ResponseEntity<>(customerPointDTOS, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerPointDTOS, HttpStatus.OK);
    }

    /**
     * @param id
     * @return list ticket of customer , status OK
     * @ author DongPV
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Integer id) {
        Ticket ticketManagement = iTicketRepositoryDong.findById(id).get();
        if (ticketManagement == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ticketManagement, HttpStatus.OK);
    }

    /**
     * @param id
     * @return list ticket of customer , Status OK
     * @author DongPV
     */
    @DeleteMapping("/delete-ticket/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        iTicketManagementService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
