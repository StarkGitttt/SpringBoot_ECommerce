package poly.edu.services;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	@Autowired
	ServletContext app;
	
	public void saveFile(MultipartFile attach, String folder) {
		String fileName = attach.getOriginalFilename();
		File dir = new File(app.getRealPath("/assets/" + folder + fileName));
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
