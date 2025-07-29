//package com.example.dncinema.controller;
//
//import com.example.dncinema.dto.discount.DiscountDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDate;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DiscountRestController_createDiscount {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    /**
//     * test the validation of field nameDiscount is not null
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_name_1() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount(null);
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-10-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//        /**
//     * test the validation of field nameDiscount is not blank
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_name_2() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount(" ");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-10-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field nameDiscount length is not higher than 255
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_name_3() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-10-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field dateStart is not null
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_dateStart_1() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart(null);
//        discountDTO.setDateEnd("2023-10-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field dateStart format is LocalDate
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_dateStart_2() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-39-17");
//        discountDTO.setDateEnd("2023-10-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field dateEnd is not null
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_dateEnd_1() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd(null);
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * test the validation of field dateStart format is LocalDate
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_dateEnd_2() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-9-17");
//        discountDTO.setDateEnd("20231017");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field describeDiscount is not blank
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_describeDiscount_1() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount("");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field describeDiscount is not null
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_describeDiscount_2() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount(null);
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field percentDiscount is not null
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_percentDiscount_1() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(null);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field percentDiscount is double type
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_percentDiscount_2() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        try {
//            discountDTO.setPercentDiscount(Double.valueOf("asd"));
//        }
//        catch (Exception e){
//            this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                            content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                    .andDo(print()).andExpect(status().is4xxClientError());
//        }
//
//    }
//    /**
//     * test the validation of field percentDiscount is not higher than 100
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_percentDiscount_3() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(101.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test the validation of field percentDiscount is higher than 0
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount_percentDiscount_4() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(0.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//    /**
//     * test create discount with all valid items
//     * @author HoangPT
//     */
//    @Test
//    public void createDiscount() throws Exception{
//        DiscountDTO discountDTO=new DiscountDTO();
//        discountDTO.setNameDiscount("Khuyến mãi");
//        discountDTO.setDateStart("2023-10-17");
//        discountDTO.setDateEnd("2023-11-17");
//        discountDTO.setDescribeDiscount("Khuyến mãi tháng 10");
//        discountDTO.setPercentDiscount(10.0);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/discount/create").
//                        content(this.objectMapper.writeValueAsString(discountDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print()).andExpect(status().is2xxSuccessful());
//    }
//}
