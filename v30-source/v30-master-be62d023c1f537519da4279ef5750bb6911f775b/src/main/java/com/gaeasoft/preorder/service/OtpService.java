package com.gaeasoft.preorder.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaeasoft.preorder.domain.Otp;
import com.gaeasoft.preorder.domain.OtpRepository;
import com.gaeasoft.preorder.remote.SmsApiProvider;

@Service
@Transactional
public class OtpService {

    private static final int EXPIRE_MIN = -3;
    private static final String OTP_MSG = "[유플러스 예약가입]\n본인인증번호는 " 
    		+ "%OTP_NUMBER%" + " 입니다";
    
    protected Logger logger;
    
    @Autowired
    private OtpRepository otpRepository;
    
    public OtpService() {
        logger = LoggerFactory.getLogger(getClass());
        logger.info("### Invoke {}", getClass());
    }
    
    /**
     * OTP 생성
     * 
     * @param ctn
     * @return
     */
    public Otp createOtp(String ctn) {
    	otpRepository.deleteByCtn(ctn);
    	
        Otp otp = new Otp();
        
        Random random = new Random();
        Integer certNumber = random.nextInt(900000) + 100000;
        
        otp.setCertNumber(certNumber.toString());
        otp.setCtn(ctn);
        otp.setRegDate(new Date());
        
        otp = otpRepository.save(otp);
        sendOtpSms(otp);
        
        return otp;
    }
    
    /**
     * 본인인증번호 확인
     * 
     * @param ctn
     * @param certNumber
     * @return
     */
    public boolean certificate(String ctn, String certNumber) {
        Date time = new Date();

        logger.debug("### time : {}", time);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, EXPIRE_MIN);
        time = cal.getTime();        
        
        int count = otpRepository.getCount(time, ctn, certNumber);
        
        logger.debug("### expire-time : {}, count : {}", time, count);
        
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * OTP SMS 전송
     * 
     * @param otp
     */
    private void sendOtpSms(Otp otp) {
        String msg = OTP_MSG.replace("%OTP_NUMBER%", otp.getCertNumber());
        logger.info("OTP Message : {}", msg);
        SmsApiProvider.sendSms(msg, otp.getCtn());
    }
}
