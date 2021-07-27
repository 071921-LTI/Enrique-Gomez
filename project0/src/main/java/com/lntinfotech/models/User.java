package com.lntinfotech.models;

import java.util.Objects;

public class User {

	private String username;
    private String password;
    private String userType;

    public User(String username, String password, String userType) {
        super();
        this.username = username;
        this.password = password;
        this.userType = userType;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(password, userType, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(userType, other.userType)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}
	
}
