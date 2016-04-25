package edu.csula.datascience.aquisition;

import org.junit.Before;

public class CollectorTest {
	
	private Collector<SimpleModel , MockData> collector;
	
	private Source <MockData> source;
	
	
	@Before
	public void setup(){
		
		 collector = new MockCollector();
	        source = new MockSource();
	}

}
