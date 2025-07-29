package com.example.dncinema;

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
public class TicketController_getTicketById {
    @Autowired
    private MockMvc mockMvc;
    /**
     * @Author: QuynhHTN
     * This a method use check the return detail ticket which param idTicket = null
     * @Throws Exception
     */
    @Test
    public void getTicketById_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/ticket/detail/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * @Author: QuynhHTN
     * This a method use check the return detail ticket which param idTicket = ''
     * @Throws Exception
     */
    @Test
    public void getTicketById_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/ticket/detail/''"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * @Author: QuynhHTN
     * This a method use check the return detail ticket which param not in the DB idTicket = 1
     * @Throws Exception
     */
    @Test
    public void getTicketById_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/ticket/detail/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * @Author: QuynhHTN
     * This a method use check the return detail ticket which param idTicket = 2
     * @Throws Exception
     */
    @Test
    public void getTicketById_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/ticket/detail/2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("statusTicket").value("Booked"))
                .andExpect(jsonPath("dateBooking").value("2023-06-01"));
    }
}
