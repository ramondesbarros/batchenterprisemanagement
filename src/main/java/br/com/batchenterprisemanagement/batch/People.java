package br.com.batchenterprisemanagement.batch;

public class People {

	private String firstname;
	
	private String lastname;
	
	private String city;
	
	private String age;

	public People(String firstname, String lastname, String city, String age) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.age = age;
	}

	public People() {
		super();
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
		
}
