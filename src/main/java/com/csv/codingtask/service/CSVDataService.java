package com.csv.codingtask.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csv.codingtask.helper.CSVDataHelper;
import com.csv.codingtask.model.CSVData;
import com.csv.codingtask.repository.CSVDataRepository;

@Service
public class CSVDataService {

	@Autowired
	CSVDataRepository repository;

	/**
	 * 
	 * @param file
	 */
	public void save(MultipartFile file) {
		try {
			List<CSVData> csvDataList = CSVDataHelper.readCSVDataFromCSVFile(file.getInputStream());
			repository.saveAll(csvDataList);
			
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param primaryKey
	 * @return
	 */
	public Optional<CSVData> getCSVDataByPrimaryKey(String primaryKey) {
		return repository.findById(primaryKey);
	}

	/**
	 * 
	 * @param primaryKey
	 */
	public boolean deleteCSVDataByPrimaryKey(String primaryKey) {
		if (getCSVDataByPrimaryKey(primaryKey) != null) {
			repository.deleteById(primaryKey);

			return true;
		}

		return false;
	}

}
