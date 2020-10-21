package com.bridgeLabs.csvHandler;

import java.io.Reader;
import java.util.Iterator;

public interface ICsvBuilder {
	public <T> Iterator<T> getIteratorFromCsv(Reader reader, Class<T> csvBindedClass) throws CsvException;
}
