package com.example.dncinema.service.impl;

import com.example.dncinema.config.Config;
import com.example.dncinema.dto.TicketDTO;
import com.example.dncinema.model.*;
import com.example.dncinema.repository.ICustomerRepository;
import com.example.dncinema.repository.IDiscountRepositoryMinh;
import com.example.dncinema.repository.IMovieRepository;
import com.example.dncinema.repository.ITicketRepositoryMinh;
import com.example.dncinema.repository.seat.ISeatRepository;
import com.example.dncinema.service.ITicketServiceMinh;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class TicketServiceMinh implements ITicketServiceMinh {
    @Autowired
    private ITicketRepositoryMinh iTicketRepository;
    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private IDiscountRepositoryMinh iDiscountRepository;
    @Autowired
    private ISeatRepository iSeatRepository;
    @Autowired
    private IMovieRepository iMovieRepository;

    /**
     * save ticket information to the database
     *
     * @param ticketDTO
     * @throws UnsupportedEncodingException
     * @author MinhNV
     */

    @Override
    public void saveTicket(TicketDTO ticketDTO) throws UnsupportedEncodingException {
        UUID uuid = UUID.randomUUID();
        String seats = "";
        for (int i = 0; i < ticketDTO.getListSeat().length; i++) {
            seats += ticketDTO.getListSeat()[i].toString() + " ";
        }
        Film film = iMovieRepository.findFilmById(ticketDTO.getIdFilm());
        String data = "Seat" + " " + seats;
        String path = "C:\\CodeGym\\movie-theater\\dn-cinema-api\\dn-cinema\\src\\main\\resources\\qr\\QR" + uuid + ".png";
        createQR(data, path);
        Ticket ticket;
        Discount discount;
        for (int i = 0; i < ticketDTO.getListSeat().length; i++) {

            Seat seat = iSeatRepository.getByIdSeat(ticketDTO.getListSeat()[i]);

            Customer customer = iCustomerRepository.findByIdCustomer(ticketDTO.getIdCustomer());
            Double price;
            if (ticketDTO.getIdDiscount() == null) {
                discount = null;
                if (seat.getTypeSeat().getIdTypeSeat() == 1) {
                    price = film.getNormalSeatPrice();
                } else {
                    price = film.getVipSeatPrice();
                }
            } else {
                discount = iDiscountRepository.findById(ticketDTO.getIdDiscount()).get();
                if (seat.getTypeSeat().getIdTypeSeat() == 1) {
                    price = film.getNormalSeatPrice() - film.getNormalSeatPrice() * discount.getPercentDiscount() / 100;
                } else {
                    price = film.getVipSeatPrice() - film.getVipSeatPrice() * discount.getPercentDiscount() / 100;
                }
            }

            ticket = new Ticket(false, price, LocalDate.now(), path, false, discount, null, customer, seat);

            iTicketRepository.save(ticket);

            setPointCustomer(ticketDTO.getIdCustomer(), seat.getTypeSeat().getIdTypeSeat());
            iSeatRepository.updateStatusSeatSell(ticketDTO.getListSeat()[i]);

//            setTypeCustomer(ticketDTO.getIdCustomer());
        }

        Customer cus = iCustomerRepository.getByIdCus(ticketDTO.getIdCustomer());

        sendEmail(cus.getEmail(), path);

    }

    /**
     * Create the Qr code through the input data and save it in the path
     *
     * @param data
     * @param path
     * @author MinhNV
     * @since 27/05/2023
     */

    public void createQR(String data, String path) {
        try {
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                    = new HashMap<EncodeHintType,
                    ErrorCorrectionLevel>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                    BarcodeFormat.QR_CODE, 100, 100);
            MatrixToImageWriter.writeToFile(
                    matrix,
                    path.substring(path.lastIndexOf('.') + 1),
                    new File(path));
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get QRCode and send email to param email passed in
     *
     * @param email
     * @param path
     * @author MinhNV
     * @since 27/05/2023
     */

    public void sendEmail(String email, String path) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(email);
            message.setSubject("Mã QR");
            message.setText("Kính gửi Quý khách hàng,<br><br>"
                            + "<div style=\"font-weight:bold\">Đây là mã QR của bạn:</div>"
                            + "<img src=\"cid:qr\">"
                            + "<br>"
                            + "Cảm ơn quý khách đã đặt vé xem phim của chúng tôi. "
                            + "Vui lòng không chia sẻ mã này với bất kỳ ai, "
                            + "vì nó được sử dụng để xác thực tài khoản của bạn."
                            + "<br>"
                            + "Nếu bạn không yêu cầu mã OTP, "
                            + "vui lòng bỏ qua email này hoặc liên hệ với chúng tôi để được hỗ trợ."
                            + "<br><br>"
                            + "Trân trọng,<br>"
                            + "<div style=\"color:#183661; font-size:20px; font-weight:bold\">DN Cinema</div>",
                    true);
            FileSystemResource qrImage = new FileSystemResource(new File(path));
            message.addInline("qr", qrImage);
            javaMailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param name
     * @return object Discount
     * @Author MinhNV
     * @since 27/05/2023
     */
    public Discount findDiscount(String name) {
        return iDiscountRepository.findByNameDiscount(name);
    }

    /**
     * Plus points for customers
     *
     * @param idCus
     * @author MinhNV
     * @since 29/05/2023
     */

    public void setPointCustomer(Integer idCus, Integer idTySeat) {
        Customer customer = iCustomerRepository.findById(idCus).get();
        if (idTySeat == 1) {
            customer.setPointCustomer(customer.getPointCustomer() + 10);
        } else {
            customer.setPointCustomer(customer.getPointCustomer() + 15);
        }
        iCustomerRepository.save(customer);
    }

    /**
     * Update customer type
     *
     * @param idCus
     * @author MinhNV
     * @since 29/05/2023
     */

    public void setTypeCustomer(Integer idCus) {
        Customer customer = iCustomerRepository.findById(idCus).get();
        if (customer.getPointCustomer() > 100) {
            iCustomerRepository.updateGold(customer);
        } else if (customer.getPointCustomer() > 200) {
            iCustomerRepository.updateDiamond(customer);
        }
    }

    /**
     * @param ticketDTO
     * @return Return url to check out page
     * @throws UnsupportedEncodingException
     * @author MinhNV
     * @since 27/05/2023
     */
    public String pay(TicketDTO ticketDTO) throws UnsupportedEncodingException {
        StringBuilder seat = new StringBuilder();
        for (int i = 0; i < ticketDTO.getListSeat().length; i++) {
            seat.append(ticketDTO.getListSeat()[i]);
        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = Config.getRandomNumber(8);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(ticketDTO.getPrice() * 100));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl + "?idCus=" + ticketDTO.getIdCustomer() + "&idFilm="
                + ticketDTO.getIdFilm() + "&idDiscount=" + ticketDTO.getIdDiscount() + "&seat=" + seat + "&price=" + ticketDTO.getPrice());
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return Config.vnp_PayUrl + "?" + queryUrl;
    }
}
