package com.example.dncinema.service.tickketManagement.impl;

import com.example.dncinema.dto.CustomerPointDTO;
import com.example.dncinema.dto.ICustomerPoint;
import com.example.dncinema.dto.ITicketManagement;
import com.example.dncinema.model.Ticket;
import com.example.dncinema.repository.ITicketRepositoryDong;
import com.example.dncinema.repository.ITicketManagementRepository;
import com.example.dncinema.service.tickketManagement.ITicketManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketManagementService implements ITicketManagementService {

    @Autowired
    private ITicketManagementRepository iTicketManagementRepository;

    @Autowired
    private ITicketRepositoryDong iTicketRepositoryDong;

    @Override
    public Page<ITicketManagement> findAllCustomerTicket(Pageable pageable) {
        Page<ITicketManagement> ticketManagements = iTicketManagementRepository.findCustomerByTicket(pageable);
        return ticketManagements;
    }

    @Override
    public Page<ICustomerPoint> findAllCustomerPoint(Pageable pageable) {
        Page<ICustomerPoint> customerPoints = iTicketManagementRepository.findAllCustomerPointHistory(pageable);
        return customerPoints;
    }

    @Override
    public Page<ICustomerPoint> searchPlusPoint(Pageable pageable, LocalDate dateStart, LocalDate dateEnd) {
        Page<ICustomerPoint> ticketManagements = iTicketManagementRepository.findAllPlusPoint(pageable,dateStart,dateEnd);
        return ticketManagements;
    }

    @Override
    public Ticket findById(Integer id) {
        Ticket ticket = (Ticket) iTicketRepositoryDong.findById(id).get();
        return ticket;
    }

    @Override
    public void delete(Integer id) {
        iTicketRepositoryDong.deleteById(id);
    }
}
