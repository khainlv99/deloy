package com.example.dncinema.controller.discount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscountRestController_getDeleteDiscount {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Author: TuanLT
     * Date: 25/05/2023
     * Test case delete Discount with id parameter passed to null
     * @throws Exception
     */

    @Test
    public void delete_id_25() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/discount/"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    /**
     * Author: TuanLT
     * Date: 25/05/2023
     * Test case delete Discount with empty id parameter
     * @throws Exception
     */
    @Test
    public void delete_id_26() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/discount/''"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    /**
     * Author: TuanLT
     * Date: 25/05/2023
     * Test case delete Discount with id parameter does not exist in DB
     * @throws Exception
     */
    @Test
    public void delete_id_27() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/discount/999"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    /**
     * Author: TuanLT
     * Date: 25/05/2023
     * Test case delete Discount with id parameter exist in DB
     * @throws Exception
     */
    @Test
    public void delete_id_28() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/discount/{id}", 2))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
