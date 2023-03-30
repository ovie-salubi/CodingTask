package com.csv.codingtask.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.csv.codingtask.model.CSVData;

public class CSVDataHelper {
	public static final String TYPE = "text/csv";

	private static Logger logger = LoggerFactory.getLogger(CSVDataHelper.class);

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<CSVData> readCSVDataFromCSVFile(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<CSVData> csvDataList = new ArrayList<CSVData>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			CSVData csvData = null;
			for (CSVRecord csvRecord : csvRecords) {
				try {
					if (isPrimaryKeyNullOrBlank(csvRecord.get("PRIMARY_KEY"))) {
						logger.info("PRIMARY_KEY attribute must be a non blank string {}",
								csvRecord.get("PRIMARY_KEY"));
						continue;
					}
					
					isUpdateTimeStampValueISO8601(csvRecord.get("UPDATED_TIMESTAMP"));

					csvData = new CSVData(csvRecord.get("PRIMARY_KEY"), csvRecord.get("NAME"),
							csvRecord.get("DESCRIPTION"), csvRecord.get("UPDATED_TIMESTAMP"));

					csvDataList.add(csvData);

				} catch (DateTimeParseException ex) {
					logger.info("Invalid value for UPDATED_TIMESTAMP - {} for PRIMARY_KEY - {}",
							csvRecord.get("UPDATED_TIMESTAMP"), csvRecord.get("PRIMARY_KEY"));
				}
			}

			return csvDataList;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static boolean isPrimaryKeyNullOrBlank(String primaryKeyValue) {
		return primaryKeyValue == null
				|| primaryKeyValue.trim().isEmpty();
	}

	public static void isUpdateTimeStampValueISO8601(String updateTimeStampValue) throws DateTimeParseException {
		if (updateTimeStampValue != null
				&& !updateTimeStampValue.trim().isEmpty()) {
			OffsetDateTime.parse(updateTimeStampValue);
		}
	}

}
