package com.niit.bloghub.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Blog extends ErrorCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "blog_id", initialValue = 1000)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_id")
	private int b_id;

	private String title;

	private String u_id;

	private String content;

	private Date b_date;

	private String status = "pending";

	private String reason;

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public String getStatus() {
		return status.toUpperCase();
	}

	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
