package com.example.cs4.booking.service;


import com.example.cs4.booking.model.Booking;
import com.example.cs4.booking.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<Booking> findAll(Pageable pageable, String phoneNumber) {
        return bookingRepository.findAllByPhone(pageable, "%" + phoneNumber + "%");
    }

    @Override
    public boolean check(Booking booking) {
        if (bookingRepository.findAllBooking().contains(booking)) {
            throw new IllegalArgumentException("Error!");
        } else if (LocalDate.parse(booking.getBookingDate()).isBefore(LocalDate.now())) {
            throw new RuntimeException("Error!");
        }
        return true;
    }

    @Transactional
    @Override
    public boolean add(Booking booking) {
        if (bookingRepository.findAllBooking().contains(booking)) {
            throw new IllegalArgumentException("Error!");
        }
        bookingRepository.save(booking);
        return true;
    }
//    @Override
//    public boolean add(Booking booking) {
//        if (bookingRepository.findAllBooking().contains(booking)) {
//            throw new IllegalArgumentException("Error!");
//        } else if (LocalDate.parse(booking.getBookingDate()).isBefore(LocalDate.now())) {
//            throw new RuntimeException("Error!");
//        }
//        bookingRepository.save(booking);
//        return true;
//    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id).get();
    }

    @Transactional
    @Override
    public boolean update(Booking booking) {
        if (bookingRepository.findAllBooking().contains(booking)) {
            throw new IllegalArgumentException("Error!");
        } else if (LocalDate.parse(booking.getBookingDate()).isBefore(LocalDate.now())) {
            throw new RuntimeException("Error!");
        }
        bookingRepository.save(booking);
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    public void sendEmail(Booking booking) {
        String toAddress = booking.getCustomer().getEmail();
        String fromAddress = "trung11041990a1@gmail.com";
        String senderName = "DATSAN";
        String subject = "Thư Xác Nhận Đặt Sân";
        String content = "<body style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;\">\n" +
                "      <p>Thân gửi :<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> " + booking.getCustomer().getName() + "</span></p>\n" +
                "      <p>DATSAN xin xác nhận bạn đã đặt sân thành công.Thông tin sân như sau:</p>\n" +
                "      <ul>\n" +
                "        <li>Tên sân: " + booking.getYard().getName() + "</li>\n" +
                "        <li>Địa chỉ: " + booking.getYard().getAddress() + "</li>\n" +
                "        <li>Ngày: " + booking.getBookingDate() + "</li>\n" +
                "        <li>Giờ: " + booking.getTime().getTime() + "</li>\n" +
                "        <li>Đã cọc: " + booking.getDeposit() + "vnđ</li>\n" +
                "        <li>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ tại DATSAN!</li>\n" +
                "      </ul>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8"); // Đặt encoding là UTF-8
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true); // Sử dụng HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }
    @Override
    public Page<Booking> findAllByPhoneAndBookingDate(Pageable pageable, String phoneNumber, String bookingDate) throws ParseException {
        return bookingRepository.findAllByPhoneAndBookingDate(pageable,"%" + phoneNumber + "%","%" + bookingDate + "%");
    }
}
