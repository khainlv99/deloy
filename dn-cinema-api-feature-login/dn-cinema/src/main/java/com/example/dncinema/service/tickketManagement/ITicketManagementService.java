package com.example.dncinema.service.tickketManagement;

import com.example.dncinema.dto.CustomerPointDTO;
import com.example.dncinema.dto.ICustomerPoint;
import com.example.dncinema.dto.ITicketManagement;
import com.example.dncinema.dto.TicketManagementDTO;
import com.example.dncinema.model.Customer;
import com.example.dncinema.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ITicketManagementService {
    Page<ITicketManagement> findAllCustomerTicket(Pageable pageable);
    Page<ICustomerPoint> findAllCustomerPoint(Pageable pageable);
    Page<ICustomerPoint> searchPlusPoint(Pageable pageable , LocalDate dateStart, LocalDate dateEnd);
    Ticket findById(Integer id);
    void delete(Integer id);
}
