package com.examples.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Prashanth Errabelli
 *
 */

@ComponentScan(basePackages = "com.examples", lazyInit = true)
@Configuration
// @Import({ RestTemplate.class })
public class ExampleSpringApplication {

}
