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
public class CustomerController_findAllUsePoint {

    @Autowired
    private MockMvc mockMvc;

    /**
     * this method is used to check the return list which param = null
     * @author DongPV
     * @throws Exception
     */
    @Test
    public void findAllUserPoint_7() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/ticket/use-point/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This method is used to check the return list which param = ""
     * @author DongPV
     * @throws Exception
     */
    @Test
    public void findAllUserPoint_8() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/ticket/use-point/''"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This method is used to check the return list which param does not exist
     * @author DongPV
     * @throws Exception
     */
    @Test
    public void findAllUserPoint_9() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/ticket/use-point/10"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This method is used to check the return list has size = 0 which param exist
     * @author DongPV
     * @throws Exception
     */
    @Test
    public void findAllUserPoint_10() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/ticket/use-point?search=2223/04/15"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This method is used to check the return list has size > 0 which param exist
     * @author DongPV
     * @throws Exception
     */
    @Test
    public void findAllUserPoint_11() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/ticket/use-point?page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(3))
                .andExpect(jsonPath("content[0].date_booking").value("2023-04-15"))
                .andExpect(jsonPath("content[0].price_after_discount").value("123"))
                .andExpect(jsonPath("content[0].status_ticket").value(1))
                .andExpect(jsonPath("content[0].customer.id_customer").value(1))
                .andExpect(jsonPath("content[0].seat.id_seat").value(1));
    }
}
