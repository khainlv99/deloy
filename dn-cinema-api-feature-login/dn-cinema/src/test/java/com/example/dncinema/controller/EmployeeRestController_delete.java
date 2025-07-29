package com.example.dncinema.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestController_delete {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void delete_id_25() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/employee?id=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case delete customer with empty id parameter
     * @throws Exception
     */
    @Test
    public void delete_id_26() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/employee?id="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case delete customer with id parameter does not exist in database
     * @throws Exception
     */
    @Test
    public void delete_id_27() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/employee?id=999"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case delete customer with id parameter exists in database
     * @throws Exception
     */
    @Test
    public void delete_id_28() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/employee/{id}", 2))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
