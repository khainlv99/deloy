//package com.example.dncinema.controller;
//
//import com.example.dncinema.dto.CustomerDTO;
//import com.example.dncinema.dto.TicketDTO;
//import com.example.dncinema.model.Customer;
//import com.example.dncinema.model.Discount;
//import com.example.dncinema.model.Film;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TicketControllerMinh_create {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    public void save_18() throws Exception {
//        Integer[] list = {1, 2};
//        Film film = new Film();
//        Discount discount = new Discount();
//        discount.setIdDiscount(1);
//        CustomerDTO customer = new CustomerDTO();
//        customer.setIdCustomer(1);
//        film.setIdFilm(1);
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setCustomer(customer);
//        ticketDTO.setFilm(film);
//        ticketDTO.setListSeat(list);
//        ticketDTO.setPrice(100000);
//        ticketDTO.setDiscount(discount);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket/create").content(this.objectMapper.writeValueAsString(ticketDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//    }
//    @Test
//    public void save_customer_13() throws Exception {
//        Integer[] list = {1, 2};
//        Film film = new Film();
//        Discount discount = new Discount();
//        discount.setIdDiscount(1);
//        film.setIdFilm(1);
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setCustomer(null);
//        ticketDTO.setFilm(film);
//        ticketDTO.setListSeat(list);
//        ticketDTO.setPrice(100000);
//        ticketDTO.setDiscount(discount);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket/create").content(this.objectMapper.writeValueAsString(ticketDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
//                .andExpect(status().is4xxClientError());
//
//    }
//    @Test
//    public void save_film_13() throws Exception {
//        Integer[] list = {1, 2};
//        Discount discount = new Discount();
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setIdCustomer(1);
//        discount.setIdDiscount(1);
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setCustomer(customerDTO);
//        ticketDTO.setFilm(null);
//        ticketDTO.setListSeat(list);
//        ticketDTO.setPrice(100000);
//        ticketDTO.setDiscount(discount);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket/create").content(this.objectMapper.writeValueAsString(ticketDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
//                .andExpect(status().is4xxClientError());
//
//    }
//    @Test
//    public void save_price_13() throws Exception {
//        Integer[] list = {1, 2};
//        Film film = new Film();
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setIdCustomer(1);
//        Discount discount = new Discount();
//        discount.setIdDiscount(1);
//        film.setIdFilm(1);
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setCustomer(customerDTO);
//        ticketDTO.setFilm(film);
//        ticketDTO.setListSeat(list);
//        ticketDTO.setPrice(0);
//        ticketDTO.setDiscount(discount);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket/create").content(this.objectMapper.writeValueAsString(ticketDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void save_listSeat_13() throws Exception {
//        Film film = new Film();
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setIdCustomer(1);
//        Discount discount = new Discount();
//        discount.setIdDiscount(1);
//        film.setIdFilm(1);
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setCustomer(customerDTO);
//        ticketDTO.setFilm(film);
//        ticketDTO.setListSeat(null);
//        ticketDTO.setPrice(100000);
//        ticketDTO.setDiscount(discount);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket/create").content(this.objectMapper.writeValueAsString(ticketDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
//                .andExpect(status().is4xxClientError());
//
//    }
//    @Test
//    public void save_listSeat_14() throws Exception {
//        Integer[] list={};
//        Film film = new Film();
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setIdCustomer(1);
//        Discount discount = new Discount();
//        discount.setIdDiscount(1);
//        film.setIdFilm(1);
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setCustomer(customerDTO);
//        ticketDTO.setFilm(film);
//        ticketDTO.setListSeat(list);
//        ticketDTO.setPrice(100000);
//        ticketDTO.setDiscount(discount);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket/create").content(this.objectMapper.writeValueAsString(ticketDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
//                .andExpect(status().is4xxClientError());
//
//    }
//
//
//}
