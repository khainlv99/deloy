package com.example.dncinema.controller.discount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscountRestController_getListDiscount {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Author: TuanLT
     * Date: 25/05/2023
     * Test case returns List Discount with size = 0;
     * @throws Exception
     */
    @Test
    public void getListDiscount_5() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/discount/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    /**
     * Author: TuanLT
     * Date: 25/05/2023
     * Test case returns List Discount with size > 0;
     * @throws Exception
     */
    @Test
    public void getListDiscount_6() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/discount/list?page=1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(2))
                .andExpect(jsonPath("content[0].nameDiscount").value("ƯU ĐÃI U22"))
                .andExpect(jsonPath("content[0].imageDiscount").value("Url6"))
                .andExpect(jsonPath("content[0].dateStart").value("2022-01-01"))
                .andExpect(jsonPath("content[0].dateEnd").value("2023-01-01"))
                .andExpect(jsonPath("content[0].describeDiscount").value("Giảm giá trực tiếp 66"))
                .andExpect(jsonPath("content[0].percentDiscount").value("70.0"));
    }
}

