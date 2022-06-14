package poly.edu.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MailModel {
	private String from = "locptpd04185@fpt.edu.vn";
	private String to;
	private String subject;
	private String body;

	List<String> cc = new ArrayList<>();
	List<String> bcc = new ArrayList<>();
	List<File> files = new ArrayList<>();

	public MailModel(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

}
