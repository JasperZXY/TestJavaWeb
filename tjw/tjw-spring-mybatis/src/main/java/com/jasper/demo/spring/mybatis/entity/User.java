package com.jasper.demo.spring.mybatis.entity;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private Date birtyday;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirtyday() {
		return birtyday;
	}
	public void setBirtyday(Date birtyday) {
		this.birtyday = birtyday;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birtyday=" + birtyday + "]";
	}

}
