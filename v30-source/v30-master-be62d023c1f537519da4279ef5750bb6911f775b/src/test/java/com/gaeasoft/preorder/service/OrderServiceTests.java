package com.gaeasoft.preorder.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gaeasoft.preorder.remote.UplusApiProviderTests;

public class OrderServiceTests {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceTests.class);

	private static final String ENABLE_CANCEL_CDS[] = {"T", "I", "6", "01", "02", "03", "04", "05", "06", "92", "93", "94", "95"};
	private static final String ENABLE_WRITE_CDS[] = {"I", "6", "01", "02", "03", "04", "05", "06", "92", "93", "94", "95"};
	private static final String CDS[] = {"I" ,"6" ,"1" ,"2" ,"3" ,"01" ,"02" ,"03" ,"04" ,"05" ,"06" ,"92" ,"93" ,"94" ,"95" ,"T" ,"4" ,"5" ,"D" ,"E"};
	
	private static Boolean getEnableCancel(String statCd) {
		return Arrays.asList(ENABLE_CANCEL_CDS).contains(statCd);
	}
	
	private static Boolean getEnableWrite(String statCd) {
		return Arrays.asList(ENABLE_WRITE_CDS).contains(statCd);
	}	
	
	public static void main(String[] args) {
		for (int i=0; i < CDS.length; i++) {
			String statCd = CDS[i];
			logger.debug("[{}] enableWrite: {}, enableCancel: {}", statCd, getEnableWrite(statCd), getEnableCancel(statCd));
		}
	}
	
}
