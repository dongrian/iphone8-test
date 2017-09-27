package com.gaeasoft.preorder.config;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gaeasoft.preorder.appender.CustomLogbackAppender;
import com.gaeasoft.preorder.service.ErrorLogService;

@Configuration
public class LogContextConfig implements InitializingBean {

	@Autowired
	private LogConfig logConfig;

	@Autowired
	private ErrorLogService errorLogService;

	@Override
	public void afterPropertiesSet() throws Exception {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

		CustomLogbackAppender customLogbackAppender = new CustomLogbackAppender(errorLogService, logConfig);

		customLogbackAppender.setContext(loggerContext);
		customLogbackAppender.setName("customLogbackAppender");
		customLogbackAppender.start();
		loggerContext.getLogger("ROOT").addAppender(customLogbackAppender);
	}
}