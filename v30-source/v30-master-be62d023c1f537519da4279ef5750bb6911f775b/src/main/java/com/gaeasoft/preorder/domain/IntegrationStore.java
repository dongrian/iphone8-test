package com.gaeasoft.preorder.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IntegrationStore implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
    private String month;
	@Column
    private String orgCd;
	@Column
    private String dlrCd;
	@Column
    private String dlrNm;
	@Column
    private String orgNm;
	@Column
    private String fullAddress;
	@Column
	private boolean experience;
	@Column
    private String address1;
	@Column
    private String address2;

}
