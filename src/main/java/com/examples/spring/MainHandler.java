package com.examples.spring;

import com.amazonaws.services.lambda.runtime.RequestHandler;

public abstract class MainHandler<I, O> implements RequestHandler<I, O> {
	// SpringRequestHandler

	/*
	 * private static final ApplicationContext context = new
	 * AnnotationConfigApplicationContext( ExampleSpringApplication.class);
	 * 
	 * public ApplicationContext getApplicationContext() { return context; }
	 * 
	 * private final AddHandler handler;
	 * 
	 * public MainHandler() { handler =
	 * getApplicationContext().getBean(AddHandler.class); }
	 * 
	 * public O handleRequest(final I input, final Context context) { return (O)
	 * handler.handleRequest(input, context); }
	 */
}