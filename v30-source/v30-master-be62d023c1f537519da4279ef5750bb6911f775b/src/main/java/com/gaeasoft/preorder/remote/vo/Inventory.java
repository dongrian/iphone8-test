package com.gaeasoft.preorder.remote.vo;

public class Inventory {

    private String colrCd;
    private String colrNm;
    private String modTypCd;
    private String modTypNm;
    private String resvSaleCode;
    private String resvSaleNm;
    private String sinfo;
    private String sizeCd;
    private String sizeNm;
    public String getColrCd() {
        return colrCd;
    }
    public void setColrCd(String colrCd) {
        this.colrCd = colrCd;
    }
    public String getColrNm() {
        return colrNm;
    }
    public void setColrNm(String colrNm) {
        this.colrNm = colrNm;
    }
    public String getModTypCd() {
        return modTypCd;
    }
    public void setModTypCd(String modTypCd) {
        this.modTypCd = modTypCd;
    }
    public String getModTypNm() {
        return modTypNm;
    }
    public void setModTypNm(String modTypNm) {
        this.modTypNm = modTypNm;
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
    public String getSinfo() {
        return sinfo;
    }
    public void setSinfo(String sinfo) {
        this.sinfo = sinfo;
    }
    public String getSizeCd() {
        return sizeCd;
    }
    public void setSizeCd(String sizeCd) {
        this.sizeCd = sizeCd;
    }
    public String getSizeNm() {
        return sizeNm;
    }
    public void setSizeNm(String sizeNm) {
        this.sizeNm = sizeNm;
    }
    @Override
    public String toString() {
        return "Inventory [colrCd=" + colrCd + ", colrNm=" + colrNm + ", modTypCd=" + modTypCd
                + ", modTypNm=" + modTypNm + ", resvSaleCode=" + resvSaleCode + ", resvSaleNm="
                + resvSaleNm + ", sinfo=" + sinfo + ", sizeCd=" + sizeCd + ", sizeNm=" + sizeNm
                + "]";
    }
}
