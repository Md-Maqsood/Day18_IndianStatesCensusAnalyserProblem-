package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {
	public static final String RIGHT_CENSUS_CSV = "src/main/resources/India-Census-Data.csv";
	public static final String WRONG_CENSUS_CSV = "src/main/resources/India-Census-Date.csv";
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
	public void givenWrongCsvFileShouldThrowCensusFileProblemException() {
		try {
			StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
			ExpectedException exceptionRule= ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONG_CENSUS_CSV);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}

}
