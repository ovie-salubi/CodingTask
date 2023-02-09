package com.csv.codingtask.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.csv.codingtask.helper.CSVDataHelper;
import com.csv.codingtask.model.CSVData;
import com.csv.codingtask.response.Response;
import com.csv.codingtask.service.CSVDataService;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVDataController {

	@Autowired
	CSVDataService csvDataService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public ResponseEntity<Response> uploadCSVFile(@RequestParam("file") MultipartFile file) {
		logger.info("Method Execution: {}", "uploadCSVFile");
		String message = "";

		if (CSVDataHelper.hasCSVFormat(file)) {
			try {
				csvDataService.save(file);

				message = "File uploaded successfully: " + file.getOriginalFilename();

				return ResponseEntity.status(HttpStatus.OK).body(new Response(message));

			} catch (Exception e) {
				message = "File upload failed: " + file.getOriginalFilename() + "!";

				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
			}
		}

		message = "Please upload the csv file";

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(message));
	}

	/**
	 * 
	 * @param primaryKey
	 * @return
	 */
	@GetMapping("/{primaryKey}")
	public ResponseEntity<CSVData> findCSVDataByPrimaryKey(@PathVariable String primaryKey) {
		logger.info("Method Execution: {}", "findCSVDataByPrimaryKey");
		try {
			Optional<CSVData> csvDataWrapper = csvDataService.getCSVDataByPrimaryKey(primaryKey);

			if (csvDataWrapper.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(csvDataWrapper.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param primaryKey
	 * @return
	 */
	@DeleteMapping(value = "/{primaryKey}")
	public ResponseEntity<String> deleteCSVData(@PathVariable String primaryKey) {
		logger.info("Method Execution: {}", "deleteCSVData");

		boolean isRemoved = csvDataService.deleteCSVDataByPrimaryKey(primaryKey);

		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(primaryKey, HttpStatus.OK);
	}

}
