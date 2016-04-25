package edu.csula.datascience.aquisition;

public class SimpleModel {

	private String year;
	private String age;
	private String sex;

	private String placeofdeath;
	private String causeofdeath;
	private String mritalstatus;

	

	public SimpleModel(String year, String age, String sex, String placeofdeath, String causeofdeath,
			String mritalstatus) {
		super();
		this.year = year;
		this.age = age;
		this.sex = sex;
		this.placeofdeath = placeofdeath;
		this.causeofdeath = causeofdeath;
		this.mritalstatus = mritalstatus;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPlaceofdeath() {
		return placeofdeath;
	}

	public void setPlaceofdeath(String placeofdeath) {
		this.placeofdeath = placeofdeath;
	}

	public String getMritalstatus() {
		return mritalstatus;
	}

	public void setMritalstatus(String mritalstatus) {
		this.mritalstatus = mritalstatus;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCauseofdeath() {
		return causeofdeath;
	}

	public void setCauseofdeath(String causeofdeath) {
		this.causeofdeath = causeofdeath;
	}
	public static SimpleModel build(MockData data) {
		return new SimpleModel(data.getYear(),data.getAge(),data.getSex(),data.getPlaceofdeath(),data.getCauseofdeath(),data.getMritalstatus());
	}
	
}
