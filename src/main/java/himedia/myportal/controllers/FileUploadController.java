package himedia.myportal.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@GetMapping({"","/form"})
	public String form() {
		return "fileupload/form";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file1") MultipartFile file1) {
		if (file1 != null) {
			logger.debug("OriginalFileName: " + file1.getOriginalFilename());
			logger.debug("File Size: " + file1.getSize());
		}
		
		return "<h1>Upload Complete</h1>";
	}
}
