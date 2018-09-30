package com.zhidisoft.entity;





import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;
import com.zhidisoft.util.Column;
import com.zhidisoft.util.Table;

@Table(name = "tb_tax_payer")
public class Tb_taxPayer {
	@Column(name = "id")
	private int id;//id
	@Column(name = "payerCode")
	private String payerCode;//缴费号码
	@Column(name = "payerName")
	private String payerName;//缴费姓名
	@Column(name = "bizAddress")
	private String bizAddress;//商业地址
	@Column(name = "taxOrganId")
	private int taxOrganId;//税务机构id
	@Column(name = "industryId")
	private int industryId;//产业编号
	@Column(name = "bizScope")	
	private String bizScope;//商业范围
	@Column(name = "invoiceType")
	private String invoiceType;//发票类型
	@Column(name = "legalPerson")
	private String legalPerson;//法人代表
	@Column(name = "legalIdCard")
	private String legalIdCard;//法人身份证
	@Column(name = "legalMobile")
	private String legalMobile;//法人电话
	@Column(name = "legalIdCardImageURL")
	private String legalIdCardImageURL;//法人图片
	@Column(name = "finaceName")
	private String finaceName;//财务名称
	@Column(name = "finaceIdCard")
	private String finaceIdCard;//财务身份证
	@Column(name = "finaceMobile")
	private String finaceMobile;//财务电话
	@Column(name = "finaceIdCardImageURL")
	private String finaceIdCardImageURL;//财务图片地址
	@Column(name = "taxerName")
	private String taxerName;//纳税人姓名
	@Column(name = "taxerIdCard")
	private String taxerIdCard;//纳税人身份证
	@Column(name = "taxerMobile")
	private String taxerMobile;//纳税人电话
	@Column(name = "taxerIdCardImageURL")
	private String taxerIdCardImageURL;//纳税人图片地址
	@Column(name = "bizAddressPhone")
	private String bizAddressPhone;//商业地址电话
	@Column(name = "recordDate")
	private Date recordDate;//收录时间
	@Column(name = "userId")
	private int userId;//用户id
	@Column(name = "removeState")
	private int removeState;
	public Tb_taxPayer() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getBizAddress() {
		return bizAddress;
	}
	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress;
	}
	public int getTaxOrganId() {
		return taxOrganId;
	}
	public void setTaxOrganId(int taxOrganId) {
		this.taxOrganId = taxOrganId;
	}
	public int getIndustryId() {
		return industryId;
	}
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	public String getBizScope() {
		return bizScope;
	}
	public void setBizScope(String bizScope) {
		this.bizScope = bizScope;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalIdCard() {
		return legalIdCard;
	}
	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}
	public String getLegalMobile() {
		return legalMobile;
	}
	public void setLegalMobile(String legalMobile) {
		this.legalMobile = legalMobile;
	}
	public String getLegalIdCardImageURL() {
		return legalIdCardImageURL;
	}
	public void setLegalIdCardImageURL(String legalIdCardImageURL) {
		this.legalIdCardImageURL = legalIdCardImageURL;
	}
	public String getFinaceName() {
		return finaceName;
	}
	public void setFinaceName(String finaceName) {
		this.finaceName = finaceName;
	}
	public String getFinaceIdCard() {
		return finaceIdCard;
	}
	public void setFinaceIdCard(String finaceIdCard) {
		this.finaceIdCard = finaceIdCard;
	}
	public String getFinaceMobile() {
		return finaceMobile;
	}
	public void setFinaceMobile(String finaceMobile) {
		this.finaceMobile = finaceMobile;
	}
	public String getFinaceIdCardImageURL() {
		return finaceIdCardImageURL;
	}
	public void setFinaceIdCardImageURL(String finaceIdCardImageURL) {
		this.finaceIdCardImageURL = finaceIdCardImageURL;
	}
	public String getTaxerName() {
		return taxerName;
	}
	public void setTaxerName(String taxerName) {
		this.taxerName = taxerName;
	}
	public String getTaxerIdCard() {
		return taxerIdCard;
	}
	public void setTaxerIdCard(String taxerIdCard) {
		this.taxerIdCard = taxerIdCard;
	}
	public String getTaxerMobile() {
		return taxerMobile;
	}
	public void setTaxerMobile(String taxerMobile) {
		this.taxerMobile = taxerMobile;
	}
	public String getTaxerIdCardImageURL() {
		return taxerIdCardImageURL;
	}
	public void setTaxerIdCardImageURL(String taxerIdCardImageURL) {
		this.taxerIdCardImageURL = taxerIdCardImageURL;
	}
	public String getBizAddressPhone() {
		return bizAddressPhone;
	}
	public void setBizAddressPhone(String bizAddressPhone) {
		this.bizAddressPhone = bizAddressPhone;
	}
	public String getRecordDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordDate);
	}
	public void setRecordDate(Date recordDate2) {
		this.recordDate = recordDate2;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRemoveState() {
		return removeState;
	}
	public void setRemoveState(int removeState) {
		this.removeState = removeState;
	}
	public Tb_taxPayer(int id, String payerCode, String payerName, String bizAddress, int taxOrganId, int industryId,
			String bizScope, String invoiceType, String legalPerson, String legalIdCard, String legalMobile,
			String legalIdCardImageURL, String finaceName, String finaceIdCard, String finaceMobile,
			String finaceIdCardImageURL, String taxerName, String taxerIdCard, String taxerMobile,
			String taxerIdCardImageURL, String bizAddressPhone, Date recordDate, int userId, int removeState) {
		super();
		this.id = id;
		this.payerCode = payerCode;
		this.payerName = payerName;
		this.bizAddress = bizAddress;
		this.taxOrganId = taxOrganId;
		this.industryId = industryId;
		this.bizScope = bizScope;
		this.invoiceType = invoiceType;
		this.legalPerson = legalPerson;
		this.legalIdCard = legalIdCard;
		this.legalMobile = legalMobile;
		this.legalIdCardImageURL = legalIdCardImageURL;
		this.finaceName = finaceName;
		this.finaceIdCard = finaceIdCard;
		this.finaceMobile = finaceMobile;
		this.finaceIdCardImageURL = finaceIdCardImageURL;
		this.taxerName = taxerName;
		this.taxerIdCard = taxerIdCard;
		this.taxerMobile = taxerMobile;
		this.taxerIdCardImageURL = taxerIdCardImageURL;
		this.bizAddressPhone = bizAddressPhone;
		this.recordDate = recordDate;
		this.userId = userId;
		this.removeState = removeState;
	}
}
	