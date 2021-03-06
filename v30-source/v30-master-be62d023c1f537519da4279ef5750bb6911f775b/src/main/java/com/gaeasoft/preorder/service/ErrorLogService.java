package com.gaeasoft.preorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaeasoft.preorder.domain.ErrorLog;
import com.gaeasoft.preorder.domain.ErrorLogRepository;


@Service
public class ErrorLogService {

	@Autowired
	private ErrorLogRepository errorLogRepository;

	@Transactional
	public void save(ErrorLog errorLog) {
		errorLogRepository.save(errorLog);
	}
}