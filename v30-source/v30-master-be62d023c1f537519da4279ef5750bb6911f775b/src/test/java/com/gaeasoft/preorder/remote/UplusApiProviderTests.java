package com.gaeasoft.preorder.remote;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gaeasoft.preorder.remote.vo.Order;
import com.gaeasoft.preorder.remote.vo.OrderDetail;
import com.gaeasoft.preorder.remote.vo.ReqInfo;
import com.gaeasoft.preorder.remote.vo.ResponseInventoryList;
import com.gaeasoft.preorder.remote.vo.ResponseOrderDetail;
import com.gaeasoft.preorder.remote.vo.ResponseOrderList;
import com.gaeasoft.preorder.remote.vo.ResponseRegistry;
import com.gaeasoft.preorder.remote.vo.ResponseReqInfo;
import com.gaeasoft.preorder.remote.vo.ResponseToken;
import com.gaeasoft.preorder.remote.vo.Orders;

public class UplusApiProviderTests {

	private static final Logger logger = LoggerFactory.getLogger(UplusApiProviderTests.class);

	private static final String ENDPOINT = "http://pre-salemobile.uplus.co.kr";
//	private static final String CHANNEL_ID = "I02"; //시스템에 할당된 인입채널 코드 "R3000004"
	private static final String CHANNEL_ID = "R3000004"; //시스템에 할당된 인입채널 코드 "R3000004"
	private static final String RESV_SALE_CODE = "20170007";
	private static final String IS_SEC = "Y"; //암호화 대상 여부
	private static final String MOD_TYPE_CD = "PH009";  // 모델번호 : LGM-G600L
	private static final String SIZE_CD = "SZ006";  // 용량코드 : 64기가
//	private static final String SIZE_CD = "SZ007";  // 용량코드 : 128기가
	private static final String COLR_CD = "CO005";  // 색상코드 : 블루
//	private static final String COLR_CD = "CO006";  // 색상코드 : 블랙
//	private static final String COLR_CD = "CO007";  // 색상코드 : 실버
//	private static final String COLR_CD = "CO008";  // 색상코드 : 라벤더
	private static final String GIFT_CD = "Gift002";  // 사은품코드 : 이모티콘
//	private static final String RECE_CD = "D01";  // 신청방법 : 택배수령
	private static final String RECE_CD = "D02";  // 신청방법 : 매장수령

	private static final String APIS[] = {
	        "예약판매등록 /api/v2/INVE002.hpix",
	        "예약판매가능여부 /api/v2/INVE001.hpix",
	        "예약판매신청목록 /api/v2/INVE003.hpix",
	        "예약판매신청상세정보 /api/v2/INVE004.hpix",
	        "재고정보 /api/v2/INVE006.hpix",
	        "예약판매매장변경 /api/v2/INVE007.hpix",
	        "예약판매신청삭제 /api/v2/INVE008.hpix"
	};

	private static final int TEST_NO = 6; // 테스트 번호

	public static void main(String[] args) {
	    /*
	     * 토큰요청 /api/v2/INVE005.hpix
	     */
	    ResponseToken responseToken = UplusApiProvider.requestToken();
	    String token = responseToken.getBody().getToken();
	    logger.debug("### 토큰: {} ", token);

	    switch (TEST_NO) {
	        default:
	        case 0:
	            /*
	             * 예약판매등록 /api/v2/INVE002.hpix
	             */
	            ResponseRegistry responseRegistry = UplusApiProvider.order(token, "홍길동", "01062228476", MOD_TYPE_CD, SIZE_CD, COLR_CD, GIFT_CD, RECE_CD);
	            if ("00".equals(responseRegistry.getHeader().getResponseCode())) {
	                logger.debug("### {} OK, reqSeqNo: {}", APIS[TEST_NO], responseRegistry.getBody().getReqSeqNo());
	            } else {
	                logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
	                        , responseRegistry.getHeader().getResponseMessage(), responseRegistry.getHeader().getResponseCode());
	            }
	            break;
	        case 1:
	            /*
	             * 예약판매가능여부 /api/v2/INVE001.hpix
	             */
	            ResponseReqInfo responseReqInfo = UplusApiProvider.reqYn(token, "홍길동", "01062228476");
	            if ("00".equals(responseReqInfo.getHeader().getResponseCode())) {
	                logger.debug("### {} OK, reqCnt: {}, reqYn: {}", APIS[TEST_NO]
	                        , responseReqInfo.getBody().getReqCnt(), responseReqInfo.getBody().getReqYn());
	            } else {
	                logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
	                        , responseReqInfo.getHeader().getResponseMessage(), responseReqInfo.getHeader().getResponseCode());
	            }
	            break;
	        case 2:
	            /*
	             * 예약판매신청목록 /api/v2/INVE003.hpix
	             */
	            ResponseOrderList responseOrderList = UplusApiProvider.getOrders(token, "홍길동", "01062228476");
        		if ("00".equals(responseOrderList.getHeader().getResponseCode())) {
        		    logger.debug("### {} OK, totalCount: {}", APIS[TEST_NO]
        		            , responseOrderList.getHeader().getTotalCount());
        		    if (Integer.valueOf(responseOrderList.getHeader().getTotalCount()) > 0) {
        		        logger.debug("### {}, orders: {}", APIS[TEST_NO]
        		                , responseOrderList.getOrders().toString());
        		    }
        		} else {
        		    logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
        		            , responseOrderList.getHeader().getResponseMessage(), responseOrderList.getHeader().getResponseCode());
        		}
	            break;
	        case 3:
	            /*
	             * 예약판매신청상세정보 /api/v2/INVE004.hpix
	             */
	            ResponseOrderDetail responseOrderDetail = UplusApiProvider.getOrderDetail(token, "CE000106");
	            if ("00".equals(responseOrderDetail.getHeader().getResponseCode())) {
	                logger.debug("### {} OK, orderDetail: {}", APIS[TEST_NO]
	                        , responseOrderDetail.getOrderDetail().toString());
	            } else {
	                logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
	                        , responseOrderDetail.getHeader().getResponseMessage(), responseOrderDetail.getHeader().getResponseCode());
	            }
	            break;
	        case 4:
	            /*
	             * 재고정보 /api/v2/INVE006.hpix
	             */
	            ResponseInventoryList responseInventoryList = UplusApiProvider.getInventories(token, "홍길동", "01062228476");
	            if ("00".equals(responseInventoryList.getHeader().getResponseCode())) {
	                logger.debug("### {} OK, inventoryList: {}", APIS[TEST_NO]
	                        , responseInventoryList.getBody().getInventoryList().toString());
	            } else {
	                logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
	                        , responseInventoryList.getHeader().getResponseMessage(), responseInventoryList.getHeader().getResponseCode());
	            }
	            break;
	        case 5:
	            /*
	             * 예약판매매장변경 /api/v2/INVE007.hpix
	             */
	            ResponseRegistry responseRegistry2 = UplusApiProvider.updateStore(token, "KW000105", "P275535", "304529");
	            if ("00".equals(responseRegistry2.getHeader().getResponseCode())) {
                    logger.debug("### {} OK, reqSeqNo: {}", APIS[TEST_NO], responseRegistry2.getBody().getReqSeqNo());
	            } else {
	                logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
	                        , responseRegistry2.getHeader().getResponseMessage(), responseRegistry2.getHeader().getResponseCode());
	            }
	            break;
	        case 6:
	            /*
	             * 예약판매신청삭제 /api/v2/INVE008.hpix
	             */
	            ResponseRegistry responseRegistry3 = UplusApiProvider.cancelOrder(token, "KW000105");
	            if ("00".equals(responseRegistry3.getHeader().getResponseCode())) {
	                logger.debug("### {} OK, reqSeqNo: {}", APIS[TEST_NO]
	                        , responseRegistry3.getBody().getReqSeqNo());
	            } else {
	                logger.debug("### {} NOT OK, responseMessage: {}, responseCode: {}", APIS[TEST_NO]
	                        , responseRegistry3.getHeader().getResponseMessage(), responseRegistry3.getHeader().getResponseCode());
	            }
	            break;
	    }

	}
}
