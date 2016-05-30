package edu.csula.datascience.aquisition;

import java.util.Collection;

public class MockCollector  implements Collector<SimpleModel, MockData> {

	public Collection<SimpleModel> mungee(Collection<MockData> src) {
		return null;
	}

	public void save(Collection<SimpleModel> data) {
	
	}

	@Override
	public void save(String path, String mongoDBCollection) {
		// TODO Auto-generated method stub
		
	}

}
