package com.gaeasoft.preorder.web;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaeasoft.preorder.exception.BadRequestException;
import com.gaeasoft.preorder.exception.UplusApiException;
import com.gaeasoft.preorder.remote.vo.OrderDetail;
import com.gaeasoft.preorder.service.OrderService;

@Controller
public class WebController {
    protected Logger logger;
    
    public WebController() {
        logger = LoggerFactory.getLogger(getClass());
        logger.info("### Invoke {}", getClass());
    }
    
    @Value("${v30.event-url}")
    private String eventUrl;
    
    @Value("${v30.event-url-mobile}")
    private String eventUrlMobile;
    
    @Value("${v30.event-redirect:false}")
    private boolean eventRedirect;
    
    @Autowired
    OrderService orderService;
    
    @RequestMapping("/")
    String root(Model model, Device device, RedirectAttributes redirectAttr, HttpServletRequest request) {
        logger.info("### /");
    	
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()){
            String name = (String)params.nextElement();
            redirectAttr.addAttribute(name, request.getParameter(name));
        }
        
        if (eventRedirect) {
        	return "redirect:/pre";
        } else {
        	return "redirect:/main";
        }
    }
    
    @RequestMapping("/offline-store")
    String offlineStore() {
        logger.info("### /offline-store");
        return "/7_offline_store";
    }
    
    @RequestMapping("/offline-about-g6")
    String offlineAbout() {
        logger.info("### /offline_about_g6");
        return "/8_offline_about_g6";
    }

    @RequestMapping("/main")
    String main(Model model) {
        logger.info("### /main");
        model.addAttribute("wrapClass", "sec_index");
    	return "/main";
    }
    
    @RequestMapping("/reserve1")
    String reserve(Model model) {
        logger.info("### /reserve1");
        model.addAttribute("wrapClass", "sec_reserve_1");
    	return "/reserve-step1";
    }
    
    @RequestMapping("/reserve2")
    String reserveStep2(Model model) {
        logger.info("### /reserve2");
        model.addAttribute("wrapClass", "sec_reserve_2");
    	return "/reserve-step2";
    }
    
    @RequestMapping("/reserve3")
    String reserveStep3(Model model, @RequestParam String reqSeqNo) {
        logger.info("### /reserve3, reqSeqNo : {}", reqSeqNo);
    	if ("".equals(reqSeqNo)) {
    		throw new BadRequestException("value is empty");
    	}
    	try {
	    	OrderDetail orderDetail = orderService.getOrderDetail(reqSeqNo);
	    	model.addAttribute("order", orderDetail);
	    	model.addAttribute("wrapClass", "sec_reserve_3");
	    	return "/reserve-step3";
        } catch (UplusApiException ue) {
        	throw new BadRequestException(ue.getMessage());
        }
    }
    
    @RequestMapping("/reserve4")
    String reserveStep4(Model model, @RequestParam String reqSeqNo) {
        logger.info("### /reserve4, reqSeqNo : {}", reqSeqNo);
    	if ("".equals(reqSeqNo)) {
    		throw new BadRequestException("value is empty");
    	}
    	model.addAttribute("reqSeqNo", reqSeqNo);
    	model.addAttribute("wrapClass", "sec_reserve_4");
    	return "/reserve-step4";
    }
    
    @RequestMapping("/reserve5")
    String reserveStep5(Model model, @RequestParam String reqSeqNo) {
        logger.info("### /reserve5, reqSeqNo : {}", reqSeqNo);
    	if ("".equals(reqSeqNo)) {
    		throw new BadRequestException("value is empty");
    	}
    	
    	try {
	    	OrderDetail orderDetail = orderService.getOrderDetail(reqSeqNo);
	    	model.addAttribute("order", orderDetail);
	    	model.addAttribute("wrapClass", "sec_reserve_5");
	    	return "/reserve-step5";
        } catch (UplusApiException ue) {
        	throw new BadRequestException(ue.getMessage());
        }
    }
    
    @RequestMapping("/about")
    String about(Model model) {
        logger.info("### /about");
        model.addAttribute("wrapClass", "sec_about_g6");
    	return "/about";
    }
    
    @RequestMapping("/benefit")
    String benefit(Model model) {
        logger.info("### /benefit");
        model.addAttribute("wrapClass", "sec_benefit");
    	return "/benefit";
    }
    
    @RequestMapping("/question")
    String question(Model model) {
        logger.info("### /question");
        model.addAttribute("wrapClass", "sec_faq");
    	return "/question";
    }
    
    @RequestMapping("/store-search")
    String event(Model model) {
        logger.info("### /store-search");
        model.addAttribute("wrapClass", "sec_event");
    	return "/store-search";
    }
    
    @RequestMapping("/reserve-confirm1")
    String reserveConfirm1(Model model) {
        logger.info("### /reserve-confirm1");
    	return "/reserve-confirm1";
    }
    
    @RequestMapping(value = "/reserve-confirm2", method = RequestMethod.POST)
    String reserveConfirm2(Model model, @RequestParam String username, @RequestParam String handphone) {
    	logger.info("### /reserve-confirm2, username : {}, handphone : {}", username, handphone);
    	
    	if (username.isEmpty() || handphone.isEmpty() ) {
    		throw new BadRequestException("이름 또는 휴대폰 번호가 없습니다.");
    	}
    	
    	try {
    		List<OrderDetail> orders = orderService.getOrderList(username, handphone);
	    	model.addAttribute("orders", orders);
    	} catch (UplusApiException ue) {
        	throw new BadRequestException(ue.getMessage());
        }
        return "/reserve-confirm2";
    }
    
    @RequestMapping("/offline")
    String offline() {
    	logger.info("### /offline");
    	return "/offline";
    }    

    @RequestMapping("/map")
    String map(Model model, @RequestParam String address, @RequestParam String dlrNm) {
        logger.info("### /map");
        model.addAttribute("address", address);
        model.addAttribute("dlrNm", dlrNm);
    	return "/map";
    }  
    
    @RequestMapping("/pre")
    String pre(Model model, Device device, HttpServletRequest request) {
    	logger.info("### /pre");
    	
    	return "redirect:/main";
    	
//    	StringBuffer params = new StringBuffer();
//    	Enumeration<String> paramNames = request.getParameterNames();
//    	while (paramNames.hasMoreElements()){
//    	    String name = (String)paramNames.nextElement();
//    	    params.append(name).append("=").append(request.getParameter(name)).append("&");
//    	}
//    	
//    	if (params.length() > 0) {
//    		params.deleteCharAt(params.length()-1);
//    		params.insert(0, "?");
//    	}
//    	
//    	if (device.isNormal()) {
//    		model.addAttribute("eventUrl", eventUrl + params.toString());
//    	} else {
//    		model.addAttribute("eventUrl", eventUrlMobile + params.toString());
//    	}
//    	return "/pre";
    }
}
