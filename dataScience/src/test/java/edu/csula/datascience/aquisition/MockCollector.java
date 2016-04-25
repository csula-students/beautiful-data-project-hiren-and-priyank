package edu.csula.datascience.aquisition;

import java.util.Collection;
import java.util.List;

public class MockCollector implements Collector<SimpleModel, MockData> {
    public Collection<SimpleModel> mungee1(Collection<SimpleModel> src) {
        // in your example, you might need to check src.hasNext() first
        return src;
     
    }
	public Collection<SimpleModel> mungee(Collection<MockData> src) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(String path, String mongoDBCollection) {
		// TODO Auto-generated method stub
		
	}
	public void save(Collection<SimpleModel> data) {
		// TODO Auto-generated method stub
		
	}
	public List<SimpleModel> mungee(Object next) {
		// TODO Auto-generated method stub
		return null;
	}
}
