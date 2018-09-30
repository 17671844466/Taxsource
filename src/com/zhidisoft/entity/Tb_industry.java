package com.zhidisoft.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.DDIV;
import com.zhidisoft.util.Column;
import com.zhidisoft.util.Table;

@Table(name = "tb_industry")
public class Tb_industry {
	@Column(name = "id")
	private int id;
	@Column(name = "industryName")
	private String industryName;	
	@Column(name = "date_create")
	private Date date_create;
	@Column(name = "date_update")
	private Date date_update;
	@Column(name = "user_create")
	private int user_create;
	@Column(name = "user_update")
	private int user_update;
	public Tb_industry() {
		super();
	}
	public Tb_industry(int id, String industryName, Date date_create, Date date_update, int user_create,
			int user_update) {
		super();
		this.id = id;
		this.industryName = industryName;
		this.date_create = date_create;
		this.date_update = date_update;
		this.user_create = user_create;
		this.user_update = user_update;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getDate_create() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date_create);
	}
	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}
	public String getDate_update() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date_update);
	}
	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}
	public int getUser_create() {
		return user_create;
	}
	public void setUser_create(int user_create) {
		this.user_create = user_create;
	}
	public int getUser_update() {
		return user_update;
	}
	public void setUser_update(int user_update) {
		this.user_update = user_update;
	}
}
