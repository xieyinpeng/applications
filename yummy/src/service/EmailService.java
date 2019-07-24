package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	Map<String,String> codeMap;
	Session session;
	
	private void init() {
		Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.sohu.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        session = Session.getInstance(prop);
        codeMap=new HashMap<String,String>();
	}
	
	private void send(String address,String content) {
		Transport ts;
		try {
			ts = session.getTransport();
	        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect("smtp.qq.com", "593955515", "pwondthynmokbfje");
			 //创建邮件对象
	        MimeMessage message = new MimeMessage(session);
	        //指明邮件的发件人
	        message.setFrom(new InternetAddress("593955515@qq.com"));
	        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
	        //邮件的标题
	        message.setSubject("来自Yummy!!的邮件");
	        //邮件的文本内容
	        message.setContent(content, "text/html;charset=UTF-8");
	        ts.sendMessage(message, message.getAllRecipients());
	        ts.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};
	
	public void sendCode(String email) {
		SecurityService service=new SecurityService();
		String code=service.createRandomString(4);
		send(email,code);
		codeMap.put(email, code);
	}
	
	public String getCode(String email) {
		return codeMap.get(email);
	}
	
	private static EmailService service;
	
	private EmailService() {
		init();
	}
	
	public static EmailService getInstance() {
		if(service==null) {
			service=new EmailService();
		}
		return service;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			EmailService.getInstance().sendCode("793046005@qq.com");
		}
	}
}
