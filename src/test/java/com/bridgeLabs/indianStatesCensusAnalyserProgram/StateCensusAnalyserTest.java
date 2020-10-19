package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {
	public static final String RIGHT_CENSUS_CSV = "src/main/resources/India-Census-Data.csv";
	public static final String WRONG_CENSUS_CSV = "src/main/resources/India-Census-Date.csv";
	public static final String WRONGTYPE_CENSUS_CSV = "src/main/resources/India-Census-Data-WrongType.csv";
	public static final String WRONGDELIMITER_CENSUS_CSV = "src/main/resources/India-Census-Data-WrongDelimiter.csv";
	public static final String WRONGHEADER_CENSUS_CSV = "src/main/resources/India-Census-Data-WrongHeader.csv";

	@Test
	public void givenIndiaCensusDataCsvShouldReturnExactCount() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int recordsCount = stateCensusAnalyser.loadStateCensusData(RIGHT_CENSUS_CSV);
			Assert.assertEquals(640, recordsCount);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void givenWrongCsvFileShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
			ExpectedException exceptionRule= ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONG_CENSUS_CSV);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}
	
	@Test
	public void givenWrongTypeCsvFileShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
			ExpectedException exceptionRule= ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGTYPE_CENSUS_CSV);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectDelimiterShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {
			StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
			ExpectedException exceptionRule= ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGDELIMITER_CENSUS_CSV);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_DELIMITER, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectHeaderShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {
			StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
			ExpectedException exceptionRule= ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGHEADER_CENSUS_CSV);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_HEADER, e.exceptionType);
		}
	}

}
