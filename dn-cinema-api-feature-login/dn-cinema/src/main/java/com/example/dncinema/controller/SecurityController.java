package com.example.dncinema.controller;

import com.example.dncinema.model.AccountUser;
import com.example.dncinema.model.Customer;
import com.example.dncinema.model.Roles;
import com.example.dncinema.security.jwt.JwtProvider;
import com.example.dncinema.security.request.*;
import com.example.dncinema.security.response.ConfirmEmailSuccess;
import com.example.dncinema.security.response.ErrorMessage;
import com.example.dncinema.security.response.JwtResponse;
import com.example.dncinema.security.response.ResponseMessage;
import com.example.dncinema.security.userPrincipal.UserPrinciple;
import com.example.dncinema.service.accountUser.IAccountUserService;
import com.example.dncinema.service.customer.ICustomerService;
import com.example.dncinema.service.roles.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author ChinhLV
 */
@RestController
@RequestMapping("/api/public")
@CrossOrigin("*")
public class SecurityController {
    @Autowired
    IAccountUserService accountUserService;
    @Autowired
    IRolesService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    ICustomerService iCustomerService;
    /**
     * @author ChinhLV
     * @body signInForm
     * @return ResponseEntity<?> signup(@Valid @RequestBody SignUpForm signUpForm)
     * Phương thức sử dụng để đăng ký account dựa trên đầu vào là username và password
     * Kết quả trả về là 1 object bao gồm: message thành công khi lưu thành công hoặc bại khi lưu thất bại
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpForm signUpForm) {
        if (accountUserService.existByNameAccount(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("The username existed !!, Try again"), HttpStatus.OK);
        }
        AccountUser users = new AccountUser(signUpForm.getUsername(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Roles> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Roles adminRole = roleService.findRolesByName("ADMIN");
                    roles.add(adminRole);
                    break;
                case "employee":
                    Roles employeeRole = roleService.findRolesByName("EMPLOYEE");
                    roles.add(employeeRole);
                    break;
                default:
                    Roles userRole = roleService.findRolesByName("USER");
                    roles.add(userRole);
            }
        });
        users.setRoles(roles);
        System.out.println(users);
        AccountUser accountUser = accountUserService.saveAccountUser(users);
        if (accountUser != null) {
            return new ResponseEntity<>(new ResponseMessage("Create user success!!!"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseMessage("Create user failed!!!"), HttpStatus.BAD_REQUEST);
    }

    /**
     * @author ChinhLV
     * @body signInForm
     * @return ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm)
     * Phương thức sử dụng để đăng nhập dựa trên đầu vào là username và password
     * Kết quả trả về là 1 object bao gồm: token được tạo có hiệu lực 1h, tên người dùng và quyền truy cập
     */
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorMessage> errorMessages = new ArrayList<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMessages.add(new ErrorMessage(f.getField(), f.getDefaultMessage())));
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(token, userPrinciple.getUsername(), userPrinciple.getAuthorities()), HttpStatus.OK);
    }
    /**
     * @author ChinhLV
     * @param emailConfirm
     * @return ResponseMessage thông báo
     * hàm trả message nếu email đã tồn tại trong db hay chưa
     */
    @PostMapping("/confirm-email")
    public ResponseEntity<?> confirmEmailSignup(@RequestBody EmailConfirm emailConfirm) {
        Boolean flag = iCustomerService.existByEmail(emailConfirm.getEmail());
        if (flag) {
            int code = accountUserService.sendEmail(emailConfirm.getEmail());
            return new ResponseEntity<>(new ConfirmEmailSuccess("Xác nhận email thành công.",String.valueOf(code), emailConfirm.getEmail()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Email không tồn tại."),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @author ChinhLV
     * @param resetPassword
     * @return http.statusCode
     * hàm trả status code 2xx nếu đổi mật khẩu thành công, 4xx nếu thất bại.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword resetPassword ) {
        Customer customer = iCustomerService.findCustomerByEmail(resetPassword.getEmail());
        AccountUser accountUser = customer.getAccountUser();
        accountUser.setPasswordAccount(passwordEncoder.encode(resetPassword.getPassword()));
        AccountUser newAccountUser = accountUserService.saveAccountUser(accountUser);
        if (newAccountUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * @author ChinhLV
     * @param facebookRequest
     * @return http.statusCode
     * hàm trả status code 2xx nếu đổi mật khẩu thành công, 4xx nếu thất bại.
     */
    @PostMapping("/login-facebook")
    public ResponseEntity<?> loginByAccountFacebook(@RequestBody FacebookRequest facebookRequest) {
        if (accountUserService.existByNameAccount(facebookRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại, vui lòng thử lại"), HttpStatus.BAD_REQUEST);
        }
        AccountUser accountUser = new AccountUser();
        Random rnd = new Random();
        int numberRandom = rnd.nextInt(900000) + 100000;
        accountUser.setNameAccount(facebookRequest.getEmail());
        accountUser.setPasswordAccount(passwordEncoder.encode(String.valueOf(numberRandom)));
        Set<Roles> roles = new HashSet<>();
        roles.add(roleService.findRolesByName("USER"));
        accountUser.setRoles(roles);
        accountUserService.saveAccountUser(accountUser);
        accountUserService.sendPassword(facebookRequest.getEmail(), numberRandom);
        accountUserService.saveAccountUser(accountUser);
        return new ResponseEntity<>(new ResponseMessage("Thêm mới tài khoản thành công."),HttpStatus.CREATED);
    }
}
