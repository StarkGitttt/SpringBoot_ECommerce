package poly.edu.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {

	@Autowired
	HttpServletRequest request;

	@Autowired
	ServletContext app;

	public String getString(String name, String defaultValue) {
		String getValue = request.getParameter(name);
		return getValue == null ? defaultValue : getValue;
	}

	public int getInt(String name, int defaultValue) {
		String getValue = request.getParameter(name);
		return getValue == null ? defaultValue : Integer.parseInt(getValue);
	}

	public double getDouble(String name, double defaultValue) {
		String getValue = request.getParameter(name);
		return getValue == null ? defaultValue : Double.parseDouble(getValue);
	}

	public boolean getBoolean(String name) {
		String getValue = request.getParameter(name);
		return getValue == null ? false : Boolean.parseBoolean(getValue);
	}

	public Date getDate(String name, String pattern) {
		String getValue = request.getParameter(name);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date = new Date();
		try {
			date = getValue == null ? new Date() : formatter.parse(getValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public void saveFile(MultipartFile attach, String folder) {
		String fileName = attach.getOriginalFilename();
		File dir = new File(app.getRealPath("/WEB-INF/views/" + folder + fileName));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			attach.transferTo(dir);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
