package com.example.dncinema.controller;

import com.example.dncinema.dto.customerDTO.CustomerDTO;
import com.example.dncinema.model.AccountUser;
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
public class RegisterCustomerController_updateCustomerAccount {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * This function use to test the validation of field name more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_name_19() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer(null);
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to check the validity of specific field name instead of empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_name_20() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("");
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * This function is used to check the validity of a specific field name instead of the wrong format
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_name_21() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("@truong");
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field name instead of too short characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_name_22() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("a");
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field name instead of too long characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_name_23() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường jsdaskdaldsasd akjsdhaksdhakjdaaksldakjsd a; \" +\n" +
                "                \"msadmasdakdnaksdnakd nakdsjnajkdnajksdnajdsnaj\" +\n" +
                "                \"jaksdaapodsapdap[lal[pd");
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field point more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_point_19() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(null);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to check the validity of specific field name instead of empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_point_20() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field name instead of the wrong format
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_point_21() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(-3.2);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a particular field name because it is less than 0
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_point_22() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(-3.2);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test the validation of field name more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_gender_19() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender(null);
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test the validation of field name more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_gender_20() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * This function is used to check the validity of a specific field name instead of the wrong format
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_gender_21() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("khác");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field phone more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_phone_19() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone(null);
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field phone instead of empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_phone_20() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field phone instead of the wrong format
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_phone_21() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("111111@1");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field phone instead of too short characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_phone_22() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("12");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field phone instead of too long characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_phone_23() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("123456789011");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test the validation of field address more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_address_19() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress(null);
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test the validation of field address more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_address_20() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("0378730129");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function is used to check the validity of a specific field name instead of too short characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void updateCustomerAccount_address_21() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.2);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("12a");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/1")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
