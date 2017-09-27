package com.gaeasoft.preorder.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaeasoft.preorder.exception.UplusApiException;
import com.gaeasoft.preorder.remote.vo.ResponseReqInfo;
import com.gaeasoft.preorder.remote.vo.Inventory;
import com.gaeasoft.preorder.remote.vo.Order;
import com.gaeasoft.preorder.remote.vo.OrderDetail;
import com.gaeasoft.preorder.remote.vo.ReqInfo;
import com.gaeasoft.preorder.remote.vo.ResponseInventoryList;
import com.gaeasoft.preorder.remote.vo.ResponseOrderDetail;
import com.gaeasoft.preorder.remote.vo.ResponseOrderList;
import com.gaeasoft.preorder.remote.vo.Orders;
import com.gaeasoft.preorder.remote.vo.ResponseRegistry;
import com.gaeasoft.preorder.remote.vo.ResponseToken;

import lguplus.hpi.sys.utils.ApiCrypt;

public class UplusApiProvider {

	private static final Logger logger = LoggerFactory.getLogger(UplusApiProvider.class);

	private static final String ENDPOINT = "http://pre-salemobile.uplus.co.kr";
//	private static final String CHANNEL_ID = "I02"; //시스템에 할당된 인입채널 코드 "R3000004"
	private static final String CHANNEL_ID = "R3000004"; // 사전예약사이트
	private static final String RESV_SALE_CODE = "20170007"; // Great_V30
	private static final String IS_SEC = "Y"; //암호화 대상 여부

	/**
	 * 토큰요청 /api/v2/INVE005.hpix
	 *
	 * @return 토큰값
	 */
	public static ResponseToken requestToken() {

		ResponseToken responseObj = null;
		try {
	         //전달 파라미터 생성
		    String params = "channelId=" + CHANNEL_ID;
            String responseJson = request("/api/v2/INVE005.hpix", params);

            // Convert JSON string to Object
			ObjectMapper mapper = new ObjectMapper();
            responseObj = mapper.readValue(responseJson, ResponseToken.class);

		} catch (Exception e) {
		    logger.error("### IF_토큰요청 {}", e.getMessage());
		}

		return responseObj;
	}

	/**
	 * 예약판매가능여부 /api/v2/INVE001.hpix
	 *
	 * @param token : 호출 전 발급받은 토큰 값. 모든 요청에 대하여 토큰을 받은 후 호출하여야 함
	 * @param name : 고객명
	 * @param phonNo : 고객휴대전화번호
	 * @return
	 */
	public static ResponseReqInfo reqYn(String token, String name, String phonNo) {

		ResponseReqInfo responseObj = null;

		//암호화 대상인 인입채널인 경우 암호화
		//암호화 대상 인입채널이 아닌 경우 인코딩
		ApiCrypt capi;
		try {
		    if("Y".equals(IS_SEC)) {
				capi = new ApiCrypt();
				capi.setKeyHint(token);
				name = capi.enCrypt(name);
				phonNo = capi.enCrypt(phonNo);
			} else {
			    name = URLEncoder.encode(URLEncoder.encode(name,"UTF-8"));
			}
		    //전달 파라미터 생성
		    String params = "resvSaleCode=" + RESV_SALE_CODE + "&name=" + name + "&phonNo=" + phonNo + "&token=" + token;
		    String responseJson = request("/api/v2/INVE001.hpix", params);

            // Convert JSON string to Object
            ObjectMapper mapper = new ObjectMapper();
            responseObj = mapper.readValue(responseJson, ResponseReqInfo.class);

		} catch (Exception e) {
			logger.error("### IF_예약판매가능여부 {}", e.getMessage());
		} finally {
		    capi = null;
		}

		return responseObj;
	}

	/**
	 * 예약판매등록 /api/v2/INVE002.hpix
	 *
	 * @param token
	 * @param name
	 * @param phonNo
	 * @param modTypCd
	 * @param sizeCd
	 * @param colrCd
	 * @param giftCd
	 * @param receCd
	 * @return
	 */
	public static ResponseRegistry order(String token, String name, String phonNo
	        , String modTypCd, String sizeCd, String colrCd
	        , String giftCd, String receCd) {

	    ResponseRegistry responseObj = null;

	    //암호화 대상인 인입채널인 경우 암호화
	    //암호화 대상 인입채널이 아닌 경우 인코딩
	    ApiCrypt capi;
	    try {
	        if("Y".equals(IS_SEC)) {
	            capi = new ApiCrypt();
	            capi.setKeyHint(token);
	            name = capi.enCrypt(name);
	            phonNo = capi.enCrypt(phonNo);
	        } else {
	            name = URLEncoder.encode(URLEncoder.encode(name,"UTF-8"));
	        }

	        //전달 파라미터 생성
	        String params = "resvSaleCode=" + RESV_SALE_CODE
	                + "&name=" + name
	                + "&phonNo=" + phonNo
	                + "&modTypCd=" + modTypCd
	                + "&sizeCd=" + sizeCd
                    + "&colrCd=" + colrCd
                    + "&giftCd=" + giftCd
                    + "&receCd=" + receCd
	                + "&token=" + token;
            String responseJson = request("/api/v2/INVE002.hpix", params);

            // Convert JSON string to Object
            ObjectMapper mapper = new ObjectMapper();
            responseObj = mapper.readValue(responseJson, ResponseRegistry.class);

	    } catch(Exception e) {
	        logger.error("### IF_예약판매등록 {}", e.getMessage());
	    } finally {
	        capi = null;
	    }

	    return responseObj;
	}

	/**
	 * 예약판매신청목록 /api/v2/INVE003.hpix
	 *
	 * @param token
	 * @param name
	 * @param phonNo
	 * @return
	 */
	public static ResponseOrderList getOrders(String token, String name, String phonNo) {

		ResponseOrderList responseObj = null;

	    //암호화 대상인 인입채널인 경우 암호화
	    //암호화 대상 인입채널이 아닌 경우 인코딩
	    ApiCrypt capi;
	    try {
	        if("Y".equals(IS_SEC)) {
	            capi = new ApiCrypt();
	            capi.setKeyHint(token);
	            name = capi.enCrypt(name);
	            phonNo = capi.enCrypt(phonNo);
	        } else {
	            name = URLEncoder.encode(URLEncoder.encode(name,"UTF-8"));
	        }
	        //전달 파라미터 생성
	        String params = "resvSaleCode=" + RESV_SALE_CODE + "&name=" + name + "&phonNo=" + phonNo + "&token=" + token;
	        String responseJson = request("/api/v2/INVE003.hpix", params);

	        // Convert JSON string to Object
	        ObjectMapper mapper = new ObjectMapper();
	        responseObj = mapper.readValue(responseJson, ResponseOrderList.class);

	        if ("00".equals(responseObj.getHeader().getResponseCode())) {
	        	logger.info("### totalCount : {}", responseObj.getHeader().getTotalCount());
	        	if (Integer.parseInt(responseObj.getHeader().getTotalCount()) > 0) {
	        		capi = new ApiCrypt();
	        		capi.setKeyHint(token);
	        		responseObj.setOrders(convertJsonOrders(capi.deCrypt(responseObj.getBody())));
	        	}
	        }
	    } catch (Exception e) {
	        logger.error("### IF_예약판매신청목록 {}", e.getMessage());
	    } finally {
	        capi = null;
	    }

	    return responseObj;
	}

	private static Orders convertJsonOrders(String jsonOrders) throws JsonParseException, JsonMappingException, IOException {
		// Convert JSON string to Object
        ObjectMapper mapper = new ObjectMapper();
        Orders orders = mapper.readValue(jsonOrders, Orders.class);
        return orders;
	}

	/**
	 * 예약판매신청상세정보 /api/v2/INVE004.hpix
	 *
	 * @param token
	 * @param reqSeqNo
	 * @return
	 */
    public static ResponseOrderDetail getOrderDetail(String token, String reqSeqNo) {

        ResponseOrderDetail responseObj = null;
        ApiCrypt capi;
        try {

            //전달 파라미터 생성
            String params = "resvSaleCode=" + RESV_SALE_CODE + "&reqSeqNo=" + reqSeqNo + "&token=" + token;
            String responseJson = request("/api/v2/INVE004.hpix", params);

            // Convert JSON string to Object
            ObjectMapper mapper = new ObjectMapper();
            responseObj = mapper.readValue(responseJson, ResponseOrderDetail.class);

            if ("00".equals(responseObj.getHeader().getResponseCode())) {
	        	logger.info("### totalCount : {}", responseObj.getHeader().getTotalCount());
	        	if (Integer.parseInt(responseObj.getHeader().getTotalCount()) > 0) {
	        		capi = new ApiCrypt();
	        		capi.setKeyHint(token);
	        		responseObj.setOrderDetail(convertJsonOrderDetail(capi.deCrypt(responseObj.getBody())));
	        	}
            }
        } catch (Exception e) {
            logger.error("### IF_예약판매신청상세정보 {}", e.getMessage());
        } finally {
	        capi = null;
	    }

        return responseObj;
    }

	private static OrderDetail convertJsonOrderDetail(String jsonOrderDetail) throws JsonParseException, JsonMappingException, IOException {
		// Convert JSON string to Object
        ObjectMapper mapper = new ObjectMapper();
        OrderDetail orderDetail = mapper.readValue(jsonOrderDetail, OrderDetail.class);
        return orderDetail;
	}

	/**
	 * 재고정보 /api/v2/INVE006.hpix (권한없음)
	 *
	 * @param token
	 * @param name
	 * @param phonNo
	 * @return
	 */
    public static ResponseInventoryList getInventories(String token, String name, String phonNo) {

        ResponseInventoryList responseObj = null;

        //암호화 대상인 인입채널인 경우 암호화
        //암호화 대상 인입채널이 아닌 경우 인코딩
        ApiCrypt capi;
        try {
            if("Y".equals(IS_SEC)) {
                capi = new ApiCrypt();
                capi.setKeyHint(token);
                name = capi.enCrypt(name);
                phonNo = capi.enCrypt(phonNo);
            } else {
                name = URLEncoder.encode(URLEncoder.encode(name,"UTF-8"));
            }
            //전달 파라미터 생성
            String params = "resvSaleCode=" + RESV_SALE_CODE + "&name=" + name + "&phonNo=" + phonNo + "&token=" + token;
            String responseJson = request("/api/v2/INVE006.hpix", params);

            // Convert JSON string to Object
            ObjectMapper mapper = new ObjectMapper();
            responseObj = mapper.readValue(responseJson, ResponseInventoryList.class);

        } catch (Exception e) {
            logger.error("### IF_재고정보 {}", e.getMessage());
        } finally {
            capi = null;
        }

        return responseObj;
    }

    /**
     * 예약판매매장변경 /api/v2/INVE007.hpix
     *
     * @param token
     * @param reqSeqNo
     * @param orgCd
     * @param dlrCd
     * @return
     */
    public static ResponseRegistry updateStore(String token, String reqSeqNo, String orgCd, String dlrCd) {

        ResponseRegistry responseObj = null;

    	//암호화 대상인 인입채널인 경우 암호화
    	//암호화 대상 인입채널이 아닌 경우 인코딩
    	try {

    		//전달 파라미터 생성
    		String params = "resvSaleCode=" + RESV_SALE_CODE
    				+ "&reqSeqNo=" + reqSeqNo
    				+ "&orgCd=" + orgCd
    				+ "&dlrCd=" + dlrCd
    				+ "&token=" + token;
    		String responseJson = request("/api/v2/INVE007.hpix", params);

    		// Convert JSON string to Object
    		ObjectMapper mapper = new ObjectMapper();
    		responseObj = mapper.readValue(responseJson, ResponseRegistry.class);

    	} catch(Exception e) {
    	    logger.error("### IF_예약판매매장변경 {}", e.getMessage());
    	}

    	return responseObj;
    }

    /**
     * 예약판매신청삭제 /api/v2/INVE008.hpix
     *
     * @param token
     * @param reqSeqNo
     * @return
     */
	public static ResponseRegistry cancelOrder(String token, String reqSeqNo) {

	    ResponseRegistry responseObj = null;

	    //암호화 대상인 인입채널인 경우 암호화
	    //암호화 대상 인입채널이 아닌 경우 인코딩
	    try {
	        //전달 파라미터 생성
	        String params = "resvSaleCode=" + RESV_SALE_CODE
	                + "&reqSeqNo=" + reqSeqNo
	                + "&token=" + token;
            String responseJson = request("/api/v2/INVE008.hpix", params);

            // Convert JSON string to Object
            ObjectMapper mapper = new ObjectMapper();
            responseObj = mapper.readValue(responseJson, ResponseRegistry.class);

	    } catch(Exception e) {
	    	logger.error("### Error : {}", e);
	    	throw new UplusApiException(e.getMessage(), e.getCause());
	    }

	    return responseObj;
	}

	private static String request(String uri, String params) throws IOException {
	    logger.info("### [Request] uri: {}, params: {}", uri, params);
	    URL url = new URL(ENDPOINT + uri);
        HttpURLConnection urlc = (HttpURLConnection)url.openConnection();

        urlc.setRequestMethod("POST");
        urlc.setDoInput(true);
        urlc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlc.setInstanceFollowRedirects(false);
        urlc.setDoOutput(true);
        OutputStream os = urlc.getOutputStream();
        os.write((params).getBytes("UTF-8"));
        os.flush();
        os.close();

        StringBuffer sb = new StringBuffer();
        String sResponse = "";
        BufferedReader br = new java.io.BufferedReader(new InputStreamReader(urlc.getInputStream()));

        while ((sResponse = br.readLine()) != null) {
            sb.append(sResponse);
        }

        urlc.disconnect();

        logger.info("### [Response] uri: {}, responseJson : {}", uri, sb.toString());
        return sb.toString();
	}

}
