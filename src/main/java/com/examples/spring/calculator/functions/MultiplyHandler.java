package com.examples.spring.calculator.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.examples.spring.ExampleSpringApplication;
import com.examples.spring.beans.ArgumentsJson;
import com.examples.spring.business.CalculatorService;

/**
 * 
 * @author Prashanth Errabelli
 *
 */
@Component
public class MultiplyHandler implements RequestHandler<ArgumentsJson, Integer> {

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

	@Autowired
	CalculatorService service;

	public Integer handleRequest(ArgumentsJson args, Context context) {

		System.out.println("starts handleRequest");
		MultiplyHandler handler = (MultiplyHandler) getApplicationContext().getBean(MultiplyHandler.class);
		Integer product = handler.multiply(args, context);
		System.out.println("ends handleRequest");
		return product;
	}

	private Integer multiply(ArgumentsJson args, Context context) {

		System.out.println("Example CalculatorProcessor (/multiply) ->  a : " + args.getA() + ",  b:{}" + args.getB());

		return service.multiply(args.getA(), args.getB());

	}

}
