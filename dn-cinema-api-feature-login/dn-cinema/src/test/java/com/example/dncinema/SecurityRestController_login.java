package com.example.dncinema;

import com.example.dncinema.security.request.SignInForm;
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

/**
 * @author ChinhLV
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestController_login {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @void login_item_13
     * @throws Exception
     * test đầu vào là null và kết quả trả về là lỗi 4xx
     */
    @Test
    public void login_item_13() throws Exception {
        SignInForm signInForm = new SignInForm();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/public/signin").
                content(this.objectMapper.writeValueAsString(signInForm)).
                contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).
                andExpect(status().is4xxClientError());
    }
    /**
     * @void login_item_14
     * @throws Exception
     * test đầu vào là empty và kết quả trả về là lỗi 4xx
     */
    @Test
    public void login_item_14() throws Exception {
        SignInForm signInForm = new SignInForm();
        signInForm.setUsername("");
        signInForm.setPassword("");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/public/signin").content(this.objectMapper.writeValueAsString(signInForm)).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is4xxClientError());
    }
    /**
     * @void login_item_15
     * @throws Exception
     * test đầu vào không đúng validate và kết quả trả về là lỗi 4xx
     */
    @Test
    public void login_item_15() throws Exception {
        SignInForm signInForm = new SignInForm();
        signInForm.setUsername("ch");
        signInForm.setPassword("ch");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/public/signin")
                .content(this.objectMapper.writeValueAsString(signInForm))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is4xxClientError());
    }
    /**
     * @void login_item_16
     * @throws Exception
     * test đầu vào nhỏ hơn min length và kết quả trả về là lỗi 4xx
     */
    @Test
    public void login_item_16() throws Exception {
        SignInForm signInForm = new SignInForm();
        signInForm.setUsername("chin");
        signInForm.setPassword("chinh");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/public/signin")
                .content(this.objectMapper.writeValueAsString(signInForm))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is4xxClientError());
    }
    /**
     * @void login_item_17
     * @throws Exception
     * test đầu vào lớn hơn max length và kết quả trả về là lỗi 4xx
     */
    @Test
    public void login_item_17() throws Exception {
        SignInForm signInForm = new SignInForm();
        signInForm.setUsername("chinhchinhchinhchinhchinhchinhchinhchinhchinhchi");
        signInForm.setPassword("chinhchinhchinhchinhchinhchinhchinhchinhchinhchi");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/public/signin")
                .content(this.objectMapper.writeValueAsString(signInForm))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is4xxClientError());
    }
    /**
     * @void login_item_17
     * @throws Exception
     * test đầu vào tất cả đều hợp lệ và kết quả trả về là lỗi 2xx
     */
    @Test
    public void login_item_18() throws Exception {
        SignInForm signInForm = new SignInForm();
        signInForm.setUsername("chinh.ga");
        signInForm.setPassword("chinhlv24");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/public/signin")
                .content(this.objectMapper.writeValueAsString(signInForm))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is2xxSuccessful());
    }
}
