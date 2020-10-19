package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class StateCensusAnalyser<T> {
	public int loadStateCensusData(String csvFilePath, Class csvBindedClass) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvBindedClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<T> csvToBean = csvToBeanBuilder.build();
			Iterator<T> censusCsvIterator = csvToBean.iterator();
			Iterable<T> csvIterable = () -> censusCsvIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException("Incorrect CSV File", CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			if (ExceptionUtils.indexOfType(e, CsvDataTypeMismatchException.class) != -1) {
				throw new CensusAnalyserException("Incorrect Type", CensusAnalyserExceptionType.INCORRECT_TYPE);
			} else if (ExceptionUtils.indexOfType(e, CsvRequiredFieldEmptyException.class) != -1) {
				if(e.getMessage().equals("Error capturing CSV header!")) {
					throw new CensusAnalyserException("Incorrect Header", CensusAnalyserExceptionType.INCORRECT_HEADER);
				}else {
					throw new CensusAnalyserException("Incorrect Delimiter",
						CensusAnalyserExceptionType.INCORRECT_DELIMITER);
				}
			} else {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
}