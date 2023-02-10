//package Running;
//
//
//
//import org.apache.commons.mail.DefaultAuthenticator;
//
//import org.apache.commons.mail.EmailAttachment;
//import org.apache.commons.mail.MultiPartEmail;
//
//
//
//public class Sending {
//public void mail(String file) throws Exception{
//	ReadProp rp=new ReadProp();
////	  String p=rp.getPath().getProperty("path");
//	 EmailAttachment attachment = new EmailAttachment();
//	  attachment.setPath(file);
//	  attachment.setDisposition(EmailAttachment.ATTACHMENT);
//	  attachment.setDescription("Kohler Report");
//	  attachment.setName("Automation report");
//
//	  // Create the email message
//	  MultiPartEmail email = new MultiPartEmail();
//	  email.setHostName("smtp.miraclesoft.com");
//	  email.setSmtpPort(587);
//	  email.setAuthenticator(new DefaultAuthenticator(rp.getPath().getProperty("mailFrom"), rp.getPath().getProperty("mailPassword")));
//	  email.setSSLOnConnect(true);
//	  
//	  
//	  	for(int i=1;i<=50;i++){
//	  		String mailTo=rp.getPath().getProperty("mailTo"+i);
//	  		if(mailTo!=null){
//	  		System.out.println(mailTo);
//	  		 email.addTo(mailTo);
//	  		}
//	  		else{
//	  			break;
//	  		}
//	  	}
//	
//	  email.setFrom(rp.getPath().getProperty("mailFrom"));
//	  email.setSubject(rp.getPath().getProperty("mailSubject"));
//	  email.setMsg("Automatic Email Reporting. Please find the attachment regarding Test case report");
//
//	  // add the attachment
//	  email.attach(attachment);
//
//	  // send the email
//	  email.send();
//}
//
//}
