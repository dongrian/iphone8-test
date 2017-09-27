package com.gaeasoft.preorder.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaeasoft.preorder.domain.ExStore;
import com.gaeasoft.preorder.domain.ExStoreRepository;
import com.gaeasoft.preorder.domain.IntegrationStore;
import com.gaeasoft.preorder.domain.IntegrationStoreRepository;
import com.gaeasoft.preorder.domain.OrderHistory;
import com.gaeasoft.preorder.domain.Otp;
import com.gaeasoft.preorder.domain.Store;
import com.gaeasoft.preorder.domain.StoreRepository;
import com.gaeasoft.preorder.exception.BadRequestException;
import com.gaeasoft.preorder.exception.UplusApiException;
import com.gaeasoft.preorder.remote.vo.OrderDetail;
import com.gaeasoft.preorder.service.OrderService;
import com.gaeasoft.preorder.service.OtpService;
import com.gaeasoft.preorder.web.support.OrderJsonForDetail;
import com.gaeasoft.preorder.web.support.Response;
import com.gaeasoft.preorder.web.support.Response.RespStatus;

@Controller
public class ApiController {
    protected Logger logger;
    
    public ApiController() {
        logger = LoggerFactory.getLogger(getClass());
        logger.info("### Invoke {}", getClass());
    }
    
    @Autowired
    OtpService otpService;
    @Autowired
    OrderService orderService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ExStoreRepository exStoreRepository;
    @Autowired
    IntegrationStoreRepository integrationStoreRepository;
    
    @RequestMapping(value = "/api/otp", method = RequestMethod.POST)
    @ResponseBody
    Response otp(@RequestParam String ctn) {
    	if ("".equals(ctn)) {
    		throw new BadRequestException("value is empty");
    	}

    	Response response = null;
        
        try {
            Otp otp = otpService.createOtp(ctn);
            response = new Response(RespStatus.OK, "정상", otp);
        } catch (Exception e){
            response = new Response(RespStatus.ERROR, "예기치 못한 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping("/api/otp/certificate")
    @ResponseBody
    Response otp(@RequestParam String ctn, @RequestParam String certNumber) {
        Response response = null;
    	
        if ("".equals(ctn) || "".equals(certNumber)) {
    		throw new BadRequestException("value is empty");
    	}
    	
        try {
            if (otpService.certificate(ctn, certNumber)) {
                response = new Response(RespStatus.OK, "정상");
            } else {
                response = new Response(RespStatus.FAIL, "인증번호가 맞지 않습니다. 정확한 번호를 입력해주세요.");
            }
        } catch (Exception e){
            response = new Response(RespStatus.ERROR, "예기치 못한 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping(value = "/api/order", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Response order(@RequestBody OrderJsonForDetail param) {
    	Response response = null;
    	logger.info("### /api/order, @RequestBody : {}", param);
    	
    	try {
    	    OrderHistory orderHistory = orderService.order(param);
    		response = new Response(RespStatus.OK, "정상", orderHistory.getReqSeqNo());
    	} catch (UplusApiException ue) {
    		response = new Response(RespStatus.ERROR, ue.getMessage());
    	} catch (Exception e) {
    		response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
    		logger.error("### 예약가입 신청 중 오류 발생.", e);
    	}
    	
    	return response;
    }
    
    @RequestMapping(value = "/api/order/store", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Response updateVisitStore(@RequestParam String reqSeqNo, @RequestParam String storeCd) {
        Response response = null;
        logger.info("### /api/order/store, reqSeqNo : {}, storeCd : {}", reqSeqNo, storeCd);
        
        if (storeCd.isEmpty() || reqSeqNo.isEmpty()) {
        	response = new Response(RespStatus.ERROR, "정상적인 파라미터가 아닙니다");
        }
        String[] array = storeCd.split(",");
        if (array.length != 2) {
        	response = new Response(RespStatus.ERROR, "정상적인 파라미터가 아닙니다");
        }
        String orgCd = array[0];
        String dlrCd = array[1];
        
        try {
        	OrderHistory orderHistory = orderService.updateVisitStore(reqSeqNo, orgCd, dlrCd);
            response = new Response(RespStatus.OK, "정상", orderHistory.getReqSeqNo());
        } catch (UplusApiException ue) {
        	response = new Response(RespStatus.ERROR, ue.getMessage());
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
            logger.error("### 예약가입 대리점변경 중 오류 발생.", e);
        }
        
        return response;
    }
    
    @RequestMapping("/api/exstore/address1")
    @ResponseBody
    Response getExStoreAddress1() {
        Response response = null;
        logger.info("### /api/exstore/address1");
        
        try {
            List<String> adds = integrationStoreRepository.findGroupByExAddress1();
            response = new Response(RespStatus.OK, "정상", adds);
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping("/api/exstore/address2")
    @ResponseBody
    Response getExStoreAddress2(@RequestParam String address1, HttpServletRequest request) {
        Response response = null;
        
        logger.info("### {}?address1={}", request.getRequestURI(), address1);
        
        try {
            List<String> adds = integrationStoreRepository.findGroupByExAddress2(address1);
            logger.info("### findGroupByAddress2 result count = {}", adds.size());
            response = new Response(RespStatus.OK, "정상", adds);
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping("/api/exstores/search")
    @ResponseBody
    Response searchExStore1(@RequestParam String query, HttpServletRequest request) {
        Response response = null;
        
        logger.info("### {}?query={}", request.getRequestURI(), query);
        
        try {
            List<IntegrationStore> stores = integrationStoreRepository.findByExKeyword(query);
            if (stores == null || stores.size() == 0) {
                response = new Response(RespStatus.EMPTY_RESULT, "검색결과가 없습니다.");
            } else {
                response = new Response(RespStatus.OK, "정상", stores);
            }
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "검색 중 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping("/api/exstores")
    @ResponseBody
    Response getExStoreAddress(@RequestParam String address1, @RequestParam String address2) {
        Response response = null;
        logger.info("### /api/exstore?address1={}&address2={}", address1, address2);
        
        try {
            List<IntegrationStore> stores = integrationStoreRepository.findByAddress1AndAddress2AndExperience(address1, address2, true);
            if (stores == null || stores.size() == 0) {
                response = new Response(RespStatus.EMPTY_RESULT, "검색결과가 없습니다.");
            } else {
                response = new Response(RespStatus.OK, "정상", stores);
            }
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "검색 중 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping("/api/address1")
    @ResponseBody
    Response getAddress1() {
        Response response = null;
        logger.info("### /api/address1");
        
        try {
        	List<String> adds = integrationStoreRepository.findGroupByAddress1();
            response = new Response(RespStatus.OK, "정상", adds);
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping("/api/address2")
    @ResponseBody
    Response getAddress2(@RequestParam String address1) {
    	logger.info("### /api/address2?address1={}", address1);
    	Response response = null;
    	
    	try {
    		List<String> adds = integrationStoreRepository.findGroupByAddress2(address1);
    		response = new Response(RespStatus.OK, "정상", adds);
    	} catch (Exception e) {
    		response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
    	}
    	
    	return response;
    }
    
    @RequestMapping("/api/stores/search")
    @ResponseBody
    Response searchStore1(@RequestParam String query) {
    	Response response = null;
    	logger.info("### /api/stores/search?query={}", query);
    	
    	try {
    		List<IntegrationStore> stores = integrationStoreRepository.findByKeyword(query);
        	if (stores == null || stores.size() == 0) {
        		response = new Response(RespStatus.EMPTY_RESULT, "검색결과가 없습니다.");
        	} else {
        		response = new Response(RespStatus.OK, "정상", stores);
        	}
    	} catch (Exception e) {
    		response = new Response(RespStatus.ERROR, "검색 중 오류가 발생했습니다.");
    	}
    	
    	return response;
    }
    
    @RequestMapping("/api/stores")
    @ResponseBody
    Response getAddress(@RequestParam String address1, @RequestParam String address2) {
        Response response = null;
        logger.info("### /api/stores?address1={}&address2={}", address1, address2);
        
        try {
        	List<IntegrationStore> stores = integrationStoreRepository.findByAddress1AndAddress2(address1, address2);
        	if (stores == null || stores.size() == 0) {
        		response = new Response(RespStatus.EMPTY_RESULT, "검색결과가 없습니다.");
        	} else {
        		response = new Response(RespStatus.OK, "정상", stores);
        	}
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "검색 중 오류가 발생했습니다.");
        }
        
        return response;
    }

    @RequestMapping("/api/orders")
    @ResponseBody
    Response getOrders(@RequestParam String name, @RequestParam String phonNo) {
        Response response = null;
        logger.info("### /api/orders, name : {}, phonNo : {}", name, phonNo);
        
        try {
        	List<OrderDetail> orderDetails = orderService.getOrderList(name, phonNo);
            response = new Response(RespStatus.OK, "정상", orderDetails);	
        } catch (UplusApiException ue) {
        	response = new Response(RespStatus.ERROR, ue.getMessage());
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "서버에서 오류가 발생했습니다.");
        }
        
        return response;
    }
    
    @RequestMapping(value = "/api/order/cancel", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Response cancleOrder(@RequestBody OrderJsonForDetail param) {
        Response response = null;
        logger.info("### /api/order/cancel, @RequestBody : {}", param);
        
        try {
            OrderHistory orderHistory = orderService.cancelOrder(param);
            response = new Response(RespStatus.OK, "정상", orderHistory.getReqSeqNo());
        } catch (UplusApiException ue) {
            response = new Response(RespStatus.ERROR, ue.getMessage());
        } catch (Exception e) {
            response = new Response(RespStatus.ERROR, "예약가입 취소 중 오류가 발생하였습니다.");
            logger.error("### 예약가입 취소 중 오류 발생.", e);
        }
        
        return response;
    }
}
