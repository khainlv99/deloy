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
public class MovieRestController_getAllFilms {
    @Autowired
    private MockMvc mockMvc;
    /**
     * @Author HaiPh
     * This method is used to check the return list has size = 0 which param exist
     * @throws Exception
     */

    @Test
    public void getAllFilms_5() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/movie/list/sd"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * @Author HaiPh
     * This method is used to check the return list has size > 0 which param exist
     * @throws Exception
     */

    @Test
    public void getAllFilms_6() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/movie/list/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("[0].nameFilm").value("Nha Ba Nu"))
                .andExpect(jsonPath("[0].director").value("Tran Thanh"))
                .andExpect(jsonPath("[0].imgFilm").value("sdg"))
                .andExpect(jsonPath("[0].describeFilm").value("Hay"))
                .andExpect(jsonPath("[0].actor").value("Tran Thanh"))
                .andExpect(jsonPath("[0].normalSeatPrice").value(70))
                .andExpect(jsonPath("[0].vipSeatPrice").value(80))
                .andExpect(jsonPath("[0].dateStartFilm").value("2023-07-11"))
                .andExpect(jsonPath("[0].dateEndFilm").value("2023-06-11"))
                .andExpect(jsonPath("[0].imgFilm").value("sdg"))
                .andExpect(jsonPath("[0].typeFilm.idTypeFilm").value(1));
    }

}
