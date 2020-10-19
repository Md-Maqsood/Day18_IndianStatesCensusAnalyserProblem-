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
	public static final String RIGHT_STATE_CODES_CSV = "src/main/resources/India-State-Codes.csv";
	public static final String WRONG_STATE_CODES_CSV = "src/main/resources/India-Stata-Codes.csv";
	public static final String WRONGTYPE_STATE_CODES_CSV = "src/main/resources/India-State-Codes-WrongType.csv";
	public static final String WRONGDELIMITER_STATE_CODES_CSV = "src/main/resources/India-State-Codes-WrongDelimiter.csv";
	public static final String WRONGHEADER_STATE_CODES_CSV = "src/main/resources/India-State-Codes-WrongHeader.csv";

	@Test
	public void givenIndiaCensusDataCsvShouldReturnExactCount() {
		StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
		try {
			int recordsCount = stateCensusAnalyser.loadStateCensusData(RIGHT_CENSUS_CSV, CSVStateCensus.class);
			Assert.assertEquals(640, recordsCount);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenWrongIndiaCensusCsvFileShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONG_CENSUS_CSV, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}

	@Test
	public void givenWrongTypeIndiaCensusCsvFileShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGTYPE_CENSUS_CSV, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}

	@Test
	public void givenIndiaCensusCsvFileIncorrectDelimiterShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {
			StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGDELIMITER_CENSUS_CSV, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_DELIMITER, e.exceptionType);
		}
	}

	@Test
	public void givenIndiaCensusCsvFileIncorrectHeaderShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {
			StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGHEADER_CENSUS_CSV, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_HEADER, e.exceptionType);
		}
	}

	@Test
	public void givenIndiaStateCodesCsvShouldReturnExactCount() {
		StateCensusAnalyser<CSVStates> stateCensusAnalyser = new StateCensusAnalyser<>();
		try {
			int recordsCount = stateCensusAnalyser.loadStateCensusData(RIGHT_STATE_CODES_CSV, CSVStates.class);
			Assert.assertEquals(32, recordsCount);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenWrongStateCodeCsvFileShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			StateCensusAnalyser<CSVStates> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONG_STATE_CODES_CSV, CSVStates.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}

	@Test
	public void givenWrongTypeStateCodeCsvFileShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			StateCensusAnalyser<CSVStates> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGTYPE_STATE_CODES_CSV, CSVStates.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}

	@Test
	public void givenStateCodesCsvFileIncorrectDelimiterShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {
			StateCensusAnalyser<CSVStates> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGDELIMITER_STATE_CODES_CSV, CSVStates.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_DELIMITER, e.exceptionType);
		}
	}

	@Test
	public void givenStateCodesCsvFileIncorrectHeaderShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {
			StateCensusAnalyser<CSVStates> stateCensusAnalyser = new StateCensusAnalyser<>();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGHEADER_STATE_CODES_CSV, CSVStates.class);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.INCORRECT_HEADER, e.exceptionType);
		}
	}
}
