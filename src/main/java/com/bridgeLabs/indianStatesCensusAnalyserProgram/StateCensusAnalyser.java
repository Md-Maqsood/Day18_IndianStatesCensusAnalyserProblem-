package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import com.bridgeLabs.csvHandler.CsvBuilderFactory;
import com.bridgeLabs.csvHandler.CsvException;
import com.bridgeLabs.csvHandler.ICsvBuilder;
import com.google.gson.Gson;
import com.bridgeLabs.csvHandler.CsvExceptionType;

public class StateCensusAnalyser {
	public static final String RIGHT_CENSUS_CSV = "src/main/resources/India-Census-Data.csv";
	public static final String RIGHT_STATE_CODES_CSV = "src/main/resources/India-State-Codes.csv";

	public List<CSVStateCensus> loadStateCensusData(String csvFilePath, CsvBuilderType csvBuilderType)
			throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
					: CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			return censusCsvList;
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public String getSortedCensusData(String csvFilePath, CsvBuilderType csvBuilderType,
			SortByParameter sortByParameter, SortOrder sortOrder) throws CsvException {
		try {
			List<CSVStateCensus> censusCsvList = loadStateCensusData(csvFilePath, csvBuilderType);
			Function keyForComparison = getKeyForComparison(csvFilePath, csvBuilderType, sortByParameter);
			Comparator<CSVStateCensus> censusComparator = Comparator.comparing(keyForComparison);
			this.sort(censusCsvList, censusComparator, sortOrder);
			String sortedStateCensusJson = new Gson().toJson(censusCsvList);
			return sortedStateCensusJson;
		} catch (NullPointerException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	private Function getKeyForComparison(String csvFilePath, CsvBuilderType csvBuilderType,
			SortByParameter sortByParameter) throws CsvException {
		switch (sortByParameter) {
		case STATE_NAME: {
			Function<CSVStateCensus, String> keyForComparison = censusState -> censusState.stateName;
			return keyForComparison;
		}
		case STATE_CODE: {
			try {
				List<CSVStates> codesCsvList = loadStateCodesData(RIGHT_STATE_CODES_CSV, csvBuilderType);
				Function<CSVStateCensus, String> keyForComparison = censusState -> {
					for (CSVStates codesState : codesCsvList) {
						if (codesState.stateName.equalsIgnoreCase(censusState.stateName)) {
							return codesState.stateCode;
						}
					}
					return null;
				};
				return keyForComparison;
			} catch (NullPointerException e) {
				throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
			}
		}
		}
		return null;
	}

	private void sort(List<CSVStateCensus> censusCsvList, Comparator<CSVStateCensus> censusComparator,
			SortOrder sortOrder) {
		for (int i = 0; i < censusCsvList.size() - 1; i++) {
			for (int j = 0; j < censusCsvList.size() - i - 1; j++) {
				CSVStateCensus census1 = censusCsvList.get(j);
				CSVStateCensus census2 = censusCsvList.get(j + 1);
				int comarisonValue = sortOrder == SortOrder.ASCENDING ? censusComparator.compare(census1, census2)
						: censusComparator.compare(census2, census1);
				if (comarisonValue > 0) {
					censusCsvList.set(j, census2);
					censusCsvList.set(j + 1, census1);
				}
			}
		}
	}

	public List<CSVStates> loadStateCodesData(String csvFilePath, CsvBuilderType csvBuilderType) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
					: CsvBuilderFactory.createBuilderCommons();
			List<CSVStates> codesCsvList = csvBuilder.getListFromCsv(reader, CSVStates.class);
			return codesCsvList;
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