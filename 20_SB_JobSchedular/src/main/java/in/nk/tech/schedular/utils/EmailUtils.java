package in.nk.tech.schedular.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to) {
		boolean status = false;
		
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			
			helper.setTo(to);
			helper.setSubject("Test Schedular");
			helper.setText("<h2>Testing Job Schedular</h2>", true);
			
			mailSender.send(msg);
			
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
