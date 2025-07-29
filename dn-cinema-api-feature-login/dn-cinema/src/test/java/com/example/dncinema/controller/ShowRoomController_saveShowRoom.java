package com.example.dncinema.controller;
import com.example.dncinema.dto.ShowRoomDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class ShowRoomController_saveShowRoom {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        /**
         *
         * @throws Exception
         */
        @Test
        public void saveShowRoom_name_14() throws Exception {

            ShowRoomDTO showRoomDTO = new ShowRoomDTO();
            showRoomDTO.setNameShowRoom("");
            showRoomDTO.setQuantitySeat(50);


            this.mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/showroom/create")
                            .content(this.objectMapper.writeValueAsString(showRoomDTO))
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void saveShowRoom_quantity_14() throws Exception {

            ShowRoomDTO showRoomDTO = new ShowRoomDTO();
            showRoomDTO.setNameShowRoom("PC01");
            showRoomDTO.setQuantitySeat(0);
            this.mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/showroom/create")
                            .content(this.objectMapper.writeValueAsString(showRoomDTO))
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void saveShowRoom_18() throws Exception {

            ShowRoomDTO showRoomDTO = new ShowRoomDTO();
            showRoomDTO.setNameShowRoom("PC10");
            showRoomDTO.setQuantitySeat(5);

            this.mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/showroom/create")
                            .content(this.objectMapper.writeValueAsString(showRoomDTO))
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        }
}
