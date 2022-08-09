package agjs.service.impl.user;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.user.UserPo;
import agjs.service.user.RegisterMailService;
import redis.clients.jedis.Jedis;

@Service
public class RegisterMailServiceImpl implements RegisterMailService {
	private final static String HOST = "smtp.gmail.com";
	private final static String AUTH = "true";
	private final static String PORT = "587";
	private final static String STARTTLE_ENABLE = "true";
	private final static String SENDER = "tga10204agjs@gmail.com";
	private final static String PASSWORD = "xrnsfkxguyaloerh";
	private Jedis jedis = new Jedis("localhost", 6379);

//  設定傳送郵件:Email信箱、主旨、內容
	public static void mail(String recipients, String mailSubject, String mailBody) {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", STARTTLE_ENABLE);
		props.put("mail.smtp.ssl.trust", HOST);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

//      設定傳送者郵件的帳號與密碼
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER, PASSWORD);
			}
		};

		Session session = Session.getDefaultInstance(props, authenticator);
		Message message = new MimeMessage(session);

		try {
//			開始設定Email Message

//			設定寄件人、收件人、副本、主旨
			message.setSentDate(new Date());
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.setSubject(MimeUtility.encodeText(mailSubject, StandardCharsets.UTF_8.toString(), "B"));

//			設定文字內容
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

	
	@Override
	public void sendMail(UserPo user) {
		
		//寄驗證信至會員所填的信箱
		String to = user.getUserEmail();
		String subject = "AGJS會員驗證碼通知";
		String ch_name = user.getUserName();
		String verifyRandom = returnAuthCode();
		System.out.println("Auth code is: " + verifyRandom);
		
		String key= "VerifyCode" + user.getUserEmail() + ":count";
		jedis.set(key, verifyRandom);
		jedis.expire(key, 300);
		String messageText = "您好！ " + ch_name + " 您的驗證碼為: " + verifyRandom + "\n" + "超過5分鐘後此筆驗證碼將失效，請於時間內回到網頁驗證以完成註冊，謝謝！";
		mail(to, subject, messageText);
	}
	
	
	@Override
	public UserPo verifyJedis(UserPo user) {
		String str = user.getVerifyMsg();
		String key= "VerifyCode" + user.getUserEmail() + ":count";
		// 會員回到網站輸入驗證碼，後端判斷驗證碼是否已超時
		String tempAuth = jedis.get(key);
		System.out.println("jedis: " + tempAuth);
		if (tempAuth == null) {
			user.setVerifyMsg("連結信已逾時，請重新申請");
		} else if (str.equals(tempAuth)){
			user.setEmailVerifyStatus(true);
			user.setVerifyMsg(null);
		} else {
			user.setVerifyMsg("驗證有誤，請重新輸入");
		}

		jedis.close();
		return user;
		
	}
	
	//產生隨機驗證碼8位，包含英文大寫、小寫、數字
	private static String returnAuthCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			//三種情境random
			int condition = (int) (Math.random() * 3) + 1;
			switch (condition) {
			//英文大寫random
			case 1:
				char c1 = (char)((int)(Math.random() * 26) + 65);
				sb.append(c1);
				break;
			//英文小寫random
			case 2:
				char c2 = (char)((int)(Math.random() * 26) + 97);
				sb.append(c2);
				break;
			//數字random
			case 3:
				sb.append((int)(Math.random() * 10));
			}
		}
		return sb.toString();
	}

	//main方法用來自己測試用
//	public static void main(String args[]) {
//
//		String to = "t8i2n6a14@gmail.com";
//
//		String subject = "密碼通知";
//
//		String ch_name = "David";
//		String passRandom = "111";
//		String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";
//
//		RegisterMailServiceImpl mailService = new RegisterMailServiceImpl();
//		mailService.sendMail(to, subject, messageText);
//
//	}

}
