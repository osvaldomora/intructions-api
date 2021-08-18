package mx.santander.fiduciarioplus.util;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class FileTool {

	public static String encode64(MultipartFile file) throws IOException {
		StringBuffer fileEncode = new StringBuffer();

		byte fileBytes[] = file.getBytes();
		String encodeBytes = Base64.getEncoder().encodeToString(fileBytes);
		fileEncode.append(encodeBytes);

		return fileEncode.toString();
	}
	
	public static String getFileExtension(String fullNameFile) {
		String extension = "";
		extension = fullNameFile.substring(fullNameFile.indexOf(".")+1);
		return extension;
	}

}