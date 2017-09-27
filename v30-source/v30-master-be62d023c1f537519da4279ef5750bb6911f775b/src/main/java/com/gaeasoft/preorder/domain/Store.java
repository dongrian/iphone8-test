package com.gaeasoft.preorder.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String dlrCd;

	@Column(nullable = false)
	private String dlrNm;
	
	@Column(nullable = false)
	private String orgType;
	
	@Column(nullable = false)
	private String orgCd;
	
	@Column(nullable = false)
	private String orgNm;

	@Column(nullable = false)
	private String address1;
	
	@Column(nullable = false)
	private String address2;
	
	@Column(nullable = false)
	private String address3;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String fullAddress;
	
	@Column(nullable = false)
	private String opt1;
	
	@Column(nullable = false)
	private String opt2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDlrCd() {
		return dlrCd;
	}

	public void setDlrCd(String dlrCd) {
		this.dlrCd = dlrCd;
	}

	public String getDlrNm() {
		return dlrNm;
	}

	public void setDlrNm(String dlrNm) {
		this.dlrNm = dlrNm;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getOpt1() {
		return opt1;
	}

	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}

	public String getOpt2() {
		return opt2;
	}

	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
