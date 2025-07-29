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
public class SeatRestController_getAllListSeatByIdRoom {
    @Autowired
    private MockMvc mockMvc;
    /**
     * @Author HaiPH
     * This method is used to check the return list which param = null
     * @throws Exception
     */
    @Test
    public void getAllListSeatByIdRoom_7() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/seat/null")).andDo(print()).andExpect(status().is4xxClientError());
    }
    /**
     * @Author HaiPh
     * This method is used to check the return list which param = ""
     * @throws Exception
     */
    @Test
    public void getAllListSeatByIdRoom_8() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/seat/''")).andDo(print()).andExpect(status().is4xxClientError());
    }
    /**
     * @Author HaiPh
     * This method is used to check the return list which param does not exist
     * @throws Exception
     */
    @Test
    public void getAllListSeatByIdRoom_9() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/seat/o")).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * @Author HaiPH
     * This method is used to check the return list has size > 0 which param exist
     * @throws Exception
     */
    @Test
    public void getAllListSeatByIdRoom_11() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/seat/1g"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("[0].idSeat").value(1))
                .andExpect(jsonPath("[0].nameSeat").value("A1"))
                .andExpect(jsonPath("[0].seat.idStatusSeat").value(1))
                .andExpect(jsonPath("[0].seat.nameStatusSeat").value("sell"))
                .andExpect(jsonPath("[0].typeSeat.idTypeSeat").value(1))
                .andExpect(jsonPath("[0].typeSeat.nameTypeSeat").value("Vip"))
                .andExpect(jsonPath("[0].showRoom.idShowRoom").value("1"))
                .andExpect(jsonPath("[0].showRoom.nameShowRoom").value("Phong 1"))
                .andExpect(jsonPath("[0].showRoom.quantitySeat").value(40));
    }
}
