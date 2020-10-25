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
	public int loadStateCensusData(String csvFilePath, CsvBuilderType csvBuilderType) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
					: CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			return censusCsvList.size();
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public String getStateWiseSortedCensusData(String csvFilePath, CsvBuilderType csvBuilderType) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
					: CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			Function<CSVStateCensus, String> keyForComparison=census->census.stateName;
			Comparator<CSVStateCensus> censusComparator=Comparator.comparing(keyForComparison);
			this.sort(censusCsvList, censusComparator);
			String sortedStateCensusJson=new Gson().toJson(censusCsvList);
			return sortedStateCensusJson;
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	private void sort(List<CSVStateCensus> censusCsvList, Comparator<CSVStateCensus> censusComparator) {
		for(int i=0;i<censusCsvList.size()-1;i++) {
			for(int j=0; j<censusCsvList.size()-i-1;j++) {
				CSVStateCensus census1=censusCsvList.get(j);
				CSVStateCensus census2=censusCsvList.get(j+1);
				if(censusComparator.compare(census1, census2)>0) {
					censusCsvList.set(j, census2);
					censusCsvList.set(j+1, census1);
				}
			}
		}
	}
	
	public int loadStateCodesData(String csvFilePath, CsvBuilderType csvBuilderType) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
					: CsvBuilderFactory.createBuilderCommons();
			List<CSVStates> codesCsvList = csvBuilder.getListFromCsv(reader, CSVStates.class);
			return codesCsvList.size();
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