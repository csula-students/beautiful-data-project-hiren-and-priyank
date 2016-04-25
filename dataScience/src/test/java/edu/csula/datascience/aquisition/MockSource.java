package edu.csula.datascience.aquisition;

import com.google.common.collect.Lists;

import java.util.Collection;

public class MockSource extends CollectorTest implements Source<MockData> {

	// TODO Auto-generated method stub
	public Collection<MockData> next() {
		return Lists.newArrayList(new MockData(null, null, null, null, null, null),
				new MockData("2000", "63", "M", "new York", "cancer", "unmerried"));

	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

}
