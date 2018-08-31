package com.demo.cal.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.demo.cal.constants.CalConstants;

import ch.qos.logback.classic.Logger;

public class CalUtils {
	
	private final static Logger log = LogUtils.getLogger(CalUtils.class);
	
	/**
	 * To perform arithmetic operations using BigDecimal to handle large int sum, multiply, etc.
	 * Can make respective Arithmetic operation classes but we can leverage Java's BigDecimal for current operations
	 **/
	public static String performArithmeticOperation(String operator, String operand1, String operand2) throws Exception{
		
		log.debug("performArthmeticOperation:: operator {} operand1 {} operand2 {}" + operator, operand1, operand2);
		
		BigDecimal result = null;
		
		if (CalConstants.ADD_OPERATION.equals(operator)) {
			result = new BigDecimal(operand1).add(new BigDecimal(operand2));
		}
		else if (CalConstants.SUB_OPERATION.equals(operator)) {
			result = new BigDecimal(operand1).subtract(new BigDecimal(operand2));
		}
		else if (CalConstants.MULT_OPERATION.equals(operator)) {
			result = new BigDecimal(operand1).multiply(new BigDecimal(operand2));
		}
		else if (CalConstants.DIV_OPERATION.equals(operator)) {
			result = new BigDecimal(operand1).divide(new BigDecimal(operand2), 6, RoundingMode.HALF_UP);
		}else{
			System.out.println("Expecting a valid arthematic operation but found:" + operator);
			throw new IllegalArgumentException();
		}
	
		log.debug("performArthmeticOperation:: result {}", result);
		
		return result.toString();
	}

	/**
	 * Valid Variable name characters a-z, A-Z
	 * Assumption: Variable name can not be reserved words like add, sub, mult, div or let
	 **/
	public static boolean isValidCalculatorVariableName(String key){
		return (!CalConstants.ARTHMETIC_OPERATIONS_SET.contains(key) && !(CalConstants.ASSIGN_OPERATIONS_SET.contains(key)) && key.matches(CalConstants.VALID_VARIABLE_NAME));
	}

	
}
