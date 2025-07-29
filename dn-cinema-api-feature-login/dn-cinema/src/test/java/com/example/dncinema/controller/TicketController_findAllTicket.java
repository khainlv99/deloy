package com.example.dncinema.controller;

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
public class TicketController_findAllTicket {
    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     * @Author DatLVP
     * This method is used to check the return list which param = null
     */

    @Test
    public void findAllTicket_7() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/list?search=null")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author DatLVP
     * This method is used to check the return list which param = ""
     */
    @Test
    public void findAllTicket_8() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/list?search=")).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    /**
     * @throws Exception
     * @Author DatLVP
     * This method is used to check the return list which param does not exist in the database
     */
    @Test
    public void findAllTicket_9() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/list?search=abc123")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author DatLVP
     * This method is used to check the return list has size = 0 which param exist
     */
    @Test
    public void findAllTicket_10() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/list?search=abcxyz")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author DatLVP
     * This method is used to check the return list has size > 0 which param exist
     */
    @Test
    public void findAllTicket_11() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/ticket/list?page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(2))
                .andExpect(jsonPath("content[0].id_ticket").value(1))
                .andExpect(jsonPath("content[0].id_customer").value(1))
                .andExpect(jsonPath("content[0].name_customer").value("Đạt"))
                .andExpect(jsonPath("content[0].phone").value("0935555123"))
                .andExpect(jsonPath("content[0].identity_card").value("191912312"))
                .andExpect(jsonPath("content[0].name_film").value("Harry Potter"))
                .andExpect(jsonPath("content[0].show_date").value("2023-11-11"))
                .andExpect(jsonPath("content[0].show_time").value("2023-11-11"))
                .andExpect(jsonPath("content[0].is_delete").value(false));
    }
}
