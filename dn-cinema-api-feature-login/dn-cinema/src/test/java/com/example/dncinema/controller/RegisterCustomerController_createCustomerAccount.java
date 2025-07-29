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
public class RegisterCustomerController_createCustomerAccount {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * this function use to test the validation of field name more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_name_13() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer(null);
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
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
    public void createCustomerAccount_point_13() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("TruongNN");
        customerDTO.setPointCustomer(null);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field gender more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_gender_13() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("TruongNN");
        customerDTO.setPointCustomer(8.0);
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
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field phone more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_phone_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone(null);
        customerDTO.setAddress("Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdCustomer(1);
        customerDTO.setIdentityCard("1234567890");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field address more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_address_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress(null);
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdCustomer(1);
        customerDTO.setIdentityCard("1234567890");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field email more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_email_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("Bắc đẩu");
        customerDTO.setEmail(null);
        customerDTO.setIdCustomer(1);
        customerDTO.setIdentityCard("1234567890");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field type customer more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_typeCustomer_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdCustomer(null);
        customerDTO.setIdentityCard("1234567890");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * this function use to test the validation of field identity more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_identity_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdCustomer(1);
        customerDTO.setIdentityCard(null);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * this function use to test the validation of field name account more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_nameAccount_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdCustomer(1);
        customerDTO.setIdentityCard("123456789");
        customerDTO.getAccountUser().setNameAccount(null);
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * this function use to test the validation of field password account more specific is null
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_passwordAccount_13() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdCustomer(1);
        customerDTO.setIdentityCard("123456789");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount(null);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     *  this function use to test the validation of field name more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_name_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("");
        customerDTO.setPointCustomer(8.0);
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field point more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_point_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setGender("Nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field gender more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_gender_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
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
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field gender more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_phone_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     *  this function use to test the validation of field address more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_address_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field address more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_email_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("");
        customerDTO.setIdentityCard("1234567890");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field identity more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_identity_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("");
        customerDTO.setIdCustomer(1);
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     *  this function use to test the validation of field type customer more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_typeCustomer_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field name account more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_nameAccount_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("");
        customerDTO.getAccountUser().setNameAccount("");
        customerDTO.getAccountUser().setPasswordAccount("123123");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  this function use to test the validation of field password account more specific is empty
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_passWord_14() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngọc Trường");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("name");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a specific field name due to incorrect formatting
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_name_15() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("@Truong");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a specific field name due to its length being too short
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_name_16() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("a");
        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a specific field name due to its length being too long
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_longName_16() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường jsdaskdaldsasd akjsdhaksdhakjdaaksldakjsd a; " +
                "msadmasdakdnaksdnakd nakdsjnajkdnajksdnajdsnaj" +
                "jaksdaapodsapdap[lal[pd");

        customerDTO.setPointCustomer(9.3);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a particular field point since the value must be greater than 0
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_point_16() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(-3.3);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     *  This function is used to check the validity of the specific gender must be male or female
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_gender_15() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(4.7);
        customerDTO.setGender("khác");
        customerDTO.setPhone("0898175813");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a specific phone number which must have 10 or 11 digits
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_phone_15() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.3);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("0378730129@");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a specific phone number which must have 10 digits
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_phone_16() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.3);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0378");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of a specific phone number which must be less than 11 digits
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_phone_17() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.3);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("03787301297899");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123132Q");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of an address that must be from 3 characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_address_16() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.3);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of the address must be less than 200 characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_address_17() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.3);
        customerDTO.setGender("nữ");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("r!HFv$4kIx2L^5VptK%DM{N[E]UbT;Oq_sQ0lmW?gZa+o)iY1A6]#9nGJ7w3hC-c8S'fjRzPvX\"" +
                ":,BdyXv9Fx,t]!UEeI7^KgsM@4Vc5c]mTIKtJ3[XD6Q}LzNSh1pDoG2=4$q&8 P9Oh#WpQVvBw_e-" +
                "mO0:d)S',hN6ZLnXY;H?Kqz5a*uMX3RbJsf1iC7{^kFjg4yvATt]E$Gc8lDUP!I2^#(z%9r\"");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     *  This function use to check the validity of specific malformed email field
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_email_15() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(3.3);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong");
        customerDTO.setIdentityCard("197366689");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function use to check the validity of specific malformed identity field
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_identity_15() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(9.9);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1111111111111111");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     *  This function is used to check the validity of an identity that must have 10 characters
     *
     * @author TruongNN
     * @Time 09h 26/05/2023
     */
    @Test
    public void createCustomerAccount_identity_16() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        AccountUser accountUser = new AccountUser();
        customerDTO.setAccountUser(accountUser);
        customerDTO.setNameCustomer("Ngô Ngọc Trường");
        customerDTO.setPointCustomer(9.9);
        customerDTO.setGender("nam");
        customerDTO.setPhone("0378730129");
        customerDTO.setAddress("15/11 Bắc đẩu");
        customerDTO.setEmail("ngongoctruong@gmail.com");
        customerDTO.setIdentityCard("1111111111111111");
        customerDTO.getAccountUser().setNameAccount("TruongNN");
        customerDTO.getAccountUser().setPasswordAccount("123123123A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
