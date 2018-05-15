package com.qa.data;

public class Users {
	
	String name;
	String job;
	
	public Users() {

	}
	public Users(String name, String job)
	{
		this.name=name;
		this.job=job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public String id;
	
	public String createdAt;
     
	public String updatedAt;
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getcreatedAt() {
		return createdAt;
	}

	public void setcreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	
}
