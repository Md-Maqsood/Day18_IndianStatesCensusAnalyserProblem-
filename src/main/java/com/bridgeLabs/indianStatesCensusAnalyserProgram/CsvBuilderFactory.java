package com.bridgeLabs.indianStatesCensusAnalyserProgram;

public class CsvBuilderFactory {
	public static ICsvBuilder createBuilder() {
		return new OpenCsvBuilder();
	}
}
