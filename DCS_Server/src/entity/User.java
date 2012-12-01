package entity;

import java.util.HashSet;
import java.util.Set;

public class User {
	private int userid;
	private String name;
	private Set information = new HashSet();

	public User() {
		super();
	}

	public User(int userid, String name, Set information) {
		super();
		this.userid = userid;
		this.name = name;
		this.information = information;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getInformation() {
		return information;
	}

	public void setInformation(Set information) {
		this.information = information;
	}

}
