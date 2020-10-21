package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.bridgeLabs.csvHandler.CsvBuilderFactory;
import com.bridgeLabs.csvHandler.CsvException;
import com.bridgeLabs.csvHandler.ICsvBuilder;
import com.bridgeLabs.csvHandler.CsvExceptionType;

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = CsvBuilderFactory.createBuilder();
			Iterator<CSVStateCensus> censusCsvIterator = csvBuilder.getIteratorFromCsv(reader, CSVStateCensus.class);
			return getCountFromIterator(censusCsvIterator);
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadStateCodesData(String csvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = CsvBuilderFactory.createBuilder();
			Iterator<CSVStates> codesCsvIterator = csvBuilder.getIteratorFromCsv(reader, CSVStates.class);
			return getCountFromIterator(codesCsvIterator);
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public <T> int getCountFromIterator(Iterator<T> csvIterator) {
		Iterable<T> csvIterable = () -> csvIterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
}