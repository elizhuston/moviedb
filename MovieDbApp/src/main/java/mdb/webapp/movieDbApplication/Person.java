package mdb.webapp.movieDbApplication;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;


@Entity
@Table(name="person")
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private int id;
	
    String name;
    
//    String dob; // verify how to store dates with database used

    public Person(String name) {
        this.name = name;
    }
    
    public Person() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}