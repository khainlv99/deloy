package com.example.dncinema.service.ticket;

import com.example.dncinema.dto.IListTicketDTO;
import com.example.dncinema.dto.TicketDetailDTO;
import com.example.dncinema.dto.TicketUpdateDTO;
import com.example.dncinema.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService {
    Ticket findTicketById(Integer id);

    void update(TicketUpdateDTO ticketUpdateDTO);

    List<TicketDetailDTO> findAll();

    Page<IListTicketDTO> findAllTicket(Pageable pageable, String search);

    void cancelTicket(Integer id);
    void confirmPrintTicket(Integer id);
}
