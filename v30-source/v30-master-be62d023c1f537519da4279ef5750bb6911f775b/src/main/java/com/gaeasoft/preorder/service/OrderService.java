package com.gaeasoft.preorder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaeasoft.preorder.domain.IntegrationStore;
import com.gaeasoft.preorder.domain.IntegrationStoreRepository;
import com.gaeasoft.preorder.domain.OrderHistory;
import com.gaeasoft.preorder.domain.OrderHistoryRepository;
import com.gaeasoft.preorder.domain.Store;
import com.gaeasoft.preorder.domain.StoreRepository;
import com.gaeasoft.preorder.exception.UplusApiException;
import com.gaeasoft.preorder.remote.UplusApiProvider;
import com.gaeasoft.preorder.remote.vo.Order;
import com.gaeasoft.preorder.remote.vo.OrderDetail;
import com.gaeasoft.preorder.remote.vo.ResponseOrderDetail;
import com.gaeasoft.preorder.remote.vo.ResponseOrderList;
import com.gaeasoft.preorder.remote.vo.ResponseRegistry;
import com.gaeasoft.preorder.remote.vo.ResponseToken;
import com.gaeasoft.preorder.web.support.OrderJsonForDetail;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	private static final String MOD_TYPE_CD = "PH009";  // 모델번호 : LGM-G600L
	private static final String SIZE_CD = "SZ006";  // 용량코드 : 64기가
	private static final String RECE_CD = "D02";  // 신청방법 : 매장수령
	private static final String ENABLE_CANCEL_CDS[] = {"T", "I", "6", "01", "02", "03", "04", "05", "06", "92", "93", "94", "95"};
	private static final String ENABLE_WRITE_CDS[] = {"I", "6", "01", "02", "03", "04", "05", "06", "92", "93", "94", "95"};
	private static final Boolean SAVE_HISTORY = false;

	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	@Autowired
	IntegrationStoreRepository storeRepository;

	/**
	 * 예약판매등록
	 *
	 * @param param
	 * @return
	 * @throws UplusApiException
	 */
	public OrderHistory order(OrderJsonForDetail param) {
		String token = getToken();
		ResponseRegistry response = UplusApiProvider.order(token,
				param.getName(),
				param.getPhonNo(),
				param.getModTypCd(),
				param.getSizeCd(),
				param.getColrCd(),
				param.getGiftCd(),
				RECE_CD);

		String reqSeqNo = "";
        if ("00".equals(response.getHeader().getResponseCode())) {
            reqSeqNo = response.getBody().getReqSeqNo();
            logger.info("### 예약판매등록 OK. reqSeqNo : {}", reqSeqNo);
        } else {
            logger.info("### 예약판매등록 NOT OK. responseMessage: {}, responseCode: {}"
                    , response.getHeader().getResponseMessage(), response.getHeader().getResponseCode());
            throw new UplusApiException(response.getHeader());
        }

		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setToken(token);
		orderHistory.setName(param.getName());
		orderHistory.setPhonNo(param.getPhonNo());
		orderHistory.setModTypCd(MOD_TYPE_CD);
		orderHistory.setSizeCd(SIZE_CD);
		orderHistory.setColrCd(param.getColrCd());
		orderHistory.setGiftCd(param.getGiftCd());
		orderHistory.setReceCd(RECE_CD);
		orderHistory.setReqDt(new Date());
		orderHistory.setReqSeqNo(reqSeqNo);
		orderHistory.setHistoryType("order");

		if (SAVE_HISTORY) {
    		orderHistory = orderHistoryRepository.save(orderHistory);
		}

		return orderHistory;
	}

	/**
	 * 예약판매매장변경
	 *
	 * @param reqSeqNo
	 * @param orgCd
	 * @param dlrCd
	 * @return
	 */
	public OrderHistory updateVisitStore(String reqSeqNo, String orgCd, String dlrCd) {
		String token = getToken();
		ResponseRegistry response = UplusApiProvider.updateStore(token,
				reqSeqNo,
				orgCd,
				dlrCd);

        if ("00".equals(response.getHeader().getResponseCode())) {
            reqSeqNo = response.getBody().getReqSeqNo();
            logger.info("### 예약판매매장변경 OK. reqSeqNo : {}", reqSeqNo);
        } else {
            logger.info("### 예약판매매장변경 NOT OK. responseMessage: {}, responseCode: {}"
                    , response.getHeader().getResponseMessage(), response.getHeader().getResponseCode());
            throw new UplusApiException(response.getHeader());
        }

		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setToken(token);
		orderHistory.setReqDt(new Date());
		orderHistory.setReqSeqNo(reqSeqNo);
		orderHistory.setOrgCd(orgCd);
		orderHistory.setDlrCd(dlrCd);
		orderHistory.setHistoryType("updateVisitStore");

        if (SAVE_HISTORY) {
            orderHistory = orderHistoryRepository.save(orderHistory);
        }

		return orderHistory;
	}

	/**
	 * 예약판매신청삭제
	 *
	 * @param param
	 * @return
	 */
	public OrderHistory cancelOrder(OrderJsonForDetail param) {
		String token = getToken();
		String reqSeqNo = "";
		ResponseRegistry response = UplusApiProvider.cancelOrder(token,
				param.getReqSeqNo());

		if ("00".equals(response.getHeader().getResponseCode())) {
            reqSeqNo = response.getBody().getReqSeqNo();
            logger.info("### 예약판매신청삭제 OK. reqSeqNo : {}", reqSeqNo);
        } else {
            logger.info("### 예약판매신청삭제 NOT OK. responseMessage: {}, responseCode: {}"
                    , response.getHeader().getResponseMessage(), response.getHeader().getResponseCode());
            throw new UplusApiException(response.getHeader());
        }

		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setToken(token);
		orderHistory.setReqDt(new Date());
		orderHistory.setReqSeqNo(reqSeqNo);
		orderHistory.setHistoryType("cancelOrder");

        if (SAVE_HISTORY) {
            orderHistory = orderHistoryRepository.save(orderHistory);
        }

		return orderHistory;
	}

	/**
	 * 예약판매신청목록
	 *
	 * @param name
	 * @param phonNo
	 * @return
	 */
	public List<OrderDetail> getOrderList(String name, String phonNo) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		String token = getToken();

		ResponseOrderList response = UplusApiProvider.getOrders(token, name, phonNo);

        if ("00".equals(response.getHeader().getResponseCode())) {
            logger.info("### 예약판매신청목록 OK. totalCount : {}", response.getHeader().getTotalCount());
            if (Integer.valueOf(response.getHeader().getTotalCount()) > 0) {
                for (Order order : response.getOrders().getOrderList()) {
                    OrderDetail orderDetail = getOrderDetailIn(token, order.getReqSeqNo());
                    if (orderDetail.getOrgCd() != null && orderDetail.getOrgCd() != "") {
                        List<IntegrationStore> stores = storeRepository.findByOrgCd(orderDetail.getOrgCd());
                        if (stores != null && stores.size() > 0) {
                            String orgTelNo = ""; //stores.get(0).getPhoneNumber();
                            String orgAddress = stores.get(0).getFullAddress();
                            orderDetail.setOrgTelNo(orgTelNo);
                            orderDetail.setOrgAddress(orgAddress);
                        }
                    }
                    orderDetails.add(orderDetail);
                }
            }
        } else {
            logger.info("### 예약판매신청목록 NOT OK. responseMessage: {}, responseCode: {}"
                    , response.getHeader().getResponseMessage(), response.getHeader().getResponseCode());
            throw new UplusApiException(response.getHeader());
        }

        return orderDetails;
	}

	/**
	 * 예약판매신청상세정보
	 *
	 * @param token
	 * @param reqSeqNo
	 * @return
	 */
	private OrderDetail getOrderDetailIn(String token, String reqSeqNo) {
	    ResponseOrderDetail response = UplusApiProvider.getOrderDetail(token, reqSeqNo);

	    if ("00".equals(response.getHeader().getResponseCode())) {
	        logger.info("### 예약판매신청상세정보 OK. reqSeqNo : {}", response.getOrderDetail().getReqSeqNo());
	    } else {
            logger.info("### 예약판매신청상세정보 NOT OK. responseMessage: {}, responseCode: {}"
                    , response.getHeader().getResponseMessage(), response.getHeader().getResponseCode());
	        throw new UplusApiException(response.getHeader());
	    }

	    OrderDetail orderDetail = response.getOrderDetail();
	    orderDetail.setEnableCancel(getEnableCancel(orderDetail.getStatCd()));
	    orderDetail.setEnableWrite(getEnableWrite(orderDetail.getStatCd()));

	    return orderDetail;
	}

	public OrderDetail getOrderDetail(String reqSeqNo) {
		String token = getToken();
		return this.getOrderDetailIn(token, reqSeqNo);
	}

	private Boolean getEnableCancel(String statCd) {
		return Arrays.asList(ENABLE_CANCEL_CDS).contains(statCd);
	}

	private Boolean getEnableWrite(String statCd) {
		return Arrays.asList(ENABLE_WRITE_CDS).contains(statCd);
	}

	/**
	 * 토큰요청
	 *
	 * @return
	 */
	private String getToken() {
	    ResponseToken response = UplusApiProvider.requestToken();
        String token = "";

	    if ("00".equals(response.getHeader().getResponseCode())) {
            token = response.getBody().getToken();
            logger.debug("### 토큰요청 OK. token : {}", token);
        } else {
            logger.info("### 토큰요청 NOT OK. responseMessage: {}, responseCode: {}"
                    , response.getHeader().getResponseMessage(), response.getHeader().getResponseCode());
            throw new UplusApiException(response.getHeader());
        }

	    return token;
	}
}
