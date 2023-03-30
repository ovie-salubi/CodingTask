package com.csv.codingtask;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import com.csv.codingtask.helper.CSVDataHelper;

class CodingtaskApplicationTests {

	@Test
	void test_isPrimaryKeyNullOrBlank() {		
		assertTrue(CSVDataHelper.isPrimaryKeyNullOrBlank(null));
		assertTrue(CSVDataHelper.isPrimaryKeyNullOrBlank(" "));
		assertTrue(CSVDataHelper.isPrimaryKeyNullOrBlank(""));
		assertFalse(CSVDataHelper.isPrimaryKeyNullOrBlank("PRIMARY_KEY"));
		
	}
	
	@Test
	void test_isUpdateTimeStampValueISO8601() {
		assertDoesNotThrow(() -> CSVDataHelper.isUpdateTimeStampValueISO8601("2020-11-16T19:00:00Z"));
		assertThrows(DateTimeParseException.class, () -> CSVDataHelper.isUpdateTimeStampValueISO8601("11-24-2023T19:00:"));
		assertThrows(DateTimeParseException.class, () -> CSVDataHelper.isUpdateTimeStampValueISO8601("11-24-2023"));
		assertDoesNotThrow(() -> CSVDataHelper.isUpdateTimeStampValueISO8601(""));
	}

}
