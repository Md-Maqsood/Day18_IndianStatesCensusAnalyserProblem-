package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bridgeLabs.csvHandler.CsvException;
import com.bridgeLabs.csvHandler.CsvExceptionType;

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
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int recordsCount = stateCensusAnalyser.loadStateCensusData(RIGHT_CENSUS_CSV,CsvBuilderType.OPEN_CSV);
			Assert.assertEquals(28, recordsCount);
		} catch (CsvException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenWrongIndiaCensusCsvFileShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCensusData(WRONG_CENSUS_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}

	@Test
	public void givenWrongTypeIndiaCensusCsvFileShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGTYPE_CENSUS_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}

	@Test
	public void givenIndiaCensusCsvFileIncorrectDelimiterShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGDELIMITER_CENSUS_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.INCORRECT_DELIMITER, e.exceptionType);
		}
	}

	@Test
	public void givenIndiaCensusCsvFileIncorrectHeaderShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCensusData(WRONGHEADER_CENSUS_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.INCORRECT_HEADER, e.exceptionType);
		}
	}

	@Test
	public void givenIndiaStateCodesCsvShouldReturnExactCount() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int recordsCount = stateCensusAnalyser.loadStateCodesData(RIGHT_STATE_CODES_CSV,CsvBuilderType.OPEN_CSV);
			Assert.assertEquals(32, recordsCount);
		} catch (CsvException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenWrongStateCodeCsvFileShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCodesData(WRONG_STATE_CODES_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}

	@Test
	public void givenWrongTypeStateCodeCsvFileShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCodesData(WRONGTYPE_STATE_CODES_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}

	@Test
	public void givenStateCodesCsvFileIncorrectDelimiterShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCodesData(WRONGDELIMITER_STATE_CODES_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.INCORRECT_DELIMITER, e.exceptionType);
		}
	}

	@Test
	public void givenStateCodesCsvFileIncorrectHeaderShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CsvException.class);
			stateCensusAnalyser.loadStateCodesData(WRONGHEADER_STATE_CODES_CSV,CsvBuilderType.OPEN_CSV);
		} catch (CsvException e) {
			Assert.assertEquals(CsvExceptionType.INCORRECT_HEADER, e.exceptionType);
		}
	}
}

	
