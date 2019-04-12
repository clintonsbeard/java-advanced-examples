package com.techelevator.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/*
 * To use the MultipartFile you must add entries to your pom.xm  (check this projects pom.xml for and example)
 * You must also add a bean to the springmvc-servlet.xml  (check this projects springmvc-servelet.xml for an example)
 * 
 * The Results Page and CSV parsing of this example is meant to work with the test.csv file found in this project.  To 
 * use your own CSV file you will need to update those parts to properly parse your file and display or use it.
 */

import com.techelevator.model.CsvFileEntry;
@Controller
public class uploadController {

	// Required to get Context information about the Tomcat Servlet container
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(path= {"/", "/upload"}, method=RequestMethod.GET)
	public String showUploadForm() {
		return "uploadForm";
	}
	
	/*
	 * If you are just uploading a file them you can use MultipartFile as a RequestParam
	 * If you need other information from your HTML FORM to be included in the upload then the parameter for this
	 * method should be an object that matches the form values, including the MultipartFile object.
	 */
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam MultipartFile csvFile, ModelMap map) {
		
		// Get a Path to store the file that can be written to by Tomcat
		File serverFilePath = getServerFilePath();
		String serverFileName = serverFilePath + File.separator + "csvUpload";
		
		// Make sure the file has content, otherwise return an error to the user
		if (csvFile.isEmpty()) {
			map.addAttribute("message", "File Object empty");
		} else {
			// If it exists, handle the file
			List<CsvFileEntry> csvFileEntries = handleCsvFile(csvFile, serverFileName);		
			/*
			 * This example just writes the Entry values to a result page, but now that you have the CSV file in 
			 * a List of Java objects, this is where you would do something with it, like save it do a Database
			 */
			map.addAttribute("csvFileEntries", csvFileEntries);
			
		}
		map.addAttribute("serverFilePath", serverFileName);
		
		return "showFile";
	}
	
	

	private File getServerFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	/*
	 * The RealPath of the Tomcat server will provide a folder that the web server can write to.  
	 * The addition of "uploads" is so our files are in their own folder in that location
	 */
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + "uploads";
	}
	
	private List<CsvFileEntry> handleCsvFile(MultipartFile file, String path) {
		
		// Write the CSV File to the disk on the server
		File csvFileOnServer = writeFileToDisk(file, path);
		
		// Load the CSV file into a list Java Beans
		List<CsvFileEntry> entries =  loadCsvEntriesFromServerFile(csvFileOnServer);
		
		// Clean up the temp file (unless you have reason to keep it)
		csvFileOnServer.delete();
		
		// Return the entries from the file
		return entries;
	}
	
	private File writeFileToDisk(MultipartFile file, String path) {
		
		// Create a file entry for the path on the Server
		File fileOnDisk = new File(path);
		try {
			// Transfer the MultipartFile's contents to the File, which writes it to the disk
			file.transferTo(fileOnDisk);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fileOnDisk;
	}
	
	private List<CsvFileEntry> loadCsvEntriesFromServerFile(File csvFileOnServer) {
		List<CsvFileEntry> csvFileEntries = new ArrayList<CsvFileEntry>();
		
		/*
		 * This is a simple solution and does not handle anything except a comma delimiter, does not handle quotes,
		 * and does not handle headers in the file.
		 * There are many better and more advanced solutions. 
		 */
		
		/*
		 * Test file format:  id,account_holder,account_type,value
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFileOnServer))) {
			
			String delimiter = ",";
			String line = "";
			
			while( (line = reader.readLine()) != null) {
				String parts[] = line.split(delimiter);
				
				csvFileEntries.add(new CsvFileEntry(parts[0], parts[1], parts[2], parts[3]));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return csvFileEntries;
	}
	
	
}
