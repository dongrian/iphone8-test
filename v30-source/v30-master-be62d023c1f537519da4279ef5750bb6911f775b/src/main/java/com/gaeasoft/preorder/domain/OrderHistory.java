package com.gaeasoft.preorder.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderHistory implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(length=512)
    private String token; // 토큰값
    
    @Column
    private String name; // 고객명
    
    @Column
    private String phonNo; // 고객폰번호
    
    @Column
    private String modTypCd; // 모델코드
    
    @Column
    private String sizeCd; // 용량코드
    
    @Column
    private String colrCd; // 색상코드
    
    @Column
    private String giftCd; // 사은품코드
    
    @Column
    private String receCd; // 수령방법코드
    
    @Column
    private Date reqDt; // 신청일
    
    @Column
    private String reqSeqNo; // 주문번호
    
    @Column
    private String orgCd; // 방문매장코드
    
    @Column
    private String dlrCd; // 대리점코드
    
    @Column
    private String historyType; // 이력타입
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getColrCd() {
        return colrCd;
    }
    public void setColrCd(String colrCd) {
        this.colrCd = colrCd;
    }
    public String getModTypCd() {
        return modTypCd;
    }
    public void setModTypCd(String modTypCd) {
        this.modTypCd = modTypCd;
    }
    public Date getReqDt() {
        return reqDt;
    }
    public void setReqDt(Date reqDt) {
        this.reqDt = reqDt;
    }
    public String getReqSeqNo() {
        return reqSeqNo;
    }
    public void setReqSeqNo(String reqSeqNo) {
        this.reqSeqNo = reqSeqNo;
    }
    public String getSizeCd() {
        return sizeCd;
    }
    public void setSizeCd(String sizeCd) {
        this.sizeCd = sizeCd;
    }
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getHistoryType() {
		return historyType;
	}
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}
	
}
