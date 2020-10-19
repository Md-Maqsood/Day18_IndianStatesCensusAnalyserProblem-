package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName(column = "districtCode", required = true)
	public int districtCode;
	@CsvBindByName(column = "stateName", required = true)
	public String stateName;
	@CsvBindByName(column = "districtName", required = true)
	public String districtName;
	@CsvBindByName(column = "population", required = true)
	public long population;
	@CsvBindByName(column = "male", required = true)
	public long male;
	@CsvBindByName(column = "female", required = true)
	public long female;
	@CsvBindByName(column = "literate", required = true)
	public long literate;

	@Override
	public String toString() {
		return "CSVStateCensus [districtCode=" + districtCode + ", stateName=" + stateName + ", districtName="
				+ districtName + ", population=" + population + ", male=" + male + ", female=" + female + ", literate="
				+ literate + "]";
	}
}
