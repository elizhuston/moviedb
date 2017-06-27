package mdb.webapp.movieDbApplication;


public class Person {
	
    String name;
    String dob; // verify how to store dates with database used


    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}  

}