package com.gaeasoft.preorder.remote.vo;

public class Order {
    private String colrCd; // 색상코드
    private String colrNm; // 색상명
    private String instTypeCd; // 채널코드
    private String instTypeNm; // 채널명
    private String modTypCd; // 모델코드
    private String modTypNm; // 모델명
    private String reqDt; // 신청일
    private String reqSeqNo; // 주문번호
    private String resvSaleSeq; // 예약이벤트코드
    private String sizeCd; // 용량코드
    private String sizeNm; // 용량명
    private String statCd; // 신청상태코드
    private String statNm; // 신청상태명
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
    public String getInstTypeCd() {
        return instTypeCd;
    }
    public void setInstTypeCd(String instTypeCd) {
        this.instTypeCd = instTypeCd;
    }
    public String getInstTypeNm() {
        return instTypeNm;
    }
    public void setInstTypeNm(String instTypeNm) {
        this.instTypeNm = instTypeNm;
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
    public String getReqDt() {
        return reqDt;
    }
    public void setReqDt(String reqDt) {
        this.reqDt = reqDt;
    }
    public String getReqSeqNo() {
        return reqSeqNo;
    }
    public void setReqSeqNo(String reqSeqNo) {
        this.reqSeqNo = reqSeqNo;
    }
    public String getResvSaleSeq() {
        return resvSaleSeq;
    }
    public void setResvSaleSeq(String resvSaleSeq) {
        this.resvSaleSeq = resvSaleSeq;
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
    public String getStatCd() {
        return statCd;
    }
    public void setStatCd(String statCd) {
        this.statCd = statCd;
    }
    public String getStatNm() {
        return statNm;
    }
    public void setStatNm(String statNm) {
        this.statNm = statNm;
    }
    @Override
    public String toString() {
        return "Order [colrCd=" + colrCd + ", colrNm=" + colrNm + ", instTypeCd=" + instTypeCd
                + ", instTypeNm=" + instTypeNm + ", modTypCd=" + modTypCd + ", modTypNm=" + modTypNm
                + ", reqDt=" + reqDt + ", reqSeqNo=" + reqSeqNo + ", resvSaleSeq=" + resvSaleSeq
                + ", sizeCd=" + sizeCd + ", sizeNm=" + sizeNm + ", statCd=" + statCd + ", statNm="
                + statNm + "]";
    }
    
}
