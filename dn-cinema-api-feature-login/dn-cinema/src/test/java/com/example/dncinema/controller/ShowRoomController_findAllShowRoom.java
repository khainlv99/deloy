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
public class ShowRoomController_findAllShowRoom {
    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list which param = null
     */

    @Test
    public void findAllShowRoom_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/showroom/list?name=null")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list which param = ""
     */
    @Test
    public void findAllShowRoom_2() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/showroom/list?name=''")).andDo(print()).andExpect(status().is4xxClientError());
    }



    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list has size = 0 which param exist
     */
    @Test
    public void findAllShowRoom_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/showroom/list?name=aaaa")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list has size > 0 which param exist
     */
    @Test
    public void findAllShowRoom_5() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/showroom/list?page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(2))
                .andExpect(jsonPath("totalElements").value(5))
                .andExpect(jsonPath("content[0].idShowRoom").value(1))
                .andExpect(jsonPath("content[0].nameShowRoom").value("PC01"))
                .andExpect(jsonPath("content[0].quantitySeat").value(50));
    }
}
