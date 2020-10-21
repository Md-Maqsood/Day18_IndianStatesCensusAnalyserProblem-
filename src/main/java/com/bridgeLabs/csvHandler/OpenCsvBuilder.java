package com.bridgeLabs.csvHandler;

import java.io.Reader;
import java.util.Iterator;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class OpenCsvBuilder implements ICsvBuilder {
	public <T> Iterator<T> getIteratorFromCsv(Reader reader, Class<T> csvBindedClass) throws CsvException {
		try {
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvBindedClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<T> csvToBean = csvToBeanBuilder.build();
			Iterator<T> censusCsvIterator = csvToBean.iterator();
			return censusCsvIterator;
		} catch (RuntimeException e) {
			if (ExceptionUtils.indexOfType(e, CsvDataTypeMismatchException.class) != -1) {
				throw new CsvException("Incorrect Type", CsvExceptionType.INCORRECT_TYPE);
			} else if (ExceptionUtils.indexOfType(e, CsvRequiredFieldEmptyException.class) != -1) {
				if (e.getMessage().equals("Error capturing CSV header!")) {
					throw new CsvException("Incorrect Header", CsvExceptionType.INCORRECT_HEADER);
				} else {
					throw new CsvException("Incorrect Delimiter", CsvExceptionType.INCORRECT_DELIMITER);
				}
			} else {
				System.out.println(e.getMessage());
				e.printStackTrace();
				throw e;
			}
		}
	}
}
