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
public class EmployeeRestController_getAllList {

    @Autowired
    private MockMvc mockMvc;

    /**
     *This method is used to check the returned list is of size 0
     * @throws Exception
     */
    @Test
    public void getListEmployee_5() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     *This method is used to check the returned list has size > 0
     * @throws Exception
     */
    @Test
    public void getListEmployee_6() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/employee"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("content[0].idEmployee").value("1"))
                .andExpect(jsonPath("content[0].nameEmployee").value("Tien"))
                .andExpect(jsonPath("content[0].phone").value("0837790795"))
                .andExpect(jsonPath("content[0].address").value("a"))
                .andExpect(jsonPath("content[0].gender").value("a"))
                .andExpect(jsonPath("content[0].email").value("a"))
                .andExpect(jsonPath("content[0].identityCard").value("a"))
                .andExpect(jsonPath("content[1].idEmployee").value("2"))
                .andExpect(jsonPath("content[1].nameEmployee").value("Tan"))
                .andExpect(jsonPath("content[1].phone").value("0123456789"))
                .andExpect(jsonPath("content[1].address").value("b"))
                .andExpect(jsonPath("content[1].gender").value("b"))
                .andExpect(jsonPath("content[1].email").value("b"))
                .andExpect(jsonPath("content[1].identityCard").value("b"));
    }
}
