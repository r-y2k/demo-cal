package com.demo.cal.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

import com.demo.cal.constants.CalConstants;
import com.demo.cal.utils.CalUtils;
import com.demo.cal.utils.LogUtils;

import ch.qos.logback.classic.Logger;

public class CalulatorService {
	
	private final static Logger log =  LogUtils.getLogger(CalulatorService.class);
	
	private LinkedList<String> stack = new LinkedList<>();
	private Map<String, LinkedList<String>> variableScopeMap = new HashMap<String, LinkedList<String>>();
	
	
	public String evalCalculatorExpression(String expression) throws Exception {
		
		log.info("Input Expression: {}",expression);
		
		// min length assumed as at least add(1,1) => 8 
		if (expression == null || expression.length() < 8) throw new IllegalArgumentException();
		
		expression = expression.replaceAll("\\s+", "");
		char[] ar = expression.toCharArray();
		int i = 0, j = 0, count=0;
		
		while (j < ar.length) {

			// prepare tokens
			while (j < ar.length && !CalConstants.DELIMITER_SET.contains(ar[j])) {
				j++;
				continue;
			}

			// token
			String term = expression.substring(i, j);

			// check if term is a number
			if ( checkForLetVariable(term)) {
				checkAndAddToMap(stack.peek(), term); // check and add variable scope to map
			}  

			if (term.length() != 0){ // add token to stack
				stack.push(term); 
			}
			
			if (CalConstants.OPEN_PARENTHESIS_DELIMITER == ar[j]) { // add closing parenthesis for scope
				count++;
				stack.push("("); 
			} 
			else if (CalConstants.CLOSE_PARENTHESIS_DELIMITER == ar[j] ) { // pop and process expression on stack
				count--;
				 processExpressionOnStack();
				//stack.push(")");
			}

			j++; 
			i = j;
		}
		
		log.debug("variableScopeMap: {} ", variableScopeMap);
		//Collections.reverse(stack);
		log.debug("stack: " + stack.toString().replaceAll("\\s+", "") + " size: " + stack.size());
			
		if (count != 0 || stack.isEmpty() || stack.size() > 1) {
			log.error("Not a valid expression");
			throw new IllegalArgumentException();
		} 
		
		if(NumberUtils.isCreatable(stack.peek())) {
			// For general integer number: round down and scale to 0
			BigDecimal result = new BigDecimal(stack.pop());
			log.debug("Round to integer: from {} to {}",result,result.setScale(0, RoundingMode.DOWN).toString());
			return result.setScale(0, RoundingMode.DOWN).toString();
		}
		
		return stack.pop();
	}
	
	
	private  String getOperandVal ()  throws Exception {
		   try {  			
		        return NumberUtils.isCreatable(stack.peek()) ? stack.pop() : variableScopeMap.get(stack.pop()).getLast();
		   } catch (Exception e) {
			   throw new IllegalArgumentException();
		   }
	}

	
	// make Number specific calculations
	private  void processExpressionOnStack () throws Exception {

		log.debug("expression: {}  size: {}  variableScopeMap: {}",  stack.toString().replaceAll("\\s+", ""), stack.size(), variableScopeMap);

		String operand2 = getOperandVal();		
		String operand1 = getOperandVal();
		
		String operator = stack.pop();
		if (operator.equals("(")) { // skip "(" as operator
			operator = stack.pop();
		}

		log.debug("processExpressionOnStack: operand2: {}, operator1: {}, operator: {} ",  operand2 , operand1 , operator);

		if (CalConstants.ARTHMETIC_OPERATIONS_SET.contains(operator)) { // perform arithmetic operations
			
			String tmpRes = CalUtils.performArithmeticOperation(operator, operand1, operand2);

			// check if term is a number for Let
			if ( checkForLetVariable(tmpRes)) {
				checkAndAddToMap(stack.peek(), tmpRes); // check and add variable scope to map
			}
			stack.push(tmpRes);
			
		} else if (CalUtils.isValidCalculatorVariableName(operator)) { // perform "let" operation
			
			performLetOperation(operator, operand2);
			
		} else {
			
			log.error("ERROR not a valid operator: {}", operator);
			// throw error case 
			throw new IllegalArgumentException();
			
		}
	}
	
	
	/**
	 * To perform "let" operation
	 *  clear variable scope from map value as expression scope ends
	 **/
	private  void performLetOperation (String operator, String operand2) throws Exception {
		log.debug("Start performLetOperation stack: {}  size: {} variableScopeMap: {}", stack.toString().replaceAll("\\s+", ""), stack.size(), variableScopeMap);
		
		// pop elements till let
		stack.pop();// remove "("
		stack.pop();// remove "let"

		// clear variable scope item from map value list
		String clearLetVariableScope = variableScopeMap.get(operator).size() == 1 ? variableScopeMap.remove(operator).toString() : variableScopeMap.get(operator).remove(variableScopeMap.get(operator).size() - 1);
		if(!"".equals(clearLetVariableScope)) {
			log.debug("clearLetVariableScope: {}", clearLetVariableScope);
		}

		// check to see if need to set "Let"
		if (!stack.isEmpty() && checkForLetVariable(operand2) && !NumberUtils.isCreatable(stack.peek())) {
			checkAndAddToMap(stack.peek(), operand2);
		}
		stack.push(operand2);
		log.debug("End performLetOperation stack: {}  size: {} variableScopeMap: {}", stack.toString().replaceAll("\\s+", ""), stack.size(), variableScopeMap);
	}
	
	private  boolean checkForLetVariable (String isNumber) {
		
		if (NumberUtils.isCreatable(isNumber)) {
			if(stack.size()>=3){
				String eleVar = stack.pop();
				String eleParanthesis = stack.pop();
				String eleLet = stack.pop();
				stack.push(eleLet);
				stack.push(eleParanthesis);
				stack.push(eleVar);
				if(CalConstants.LET_OPERATION.equals(eleLet)){
					return true;
				}
			}
		}		
		return false;
	}
	
	
    /**
	 * To check and add valid variable name/value to variable scope map
	 **/
	private  void checkAndAddToMap (String key, String val) throws Exception{
		
		if (CalUtils.isValidCalculatorVariableName(key)) {
			if (variableScopeMap.get(key) == null) {
				LinkedList<String> item = new LinkedList<String>();
				item.add(val);
				variableScopeMap.put(key, item);
			} else {
				variableScopeMap.get(key).add(val);
			}
		}else{
			log.error("Expecting a valid variable name but found: {}", key);
			// throw not a valid expression
			throw new IllegalArgumentException();
		}
	}
	

}
