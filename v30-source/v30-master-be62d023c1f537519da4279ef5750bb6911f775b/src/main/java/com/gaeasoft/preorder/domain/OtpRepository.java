package com.gaeasoft.preorder.domain;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    @Query("select count(otp.id) from Otp otp where otp.regDate >= :time and otp.ctn = :ctn and otp.certNumber = :certNumber")
    Integer getCount(@Param("time") Date time, @Param("ctn") String ctn, @Param("certNumber") String certNumber);
    
    Long deleteByCtn(String ctn);
}