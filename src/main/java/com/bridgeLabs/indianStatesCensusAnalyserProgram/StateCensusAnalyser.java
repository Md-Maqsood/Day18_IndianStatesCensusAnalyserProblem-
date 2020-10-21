package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder=CsvBuilderFactory.createBuilder();
			Iterator<CSVStateCensus> censusCsvIterator = csvBuilder.getIteratorFromCsv(reader,
					CSVStateCensus.class);
			return getCountFromIterator(censusCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("Incorrect CSV File", CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	public int loadStateCodesData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder=CsvBuilderFactory.createBuilder();
			Iterator<CSVStates> codesCsvIterator = csvBuilder.getIteratorFromCsv(reader, CSVStates.class);
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
}