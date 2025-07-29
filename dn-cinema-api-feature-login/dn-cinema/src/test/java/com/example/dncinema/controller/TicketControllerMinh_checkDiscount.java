package com.example.dncinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerMinh_checkDiscount {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void checkDiscount_name_1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/ticket/check-discount?discountName=abc")).andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void checkDiscount_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/ticket/check-discount?discountName=SummerSale"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("idDiscount").value(1))
                .andExpect(jsonPath("dateStart").value("2023-06-01"))
                .andExpect(jsonPath("dateEnd").value("2023-06-30"))
                .andExpect(jsonPath("describeDiscount").value("Get 20% off on all tickets"))
                .andExpect(jsonPath("percentDiscount").value(20));
    }
}
