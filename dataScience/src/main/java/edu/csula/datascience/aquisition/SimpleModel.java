package edu.csula.datascience.aquisition;

public class SimpleModel {
	
	private String id;
	private String Deathrecords;
	
	public SimpleModel(String id , String Deathrecords){
		
		this.id = id;
		this.Deathrecords = Deathrecords;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeathrecords() {
		return Deathrecords;
	}

	public void setDeathrecords(String deathrecords) {
		Deathrecords = deathrecords;
	}
	
	public static SimpleModel build (MockData data)
	{
		return new SimpleModel(data.getId(), data.getDeathRecords());
	}
	
	

}
