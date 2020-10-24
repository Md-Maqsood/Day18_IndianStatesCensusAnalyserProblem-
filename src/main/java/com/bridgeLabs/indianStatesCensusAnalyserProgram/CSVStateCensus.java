package com.bridgeLabs.indianStatesCensusAnalyserProgram;

import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName(column = "Rank", required = true)
	public int rank;
	@CsvBindByName(column = "State", required = true)
	public String stateName;
	@CsvBindByName(column = "Population", required = true)
	public long population;
	@CsvBindByName(column = "AreaInSqKm", required = true)
	public int area;
	@CsvBindByName(column = "DensityPerSqKm", required = true)
	public int density;

	@Override
	public String toString() {
		return "CSVStateCensus [rank=" + rank + ", stateName=" + stateName + ", population=" + population + ", area="
				+ area + ", density=" + density + "]";
	}

	public CSVStateCensus() {
	}

	public CSVStateCensus(List<String> values) {
		this.rank = Integer.parseInt(values.get(0));
		this.stateName = values.get(1);
		this.population = Long.parseLong(values.get(2));
		this.area = Integer.parseInt(values.get(3));
		this.density = Integer.parseInt(values.get(4));
	}
}
