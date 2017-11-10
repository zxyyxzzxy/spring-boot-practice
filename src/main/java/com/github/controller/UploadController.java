package com.github.controller;

import com.github.util.SpringContextHolder;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

@RestController
@RequestMapping("upload")
public class UploadController {

	@CrossOrigin
	@RequestMapping(method= RequestMethod.POST)
	public String upload(@Value("${spring.web.upload-location}") String uploadPath, @RequestParam MultipartFile uploadFile, String directory) throws Exception {

		File file = new File(uploadPath);
		if (StringUtils.isNotEmpty(directory)) {
			file = new File(file, directory);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
    	String fileName = System.currentTimeMillis() + new Random().nextInt(10) + "." + FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		file = new File(file, fileName);
		uploadFile.transferTo(file);
		return "/" + file.getAbsolutePath().replace(uploadPath, "");
	}
    
}
