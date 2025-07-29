package com.example.dncinema.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestController_search {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Test case find customer code with null parameter
     *
     * @throws Exception
     */
    @Test
    public void search_code_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchCode=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case finds customer code with empty parameter
     *
     * @throws Exception
     */
    @Test
    public void search_code_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchCode="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Test case finds customer code whose parameter does not exist in the database
     *
     * @throws Exception
     */
    @Test
    public void search_code_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchCode=non-existing-code"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case find customer code whose parameter exists in database
     *
     * @throws Exception
     */
    @Test
    public void search_code_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchCode=NV"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Test case finding customer name with null parameter
     *
     * @throws Exception
     */
    @Test
    public void search_name_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchName=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case find customer name with empty parameter
     *
     * @throws Exception
     */
    @Test
    public void search_name_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchName="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Test case find customer name with parameter does not exist in database
     *
     * @throws Exception
     */
    @Test
    public void search_name_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchName=non-existing-name"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case find customer name with parameter exists in database
     * @throws Exception
     */
    @Test
    public void search_name_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchName=Tiến"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Test case find customer phone number with null parameter
     *
     * @throws Exception
     */
    @Test
    public void search_phone_number_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchPhoneNumber=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case find customer phone number with empty parameter
     *
     * @throws Exception
     */
    @Test
    public void search_phone_number_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchPhoneNumber="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Test case find customer phone number with parameter does not exist in database
     *
     * @throws Exception
     */
    @Test
    public void search_phone_number_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchPhoneNumber=non-existing-code"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test case find customer phone number with parameter exists in database
     *
     * @throws Exception
     */
    @Test
    public void search_phone_number_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee?searchPhoneNumber=0837790795"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * This method is used to search for customers by information such as code,
     * name or phone number and returns a customer page with the page size and page number
     * specified by the paginable parameter.
     * @throws Exception
     */
    @Test
    public void search_with_pageable_99() throws Exception {
        int pageSize = 3;
        int pageNumber = 0;
        String sortBy = "id";
        Sort sort = Sort.by(sortBy);
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee")
                                .param("searchCode", "")
                                .param("searchName", "")
                                .param("searchPhoneNumber", "")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("page", String.valueOf(pageNumber))
                                .param("size", String.valueOf(pageSize))
                                .param("sort", sortBy)
                ).andDo(print())
                .andExpect(status().isOk());
    }
}
