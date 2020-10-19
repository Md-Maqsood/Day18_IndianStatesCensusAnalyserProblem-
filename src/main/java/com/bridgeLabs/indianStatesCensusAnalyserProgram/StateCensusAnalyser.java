package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean=csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusCsvIterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvIterable = () -> censusCsvIterator;
			int numOfEntries=(int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		}catch(IOException e) {
			throw new CensusAnalyserException("Incorrect CSV File", CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
}