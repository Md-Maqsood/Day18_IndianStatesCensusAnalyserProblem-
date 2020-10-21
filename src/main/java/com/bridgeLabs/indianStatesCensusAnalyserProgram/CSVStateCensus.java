package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.util.List;

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

	public CSVStateCensus() {
	}

	public CSVStateCensus(List<String> values) {
		this.districtCode = Integer.parseInt(values.get(0));
		this.stateName = values.get(1);
		this.districtName = values.get(2);
		this.population = Long.parseLong(values.get(3));
		this.male = Long.parseLong(values.get(4));
		this.female = Long.parseLong(values.get(5));
		this.literate = Long.parseLong(values.get(6));
	}

	@Override
	public String toString() {
		return "CSVStateCensus [districtCode=" + districtCode + ", stateName=" + stateName + ", districtName="
				+ districtName + ", population=" + population + ", male=" + male + ", female=" + female + ", literate="
				+ literate + "]";
	}
}
