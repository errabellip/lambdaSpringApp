package com.examples.spring.calculator.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.examples.spring.ExampleSpringApplication;
import com.examples.spring.LoggingConfigLoader;
import com.examples.spring.beans.ArgumentsJson;
import com.examples.spring.business.CalculatorService;

/**
 * 
 * @author Prashanth Errabelli
 *
 */
@Component
public class AddHandler implements RequestHandler<ArgumentsJson, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(AddHandler.class);

	private static ApplicationContext context;
	static {
		try {
			LoggingConfigLoader logConfigurator = new LoggingConfigLoader("/var/task/logback.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String environmentName = System.getenv("DAI_ENV");
		context = new AnnotationConfigApplicationContext(ExampleSpringApplication.class); // new
																							// SpringApplicationBuilder().sources(ExampleSpringApplication.class).profiles(environmentName)
		// .build().run();
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}

	@Autowired
	CalculatorService service;

	public Integer handleRequest(ArgumentsJson args, Context context) {

		AddHandler handler = getApplicationContext().getBean(AddHandler.class);

		logger.debug("starts handleRequest");
		Integer sum = handler.add(args, context);
		logger.debug("ends handleRequest");
		return sum;
	}

	private Integer add(ArgumentsJson args, Context context) {

		System.out.println("Example CalculatorProcessor (/add) ->  a : " + args.getA() + ",  b:{}" + args.getB());

		return service.add(args.getA(), args.getB());

	}
}
