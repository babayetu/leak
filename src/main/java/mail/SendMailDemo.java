package mail;

import javax.mail.MessagingException;

public class SendMailDemo {
	public static void main(String[] args) {
		try {
			new Mailer("PHX-EXMCS-VIP.corp.ebay.com", "true", "corp\\jingjliu", "jingjliu@paypal.com", "Mayflower2k").send(new String[] { "jingjliu@paypal.com" }, null, null, "demo_title", "<h3>test</h3>");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}