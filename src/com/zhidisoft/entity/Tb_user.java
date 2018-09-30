package com.zhidisoft.entity;

import com.zhidisoft.util.Column;
import com.zhidisoft.util.Table;



@Table(name = "tb_user")
public class Tb_user {
	public Tb_user() {
		super();
	}
	public Tb_user(String username, String password) {
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
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "id")
	private Integer id;
	public Tb_user(Integer id) {
		super();
		this.id = id;
	}
	public Tb_user(String username, String password, Integer id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
