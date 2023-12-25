package com.example.cs4.account.service;

import com.example.cs4.account.model.Account;
import com.example.cs4.account.repository.IAccountRepository;
import com.example.cs4.customer.model.Customer;
import com.example.cs4.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;


@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ICustomerService iCustomerService;

    @Override
    public boolean save(Account account) {
        try {
            iAccountRepository.save(account);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Account findAccountByUserName(String username) {
        return iAccountRepository.findAccountByUserName(username);
    }

    @Override
    public Account findAccountById(Integer id) {
        return iAccountRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void softDeleteAccount(Integer accountId) {
        iAccountRepository.softDeleteAccount(accountId);
        iAccountRepository.softDeleteCustomer(accountId);
    }

    @Override
    public void activeAccount(Integer accountId) {
        iAccountRepository.activeAccount(accountId);
        iAccountRepository.activeCustomer(accountId);
    }

    @Override
    public void updatePassword(String password, Integer accountId) {
        iAccountRepository.updatePassword(password,accountId);
    }

    public void sendEmail(String email, String randomNumber) {
        String htmlLink = "http://localhost:8080/showFormActiveAccount";
        Customer customer = iCustomerService.findCustomerByEmail(email);
        String fromAddress = "trung11041990a1@gmail.com";
        String senderName = "DATSAN";
        String subject = "Thư Kích Hoạt Tài Khoản";
        String content = "<body style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;\">\n" +
                "      <p>Xin chào :<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> " + customer.getName() + "</span></p>\n" +
                "      <p>DATSAN xác nhận bạn đã đăng ký tài khoản thành công.</p>\n" +
                "      <ul>\n" +
                "        <li>Chỉ còn một bước nữa để sử dụng dịch vụ của DATSAN " + "</li>\n" +
                "        <li>Mã kích hoạt của bạn là: " + randomNumber+ "</li>\n" +
                "        <li><a href=\"" + htmlLink + "\">Nhấp vào đây để kích hoạt tài khoản</a></li>\n" +
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
            helper.setTo(email);
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
    public void sendEmailGetAccount(String email, String randomNumber) {
        String htmlLink = "http://localhost:8080/showFormGetAccount";
        Customer customer = iCustomerService.findCustomerByEmail(email);
        Account account = findAccountById(customer.getAccount().getAccountId());
        String fromAddress = "trung11041990a1@gmail.com";
        String senderName = "DATSAN";
        String subject = "Thư lấy lại Tài Khoản";
        String content = "<body style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;\">\n" +
                "      <p>Xin chào :<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> " + account.getUserName() + "</span></p>\n" +
                "      <ul>\n" +
                "        <li>Để có thể lấy lại tài khoản tại DATSAN " + "</li>\n" +
                "        <li>Mã lấy lại tài khoản của bạn là: " + randomNumber + "</li>\n" +
                "        <li><a href=\"" + htmlLink + "\">Nhấp vào đây và nhập mã lấy lại tài khoản </a></li>\n" +
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
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true); // Sử dụng HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);

    }
}
