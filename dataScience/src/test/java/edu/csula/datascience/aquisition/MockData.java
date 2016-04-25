package edu.csula.datascience.aquisition;

public class MockData {
	
	private final String id;
	private final String DeathRecords;
	
	
	public MockData(String id , String DeathRecords)
	{
		this.id  = id;
		this.DeathRecords = DeathRecords;
	}


	public String getId() {
		return id;
	}


	public String getDeathRecords() {
		return DeathRecords;
	}

}
