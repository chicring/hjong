package com.hjong.util;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MailUtils {
    @Resource
    JavaMailSender sender;

    public Boolean sendVerifyCode(String email,int code){
        if(isValidEmail(email)){
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            try {
                helper.setFrom("c.jong@qq.com");
                helper.setTo(email);
                helper.setSubject("驼鹿快传");
                helper.setText("<html><body><center>" +
                        "<h1>欢迎来到驼鹿快传</h1>" +
                        "<img src='http://lsky.hjong.cn/i/2023/11/13/65519f0c9bc5e.png' height='80' width='80' alt='Icon'>" +
                        "<br> <p>请确认是否是你本人操作，以确保账户安全：</p> <br>" +
                        "<h4>如若是请使用此验证码：</h4> " +
                        "<h2>"+code+"</h2> <br>" +
                        "<h5>验证码5分钟有效</h5> "+
                        "<p>如非本人操作，请立即更改密码。</p> "+
                        "</center></body></html>", true);

                sender.send(mimeMessage);
                return true;
            } catch (Exception e) {
                return false;
            }
        }else {
            return false;
        }

    }

    //判断是不是邮箱地址
    private boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
