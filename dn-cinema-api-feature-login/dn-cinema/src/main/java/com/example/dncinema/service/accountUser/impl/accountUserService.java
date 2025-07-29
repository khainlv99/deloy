package com.example.dncinema.service.accountUser.impl;

import com.example.dncinema.dto.accounUserDTO.AccountUserDTO;
import com.example.dncinema.model.AccountUser;
import com.example.dncinema.repository.IAccountUserRepository;
import com.example.dncinema.service.accountUser.IAccountUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * @author ChinhLV
 * @Param name
 * @return AccountUser findAccountUserByNameAccount(String name)
 * Phương thức sử dụng để tìm kiếm account dựa vào tên account được truyền vào
 * Kết quả trả về là đối tượng AccountUser nếu thành công
 * @Param name
 * @return Boolean existByNameAccount(String name)
 * Phương thức sử dụng check tồn tại của tên account hay không
 * Nếu tên account đã tồn tại trong db thì trả về true, ngược lại false.
 * @Param AccountUser
 * @return AccountUser saveAccountUser(AccountUser accountUser)
 * Phương thức sử dụng để đối tượng của AccountUser về database
 */
@Service
public class accountUserService implements IAccountUserService {
    @Autowired
    private IAccountUserRepository accountUserRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public AccountUser findAccountUserByNameAccount(String name) {
        AccountUser accountUser = accountUserRepository.findAccountUserByNameAccount(name);
        return accountUser;
    }

    @Override
    public Boolean existByNameAccount(String name) {
        AccountUser accountUser = accountUserRepository.findAccountUserByNameAccount(name);
        if (accountUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public AccountUser saveAccountUser(AccountUser accountUser) {
        return accountUserRepository.save(accountUser);
    }

    @Override
    public AccountUser findAccountUserByEmail(String email) {
        return accountUserRepository.findAccountUserByEmail(email);
    }

    @Override
    public int sendEmail(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message;
        Random rnd = new Random();
        int numberRandom = rnd.nextInt(900000) + 100000;
        try {
            message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(email);
            message.setSubject("Mã QR");
            message.setText("Kính gửi Quý khách hàng,<br><br>"
                            + "<div style=\"font-weight:bold\">Đây là mã code của bạn:</div>"
                            + "<h3 class=\"font-weight:bold\">" + numberRandom + "</h3>"
                            + "<br>"
                            + "Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi. "
                            + "Vui lòng không chia sẻ mã này với bất kỳ ai, "
                            + "vì nó được sử dụng để xác thực tài khoản của bạn."
                            + "<br>"
                            + "Nếu bạn không yêu cầu mã code, "
                            + "vui lòng bỏ qua email này hoặc liên hệ với chúng tôi để được hỗ trợ."
                            + "<br><br>"
                            + "Trân trọng,<br>"
                            + "<div style=\"color:#183661; font-size:20px; font-weight:bold\">DN Cinema</div>",
                    true);
            javaMailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return numberRandom;
    }
    @Override
    public void sendPassword(String email, int password) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(email);
            message.setSubject("Mã QR");
            message.setText("Kính gửi Quý khách hàng,<br><br>"
                            + "<div style=\"font-weight:bold\">Đây là mật khẩu của bạn:</div>"
                            + "<h3 class=\"font-weight:bold\">" + password + "</h3>"
                            + "<br>"
                            + "Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi. "
                            + "Vui lòng không chia sẻ mã này với bất kỳ ai, "
                            + "vì nó được sử dụng để đăng nhập tài khoản của bạn sau này."
                            + "<br>"
                            + "Nếu bạn không yêu cầu mã, "
                            + "vui lòng bỏ qua email này hoặc liên hệ với chúng tôi để được hỗ trợ."
                            + "<br><br>"
                            + "Trân trọng,<br>"
                            + "<div style=\"color:#183661; font-size:20px; font-weight:bold\">DN Cinema</div>",
                    true);
            javaMailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePassword(AccountUserDTO accountUserDTO, Integer id) {
        AccountUser accountUser = accountUserRepository.findAccountUserById(id);
        BeanUtils.copyProperties(accountUserDTO, accountUser);
        accountUserRepository.savePassword(
                accountUserDTO.getPasswordAccount(),
                accountUserDTO.getId()
        );
    }
    @Override
    public AccountUser findById(int id) {
        return accountUserRepository.findAccountUserById(id);
    }

}
