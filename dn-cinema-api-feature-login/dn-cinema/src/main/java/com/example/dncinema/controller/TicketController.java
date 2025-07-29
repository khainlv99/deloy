package com.example.dncinema.controller;

import com.example.dncinema.dto.IListTicketDTO;
import com.example.dncinema.dto.TicketDetailDTO;
import com.example.dncinema.dto.TicketUpdateDTO;
import com.example.dncinema.model.Ticket;
import com.example.dncinema.service.ticket.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee/ticket")
@CrossOrigin("*")
public class TicketController {
    @Autowired
    ITicketService iTicketService;

    /**
     * @return new ResponseEntity
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @Usage_method use method findAll ticket
     */
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<TicketDetailDTO> ticketDetailDTOS = iTicketService.findAll();
        return new ResponseEntity<>(ticketDetailDTOS, HttpStatus.OK);
    }

    /**
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @Param id
     * @Return new ResponseEntity<>
     * @Usage_method findById to show detail ticket
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findTicketById(@PathVariable Integer id) {

        Ticket ticket = iTicketService.findTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    // array id seat mong muon tra lai ve | array id ticket | customer_id

    /**
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @Param ticketDetailDTO
     * @Param bindingResult
     * @Param id
     * @Return new ResponseEntity
     * @Usage_method use method update ticket when customers change ticket
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateTicket(@Validated @RequestBody TicketUpdateDTO ticketUpdateDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            iTicketService.update(ticketUpdateDTO);
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            List<FieldError> err = bindingResult.getFieldErrors();
            for (FieldError error : err) {
                if (!map.containsKey(error.getField())) {
                    map.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * @param pageable
     * @param search
     * @return ResponseEntity<>(tickets, HttpStatus.OK);
     * Phương thức sử dụng để tìm kiếm kết hợp danh sách vé đặt
     * @author DatLVP
     */

    @GetMapping("/list")
    public ResponseEntity<Page<IListTicketDTO>> findAllTicket(@PageableDefault(size = 10) Pageable pageable,
                                           @RequestParam(required = false, defaultValue = "") String search) {
        Page<IListTicketDTO> tickets = iTicketService.findAllTicket(pageable, search);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    /**
     * @param id
     * @return HttpStatus.NO_CONTENT;
     * Phương thức sử dụng để id để huỷ vé
     * @author DatLVP
     */
    @PutMapping("/cancelTicket/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelTicket(@PathVariable("id") Integer id) {
        iTicketService.cancelTicket(id);
    }
}
