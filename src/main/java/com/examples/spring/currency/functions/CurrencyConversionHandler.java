package com.examples.spring.currency.functions;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.examples.spring.ExampleSpringApplication;
import com.examples.spring.beans.CurrencyConversionBean;
import com.examples.spring.business.CurrencyService;

/**
 * 
 * @author Prashanth Errabelli
 *
 */
@Component
public class CurrencyConversionHandler implements RequestHandler<CurrencyConversionBean, BigDecimal> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CurrencyService currencyService;

	private static ApplicationContext context;
	static {

		String environmentName = System.getenv("DAI_ENV");
		context = new AnnotationConfigApplicationContext(ExampleSpringApplication.class); // new
																							// SpringApplicationBuilder().sources(ExampleSpringApplication.class).profiles(environmentName)
		// .build().run();
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}

	public BigDecimal handleRequest(CurrencyConversionBean args, Context context) {

		logger.debug("starts handleRequest");
		CurrencyConversionHandler handler = (CurrencyConversionHandler) getApplicationContext()
				.getBean(CurrencyConversionHandler.class);
		BigDecimal totalAmount = handler.convert(args, context);
		logger.debug("ends handleRequest");
		return totalAmount;
	}

	public BigDecimal convert(CurrencyConversionBean args, Context context) {

		return currencyService.convert(args.getFrom(), args.getTo(), args.getQuantity());
	}

}