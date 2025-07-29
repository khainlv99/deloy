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
public class MovieController_getFilmById {
    @Autowired
    private MockMvc mockMvc;

    /**
     * @Author: QuynhHTN
     * This a method use check the return detail film which param idFilm = null
     * @Throws Exception
     */
    @Test
    public void getFilmById_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/movie/detail/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * @Author: QuynhHTN
     * This a method use check the return detail film which param idFilm = ''
     * @throws Exception
     */
    @Test
    public void getFilmById_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/movie/detail/''"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * @Author: QuynhHTN
     * This a method use check the return detail film which param not in the DB idFilm = 100
     * @throws Exception
     */
    @Test
    public void getFilmById_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/movie/detail/100"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * @Author: QuynhHTN
     * This a method use check the return detail film which param idFilm = 3
     * @throws Exception
     */
    @Test
    public void getFilmById_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/movie/detail/3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("actor").value("Robert Downey Jr., Chris Evans, Mark Ruffalo"))
                .andExpect(jsonPath("nameFilm").value("Avengers: Endgame"));
    }
}
