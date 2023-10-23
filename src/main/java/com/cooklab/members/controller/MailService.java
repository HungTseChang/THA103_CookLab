package com.cooklab.members.controller;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

	        // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	        // ●1) 登入你的Gmail的: 
	        // ●2) 點選【管理你的 Google 帳戶】
	        // ●3) 點選左側的【安全性】
	       
	        // ●4) 完成【兩步驟驗證】的所有要求如下:
	        //     ●4-1) (請自行依照步驟要求操作之.....)
	       
	        // ●5) 完成【應用程式密碼】的所有要求如下:
	        //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
	        //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
	        //     ●5-3) 最後按【產生】密碼
			final String myGmail = "tommy76190@gmail.com";
			final String myGmail_password = "cbmjiuewunwjohjg";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {

		String to = "martin80699@gmail.com";

		String subject = "密碼通知";

		String ch_name = "peter1";
		String passRandom = genAuthCode();
		String messageText =  ch_name +" 您好!\n\n["+ passRandom +"]\n\n為您在廚藝實驗室(CookLab)的驗證碼，請於10分鐘內輸入" +"\n" ;

		MailService mailService = new MailService();
		mailService.sendMail(to, subject, messageText);
	}
	
	public static String genAuthCode() {
		
		String verificationCode="";
		int ret;
		for(int i = 0;i<8;i++)
		{
			ret=(int) (Math.random() * 3);
			switch(ret)
			{
				//數字 Unicode:48~57
				case 0:
					ret=((int) (Math.random() * 10))+ 48;
					break;
				//大寫英文 Unicode:65~90
				case 1:
					ret=((int) (Math.random() * 26))+ 65;
					break;
				//小寫英文 Unicode:97~122
				case 2:
					ret=((int) (Math.random() * 26))+ 97;
					break;
			}
			verificationCode += (char)ret;
		}
		return verificationCode;
	}

}
