package com.gaeasoft.preorder.remote.vo;

public class OrderDetail extends Order {

	private String resvSaleCode;
	private String resvSaleNm;
	private String resvSaleSeq;
	private String name;
	private String phonNo;
	private String giftCd;
	private String giftNm;
	private String accOrgNm;
    private String accNm;
    private String accTelNo;
    private String telNo;
    private String receCd;
    private String receNm;
    private String orgCd;
    private String orgNm;
    private String dlrCd;
    private String dlrNm;
    private String orgTelNo;
    private String orgAddress;
    private String appSendNo;
    private Boolean enableCancel;
    private Boolean enableWrite;
    public String getAccNm() {
        return accNm;
    }
    public void setAccNm(String accNm) {
        this.accNm = accNm;
    }
    public String getAccOrgNm() {
        return accOrgNm;
    }
    public void setAccOrgNm(String accOrgNm) {
        this.accOrgNm = accOrgNm;
    }
    public String getAccTelNo() {
        return accTelNo;
    }
    public void setAccTelNo(String accTelNo) {
        this.accTelNo = accTelNo;
    }
    public String getAppSendNo() {
        return appSendNo;
    }
    public void setAppSendNo(String appSendNo) {
        this.appSendNo = appSendNo;
    }
    public String getDlrCd() {
        return dlrCd;
    }
    public void setDlrCd(String dlrCd) {
        this.dlrCd = dlrCd;
    }
    public String getGiftCd() {
        return giftCd;
    }
    public void setGiftCd(String giftCd) {
        this.giftCd = giftCd;
    }
    public String getGiftNm() {
        return giftNm;
    }
    public void setGiftNm(String giftNm) {
        this.giftNm = giftNm;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getOrgTelNo() {
        return orgTelNo;
    }
    public void setOrgTelNo(String orgTelNo) {
        this.orgTelNo = orgTelNo;
    }
    public String getPhonNo() {
        return phonNo;
    }
    public void setPhonNo(String phonNo) {
        this.phonNo = phonNo;
    }
    public String getReceNm() {
        return receNm;
    }
    public void setReceNm(String receNm) {
        this.receNm = receNm;
    }
    public String getResvSaleCode() {
        return resvSaleCode;
    }
    public void setResvSaleCode(String resvSaleCode) {
        this.resvSaleCode = resvSaleCode;
    }
    public String getResvSaleNm() {
        return resvSaleNm;
    }
    public void setResvSaleNm(String resvSaleNm) {
        this.resvSaleNm = resvSaleNm;
    }
    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
	public String getDlrNm() {
		return dlrNm;
	}
	public void setDlrNm(String dlrNm) {
		this.dlrNm = dlrNm;
	}
	public String getReceCd() {
		return receCd;
	}
	public void setReceCd(String receCd) {
		this.receCd = receCd;
	}
	public String getResvSaleSeq() {
		return resvSaleSeq;
	}
	public void setResvSaleSeq(String resvSaleSeq) {
		this.resvSaleSeq = resvSaleSeq;
	}
	public Boolean getEnableCancel() {
		return enableCancel;
	}
	public void setEnableCancel(Boolean enableCancel) {
		this.enableCancel = enableCancel;
	}
	public Boolean getEnableWrite() {
		return enableWrite;
	}
	public void setEnableWrite(Boolean enableWrite) {
		this.enableWrite = enableWrite;
	}
    public String getOrgAddress() {
        return orgAddress;
    }
    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }
    @Override
    public String toString() {
        return super.toString() 
        		+ " OrderDetail [resvSaleCode=" + resvSaleCode + ", resvSaleNm=" + resvSaleNm
                + ", resvSaleSeq=" + resvSaleSeq + ", name=" + name + ", phonNo=" + phonNo
                + ", giftCd=" + giftCd + ", giftNm=" + giftNm + ", accOrgNm=" + accOrgNm
                + ", accNm=" + accNm + ", accTelNo=" + accTelNo + ", telNo=" + telNo + ", receCd="
                + receCd + ", receNm=" + receNm + ", orgCd=" + orgCd + ", orgNm=" + orgNm
                + ", dlrCd=" + dlrCd + ", dlrNm=" + dlrNm + ", orgTelNo=" + orgTelNo
                + ", orgAddress=" + orgAddress + ", appSendNo=" + appSendNo + ", enableCancel="
                + enableCancel + ", enableWrite=" + enableWrite + "]";
    }
	
}
