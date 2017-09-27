package com.gaeasoft.preorder.web.support;

public class OrderJsonForDetail {
    private String name; // 고객 이름
	private String phonNo; // 고객 휴대폰번호
	private String modTypCd; // 모델코드
	private String sizeCd;	// 용량코드
	private String colrCd;	// 컬러코드
	private String giftCd;	// 사은품코드
	private String receCd;	// 수령방법코드
	private String reqSeqNo; // 예약판매번호
	private String orgCd; // 방문매장코드
	private String dlrCd; // 대리점코드
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonNo() {
		return phonNo;
	}
	public void setPhonNo(String phonNo) {
		this.phonNo = phonNo;
	}
	public String getModTypCd() {
		return modTypCd;
	}
	public void setModTypCd(String modTypCd) {
		this.modTypCd = modTypCd;
	}
	public String getSizeCd() {
		return sizeCd;
	}
	public void setSizeCd(String sizeCd) {
		this.sizeCd = sizeCd;
	}
	public String getColrCd() {
		return colrCd;
	}
	public void setColrCd(String colrCd) {
		this.colrCd = colrCd;
	}
	public String getGiftCd() {
		return giftCd;
	}
	public void setGiftCd(String giftCd) {
		this.giftCd = giftCd;
	}
	public String getReceCd() {
		return receCd;
	}
	public void setReceCd(String receCd) {
		this.receCd = receCd;
	}
	public String getReqSeqNo() {
		return reqSeqNo;
	}
	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getDlrCd() {
		return dlrCd;
	}
	public void setDlrCd(String dlrCd) {
		this.dlrCd = dlrCd;
	}
	@Override
	public String toString() {
		return "OrderJsonForDetail [name=" + name + ", phonNo=" + phonNo + ", modTypCd=" + modTypCd + ", sizeCd="
				+ sizeCd + ", colrCd=" + colrCd + ", giftCd=" + giftCd + ", receCd=" + receCd + ", reqSeqNo=" + reqSeqNo
				+ ", orgCd=" + orgCd + ", dlrCd=" + dlrCd + "]";
	}
	
}
