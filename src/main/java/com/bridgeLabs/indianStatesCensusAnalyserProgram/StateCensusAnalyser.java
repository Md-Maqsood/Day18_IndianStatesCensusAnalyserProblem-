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

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<CSVStateCensus> censusCsvIterator = getIteratorFromCsv(reader, CSVStateCensus.class);
			return getCountFromIterator(censusCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("Incorrect CSV File", CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadStateCodesData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<CSVStates> codesCsvIterator = getIteratorFromCsv(reader, CSVStates.class);
			return getCountFromIterator(codesCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("Incorrect CSV File", CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public <T> int getCountFromIterator(Iterator<T> csvIterator) {
		Iterable<T> csvIterable = () -> csvIterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}

	public <T> Iterator<T> getIteratorFromCsv(Reader reader, Class<T> csvBindedClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvBindedClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<T> csvToBean = csvToBeanBuilder.build();
			Iterator<T> censusCsvIterator = csvToBean.iterator();
			return censusCsvIterator;
		} catch (RuntimeException e) {
			if (ExceptionUtils.indexOfType(e, CsvDataTypeMismatchException.class) != -1) {
				throw new CensusAnalyserException("Incorrect Type", CensusAnalyserExceptionType.INCORRECT_TYPE);
			} else if (ExceptionUtils.indexOfType(e, CsvRequiredFieldEmptyException.class) != -1) {
				if (e.getMessage().equals("Error capturing CSV header!")) {
					throw new CensusAnalyserException("Incorrect Header", CensusAnalyserExceptionType.INCORRECT_HEADER);
				} else {
					throw new CensusAnalyserException("Incorrect Delimiter",
							CensusAnalyserExceptionType.INCORRECT_DELIMITER);
				}
			} else {
				System.out.println(e.getMessage());
				e.printStackTrace();
				throw e;
			}
		}
	}
}