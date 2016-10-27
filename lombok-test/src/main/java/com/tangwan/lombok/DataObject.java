package com.tangwan.lombok;

public class DataObject {
	private String username;
	private String password;
	
	public DataObject() {
		super();
	}
	public DataObject(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataObject other = (DataObject) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		DataObject d1 = new DataObject("tangwan","tangwan");
		System.out.println(d1);
		DataObject d2 = new DataObject("tangwan","tangwan");
		System.out.println(d2);
		
		System.out.println(d1.equals(d2));
		
		String abc = "abc";
		System.out.println(abc.hashCode());
	}
}
