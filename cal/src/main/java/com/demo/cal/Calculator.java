package com.demo.cal;

import com.demo.cal.constants.CalConstants;
import com.demo.cal.service.CalulatorService;
import com.demo.cal.utils.LogUtils;

import ch.qos.logback.classic.Logger; 

public class Calculator {
	
	private final static Logger log =  LogUtils.getLogger(Calculator.class);
	
	
	public static void main(String[] args) {
		
		if (args.length != 1) {
			
			System.out.println(CalConstants.USAGE_MSG);
			
			return;
		}
		 
		String expression = args[0];

		try {
			
			CalulatorService calulatorService = new CalulatorService();
			
			String  result =  calulatorService.evalCalculatorExpression(expression);
			
			log.info("expression: {} result: {}", expression, result);
			
			System.out.println(result);
			
		} catch (ArithmeticException e) {
			
			log.error("Not a number. ",e);
			
		} catch (Exception e) {
			
			log.error("Invalid input. ",e);
			
		}
		
	} 	
		
}

