//package com.example.dncinema.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DiscountRestController_findDiscountById {
//    @Autowired
//    private MockMvc mockMvc;
//    /**
//     * test case id is null
//     * @author HoangPT
//     */
//    @Test
//    public void findDiscountById_id_1() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/discount/?id=null")).
//                andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test case dont have discount return from database
//     * @author HoangPT
//     */
//    @Test
//    public void findDiscountById_id_3() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/discount/1000000")).
//                andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test case find discount successful
//     * @author HoangPT
//     */
//    @Test
//    public void findDiscountById_4() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/discount/1")).
//                andDo(print()).andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("nameDiscount").value("Khuyến mãi"))
//                .andExpect(jsonPath("dateStart").value("2023-10-17"))
//                .andExpect(jsonPath("dateEnd").value("2023-11-17"))
//                .andExpect(jsonPath("describeDiscount").value("Khuyến mãi cuối tháng 6 giành cho 50 khách hàng may mắn đã từng đặt vé vào tháng 4"))
//                .andExpect(jsonPath("percentDiscount").value(10.0));
//    }
//}
