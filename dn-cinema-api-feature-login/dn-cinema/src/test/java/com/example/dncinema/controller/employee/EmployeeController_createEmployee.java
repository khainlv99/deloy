//package com.example.dncinema.controller.employee;
//
//import com.example.dncinema.dto.EmployeeDTO;
//import com.example.dncinema.model.AccountUser;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class EmployeeController_createEmployee {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    /**
//     * this function use to test the validation of field name more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_name_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee(null);
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("lamemcothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cunha");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field phone more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_phone_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone(null);
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field address more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_address_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress(null);
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field gender more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_gender_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender(null);
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothaie");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field dateOfBirth more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_dateOfBirth_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth(null);
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothaie");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field image more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_image_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee(null);
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothaai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field email more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_email_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail(null);
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field identity card more specific is null
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_identityCard_13() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard(null);
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field name more specific is empty     *
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_name_14() throws Exception {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("abr");
//        employeeDTO.getAccountUser().setPasswordAccount("null");
//        this.mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/customer/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field phone more specific is empty
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_phone_14() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field address more specific is empty     *
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_address_14() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field gender more specific is empty     *
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_gender_14() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothaie");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field imageEmployee more specific is empty     *
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_imageEmployee_14() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothaai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field email more specific is empty     *
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_email_14() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to test the validation of field identity card more specific is empty     *
//     *
//     * @author NghiaTT
//     * @Time 14h00 25/05/2023
//     */
//    @Test
//    public void createEmployee_identityCard_14() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field name more specific is containing special character
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createCustomer_name_15() throws Exception {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("Trần Trọng Nghĩa @");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("abr");
//        employeeDTO.getAccountUser().setPasswordAccount("null");
//
//        this.mockMvc.perform(MockMvcRequestBuilders
//                        .post("/customer/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field phone more specific is containing special character
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_phone_15() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0908175813@");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field address more specific is containing special character
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_address_15() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("@#K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//
//    /**
//     * //     * this function use to test the validation of field email more specific is containing special character
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_email_15() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb09230");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field identityCard more specific is containing special character
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_identityCard_15() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("dfw3423");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field identityCard minLength
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_identityCard_16() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("1");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field phone minLength
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_phone_16() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0987");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field email minLength
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_email_16() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("04@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field identityCard maxLength
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_identityCard_17() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("1");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field phone maxLength
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_phone_17() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0987");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * //     * this function use to test the validation of field email maxLength
//     * //     * @author NghiaTT
//     * //     * @Time 14h00 25/05/2023
//     * //
//     */
//    @Test
//    public void createEmployee_email_17() throws Exception {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("04@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("cothai");
//        employeeDTO.getAccountUser().setPasswordAccount("anhcho5cuu");
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * this function use to create employee with all valid item
//     *
//     * @author NghiaTT
//     * @Time 14:00 25/04/2023
//     */
//    @Test
//    public void createEmployee_18() throws Exception {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        AccountUser accountUser = new AccountUser();
//        employeeDTO.setAccountUser(accountUser);
//        employeeDTO.setPhone("0898175813");
//        employeeDTO.setAddress("K03/10 Vạn Tường");
//        employeeDTO.setGender("Nam");
//        employeeDTO.setDateOfBirth("2004-05-01");
//        employeeDTO.setImgEmployee("https://tse4.mm.bing.net/th?id=OIP.BiCKfwDo53OAWNYYcHF_RwAAAA&pid=Api&P=0&h=180");
//        employeeDTO.setNameEmployee("NghiaTT");
//        employeeDTO.setEmail("ttn.hdttb152004@gmail.com");
//        employeeDTO.setIdentityCard("123456789012");
//        employeeDTO.getAccountUser().setNameAccount("ahhhh");
//        employeeDTO.getAccountUser().setPasswordAccount("2324234");
//        this.mockMvc.perform(MockMvcRequestBuilders
//                        .post("/employee/create")
//                        .content(this.objectMapper.writeValueAsString(employeeDTO))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//    }
//}
