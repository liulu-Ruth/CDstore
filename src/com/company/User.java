package com.company;

public class User {
	
	private String id = "";
	private String password = "";
	private String worktype = "";
	private String name="";

	private String enemy = "";
	public String getEnemy() {
		return enemy;
	}
	public void setEnemy(String enemy) {
		this.enemy = enemy;
	}
	public User() {
		
	}
	public User(String id, String password, String worktype,String name) {
		this.id = id;
		this.password = password;
		this.worktype = worktype;
		this.name=name;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName(){return name;}

	public void setName(String name){this.name=name;}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

}
