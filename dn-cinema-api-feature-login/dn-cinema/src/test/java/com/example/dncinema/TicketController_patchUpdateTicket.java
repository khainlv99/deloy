package com.example.dncinema;

import com.example.dncinema.dto.*;
import com.example.dncinema.model.Discount;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//note: item 21,22,23 không phải do người dùng nhập vào
@SpringBootTest
@AutoConfigureMockMvc
public class TicketController_patchUpdateTicket {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @throws Exception
     * @Author: QuynhHTN
     * This a method use check the return update ticket which param idSeat = null
     */
    @Test
    public void UpdateTicket_idSeat_19() throws Exception {
        TicketDetailDTO ticketDetailDTO = new TicketDetailDTO();
        ticketDetailDTO.setIdTicket(2);
        ticketDetailDTO.setDateBooking(LocalDate.parse("2023-06-01"));
        ticketDetailDTO.setIdQr("ABC123");
        ticketDetailDTO.setPriceAfterDiscount(8L);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setIdEmployee(2);


//        StatusSeatDTO statusSeatDTO=new StatusSeatDTO();
//        statusSeatDTO.setIdStatusSeat(null);
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setIdSeat(null);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setIdCustomer(1);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/ticket/update/2")
                        .content(this.objectMapper.writeValueAsString(ticketDetailDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    /**
     * @throws Exception
     * @Author: QuynhHTN
     * This a method use check the return update ticket which param idSeat = ""
     */
    @Test
    public void UpdateTicket_idSeat_20() throws Exception {
        TicketDetailDTO ticketDetailDTO = new TicketDetailDTO();
        ticketDetailDTO.setIdTicket(2);
        ticketDetailDTO.setDateBooking(LocalDate.parse("2023-06-01"));
        ticketDetailDTO.setIdQr("ABC123");
        ticketDetailDTO.setPriceAfterDiscount(8L);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setIdEmployee(2);

        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setIdSeat(0);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setIdCustomer(1);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/ticket/update/2")
                        .content(this.objectMapper.writeValueAsString(ticketDetailDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * @Author: QuynhHTN
     * This a method use check the return update ticket which param idSeat =1
     * @throws Exception
     */
    @Test
    public void UpdateTicket_idSeat_24() throws Exception {
        TicketDetailDTO ticketDetailDTO = new TicketDetailDTO();
        ticketDetailDTO.setIdTicket(2);
        ticketDetailDTO.setStatusTicket("Booked");
        ticketDetailDTO.setDateBooking(LocalDate.parse("2023-06-01"));
        ticketDetailDTO.setIdQr("ABC123");
        ticketDetailDTO.setPriceAfterDiscount(8L);
        Discount discount=new Discount();
        discount.setIdDiscount(1);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setIdEmployee(1);


        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setIdSeat(1);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setIdCustomer(1);

        /* Start */

//        customerDTO.setPointCustomer(100.0);
        /* End */

        ticketDetailDTO.setDiscount(discount);
        ticketDetailDTO.setSeatDTO(seatDTO);
        ticketDetailDTO.setCustomerDTO(customerDTO);
        ticketDetailDTO.setEmployeeDTO(employeeDTO);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ticket/update/2")
                        .content(this.objectMapper.writeValueAsString(ticketDetailDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
