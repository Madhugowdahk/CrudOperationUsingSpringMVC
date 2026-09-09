package com.unibrain.Model;


public class ResponseData {
    private String message;
    private String name;
    private String email;
    private long number;
    private int age;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long l) {
		this.number = l;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ResponseData()
    {
    	
    }
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	@Override
	public String toString() {
		return "ResponseData [message=" + message + ", name=" + name + ", email=" + email + ", number=" + number
				+ ", age=" + age + "]";
	}

    }
