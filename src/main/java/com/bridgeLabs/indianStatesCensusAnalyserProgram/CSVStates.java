package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

	@CsvBindByName(column = "id", required = true)
	public int id;

	@CsvBindByName(column = "state name", required = true)
	public String stateName;

	@CsvBindByName(column = "tin", required = true)
	public int tin;

	@CsvBindByName(column = "state code", required = true)
	public String stateCode;

	@Override
	public String toString() {
		return "CSVStates [stateName=" + stateName + ", stateCode=" + stateCode + "]";
	}

}
