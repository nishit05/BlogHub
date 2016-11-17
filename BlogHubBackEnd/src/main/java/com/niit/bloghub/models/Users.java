package com.niit.bloghub.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity(name = "Friends")
public class Users extends ErrorCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "user_id", initialValue = 1000)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	private int u_id;
	private String name;
	private String email;
	private String username;
	private String password;
	private String type;
	private String reason;
	private String status = "pending".toUpperCase();
	private String isOnline = "No".toUpperCase();
	private String role = "Role_user".toUpperCase();

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

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

	public String getType() {
		return type.toUpperCase();
	}

	public void setType(String type) {
		this.type = type.toUpperCase();
	}

	public String getStatus() {
		return status.toUpperCase();
	}

	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}

	public String getOnline() {
		return isOnline.toUpperCase();
	}

	public void setOnline(String online) {
		this.isOnline = online.toUpperCase();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
