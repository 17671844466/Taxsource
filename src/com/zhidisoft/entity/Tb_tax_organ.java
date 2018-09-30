package com.zhidisoft.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhidisoft.util.Column;
import com.zhidisoft.util.Table;

@Table(name = "tb_tax_organ")
public class Tb_tax_organ {
	@Column(name = "id")
	private int id;//id
	@Column(name = "organName")
	private String organName;//缴费号码
	@Column(name = "parentId")
	private int parentId;//缴费姓名	
	@Column(name = "address")
	private String address;//商业地址
	@Column(name = "phone")
	private String phone;//税务机构id	
	@Column(name = "faxPhone")
	private String faxPhone;//产业编号
	@Column(name = "email")	
	private String email;//商业范围	
	@Column(name = "leaderId")
	private int leaderId;//发票类型
	@Column(name = "taxTypeCode")
	private String taxTypeCode;//法人代表
	@Column(name = "state")
	private int state;//法人身份证
	@Column(name = "date_create")
	private Date date_create;//法人电话
	@Column(name = "date_update")
	private Date date_update;//法人图片	
	@Column(name = "user_create")
	private int user_create;//财务名称
	@Column(name = "user_update")
	private int user_update;//财务身份证
	public Tb_tax_organ() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tb_tax_organ(int id, String organName, int parentId, String address, String phone, String faxPhone,
			String email, int leaderId, String taxTypeCode, int state, Date date_create, Date date_update,
			int user_create, int user_update) {
		super();
		this.id = id;
		this.organName = organName;
		this.parentId = parentId;
		this.address = address;
		this.phone = phone;
		this.faxPhone = faxPhone;
		this.email = email;
		this.leaderId = leaderId;
		this.taxTypeCode = taxTypeCode;
		this.state = state;
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
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFaxPhone() {
		return faxPhone;
	}
	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	public String getTaxTypeCode() {
		return taxTypeCode;
	}
	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
