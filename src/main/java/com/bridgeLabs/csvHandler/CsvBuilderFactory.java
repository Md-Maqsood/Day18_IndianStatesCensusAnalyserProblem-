package com.bridgeLabs.csvHandler;

public class CsvBuilderFactory {
	public static ICsvBuilder createBuilder() {
		return new OpenCsvBuilder();
	}
}
