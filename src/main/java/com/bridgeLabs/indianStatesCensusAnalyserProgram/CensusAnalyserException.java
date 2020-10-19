package com.bridgeLabs.indianStatesCensusAnalyserProgram;

public class CensusAnalyserException extends Exception{	
	CensusAnalyserExceptionType exceptionType;
	public CensusAnalyserException(String message, CensusAnalyserExceptionType exceptionType) {
		super(message);
		this.exceptionType=exceptionType;
	}
}

enum CensusAnalyserExceptionType{
	CENSUS_FILE_PROBLEM, INCORRECT_TYPE, INCORRECT_DELIMITER
}