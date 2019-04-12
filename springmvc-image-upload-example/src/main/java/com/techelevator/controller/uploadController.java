package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class uploadController {

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(path= {"/", "/upload"}, method=RequestMethod.GET)
	public String showUploadForm() {
		return "uploadForm";
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam(required=false) String CSRF_TOKEN, @RequestParam MultipartFile file, ModelMap map) {
		
		File imagePath = getImageFilePath();
		String imageName = imagePath + File.separator + "testImage";
		
		if (file.isEmpty()) {
			map.addAttribute("message", "File Object empty");
		} else {
			createImage(file, imageName);
		}
		map.addAttribute("message", "uploaded to: " + imageName);
		return "showFile";
	}
	
	@RequestMapping(path="/image/{imageName}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value="imageName") String imageName) {
		String imagePath = getServerContextPath() + File.separator + imageName;
		File image = new File(imagePath);
		try {
			return Files.readAllBytes(image.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private File getImageFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + "uploads";
	}
	
	private void createImage(MultipartFile file, String name) {
		File image = new File(name);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
	
			stream.write(file.getBytes());
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
