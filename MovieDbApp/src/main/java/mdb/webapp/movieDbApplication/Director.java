package mdb.webapp.movieDbApplication;

public class Director extends Person {

	public Director(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public int getDirectorIdByName(String name) {
		int id = 0;// TODO replace with call to a database query
		return id;
	}

	public void setDirectorName(String name) {
		this.name = name;
	}

}
