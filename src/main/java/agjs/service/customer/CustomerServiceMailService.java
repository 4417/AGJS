package agjs.service.customer;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class CustomerServiceMailService {
	private final static String HOST = "smtp.gmail.com";
	private final static String AUTH = "true";
	private final static String PORT = "587";
	private final static String STARTTLE_ENABLE = "true";
	private final static String SENDER = "tga10204agjs@gmail.com";
	private final static String PASSWORD = "xrnsfkxguyaloerh";

//  設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String recipients, String mailSubject, String mailBody) {
//		String recipientCcs = "副本mail";
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", STARTTLE_ENABLE);
		props.put("mail.smtp.ssl.trust", HOST);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

//      設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER, PASSWORD);
			}
		};

		Session session = Session.getDefaultInstance(props, authenticator);
		Message message = new MimeMessage(session);

		try {
//			設定Email Message start

//			設定寄件人、收件人、副本、主旨
			message.setSentDate(new Date());
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientCcs));
//          https://javaee.github.io/javamail/docs/api/javax/mail/internet/MimeUtility.html#encodeText-java.lang.String-java.lang.String-java.lang.String- (第三個參數參考API文件)
			message.setSubject(MimeUtility.encodeText(mailSubject, StandardCharsets.UTF_8.toString(), "B"));

//			first part (text)
			MimeBodyPart messageBody = new MimeBodyPart();
			messageBody.setContent(mailBody, "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);

			message.setContent(multipart);

//   		寄出email
			Transport transport = session.getTransport("smtp");
			try {
				transport.connect();
				transport.sendMessage(message, message.getAllRecipients());
			} finally {
				transport.close();
			}

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
