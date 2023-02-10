package Application;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
public boolean mail(Map<String,String> receivers,String user,String password,String smtp,String port,String subject,String message,List<String> attachFiles) {
	Properties prop=System.getProperties();
	prop.put("mail.smtp.host", smtp);
	prop.put("mail.smtp.port", port);
	prop.put("mail.smtp.auth", true);
	prop.put("mail.smtp.starttls.enable", "true");
	prop.put("mail.user", user);
	prop.put("mail.password", password);
	Authenticator auth=new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,password);
		}
	};
	Session session=Session.getInstance(prop, auth);
	try {
		Message msg = new MimeMessage(session);
//		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//		msg.addHeader("format", "flowed");
//		msg.addHeader("Content-Transfer-Encoding", "8bit");
		 
		msg.setFrom(new InternetAddress(user));

//		msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		msg.setSubject(subject);
		msg.setSentDate(new Date());
		int to=0,cc=0,bcc=0;
		for(Entry<String, String> entry : receivers.entrySet()) {
		    String type = entry.getValue().toString();
		    if(type.equalsIgnoreCase("TO")) {
		    	to++;
		    	}
		    else if(type.equalsIgnoreCase("CC")) {
		    	cc++;
		    }
		    else {
		    	bcc++;
		    }
		}
		InternetAddress[] mailAddress_TO = new InternetAddress[to];
		InternetAddress[] mailAddress_CC = new InternetAddress[cc];
		InternetAddress[] mailAddress_BCC = new InternetAddress[bcc];
		to=0;cc=0;bcc=0;
		for(Entry<String, String> entry : receivers.entrySet()) {
			 String rcv=entry.getKey().toString(); 
			 String type = entry.getValue().toString();
			    if(type.equalsIgnoreCase("TO")) {
			    	mailAddress_TO[to]=new InternetAddress(rcv);
			    	to++;
			    	}
			    else if(type.equalsIgnoreCase("CC")) {
			    	mailAddress_CC[cc]=new InternetAddress(rcv);
			    	cc++;
			    }
			    else {
			    	mailAddress_BCC[bcc]=new InternetAddress(rcv);
			    	bcc++;
			    }
			
		}
		System.out.println(mailAddress_TO);
		System.out.println(mailAddress_CC);
		if(to!=0)
		msg.addRecipients(Message.RecipientType.TO, mailAddress_TO);
		if(cc!=0)
		msg.addRecipients(Message.RecipientType.CC, mailAddress_CC);
		if(bcc!=0)
		msg.addRecipients(Message.RecipientType.BCC, mailAddress_BCC);
		
		
		 BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         messageBodyPart.setContent(message, "text/html");
                  
         // Create a multipart message for attachment
         Multipart multipart = new MimeMultipart();
         
         if(attachFiles.size()!=0) {
        	 for(String filePath : attachFiles) {
         MimeBodyPart attachment=new MimeBodyPart();
         attachment.attachFile(new File(filePath));
         multipart.addBodyPart(attachment);
        	 }
         }

         // Set text message part
         multipart.addBodyPart(messageBodyPart);
         
         // Send the complete message parts
         msg.setContent(multipart);
//         msg.setContent(message,"text/html");

         // Send message
         Transport.send(msg);
         System.out.println("EMail Sent Successfully with attachment!!");
         return true;
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} 
	
}
}
