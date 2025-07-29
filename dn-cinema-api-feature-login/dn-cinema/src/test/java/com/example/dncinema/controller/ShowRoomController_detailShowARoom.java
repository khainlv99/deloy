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
public class ShowRoomController_detailShowARoom {
    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list which param id null
     */
    @Test
    public void findbyIdShowRoom_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/showroom?id=null")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list which param id ''
     */
    @Test
    public void findbyIdShowRoom_2() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/showroom?id=''")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list which param id not exist in database
     */
    @Test
    public void findByIdShowRoom_3() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/showroom?id=10")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @throws Exception
     * @Author LanhNM
     * This method is used to check the return list which param id exist in database
     */
//    @Test
//    public void findByIdShowRoom_4() throws Exception {
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders.get("/showroom/list?id=1"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("content[0].nameShowRoom").value("PC01"))
//                .andExpect(jsonPath("content[0].quantitySeat").value(50))
//                .andExpect(jsonPath("content[0].nameSeat").value("A1"))
//                .andExpect(jsonPath("content[0].nameTypeSeat").value("Vip"))
//                .andExpect(jsonPath("content[0].nameStatusSeat").value("Đã chọn"));
//    }
}
