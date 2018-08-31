package com.demo.cal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.demo.cal.data.CalculatorTestData;
import com.demo.cal.service.CalulatorService;

@RunWith(JUnitPlatform.class)
public class CalculatorTest{
	
	private static CalculatorTestData testData;
	
	@BeforeAll
	public  static void initCalculatorTestData () {	
		testData = new CalculatorTestData();
	}
	
	
	@Test
	@DisplayName("Valid Expressions")
	public void testValidAdd() {
		
		for(String [] expToEval: testData.validDataToEvaluate){
			System.out.println("Expression: "+ expToEval[0] + " expected: "+ expToEval[1]);
			String result = null;
			try {
				CalulatorService calulatorService = new CalulatorService();
				result = calulatorService.evalCalculatorExpression(expToEval[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Assertions.assertTrue(expToEval[1].equals(result));
			System.out.println();
		} 
	}   
	
	
	@Test
	@DisplayName("Valid Edge Case Expressions")
	public void testValidEdgeExpressions()  {
		
		for(String [] expToEval: testData.validEdgeDataEvaluate){
			System.out.println("Expression: "+ expToEval[0] + " expected: "+ expToEval[1]);
			String result = null;
			try {
				CalulatorService calulatorService = new CalulatorService();
				result = calulatorService.evalCalculatorExpression(expToEval[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Assertions.assertTrue(expToEval[1].equals(result));	
			System.out.println();
		} 
	}  
	
	
	@Test
	@DisplayName("Valid Exception Thrown")
	public void testExpectedException() {	
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
		  CalulatorService calulatorService = new CalulatorService();
		  calulatorService.evalCalculatorExpression(testData.invalidDataToEvaluate[0]);
	  });
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
		  CalulatorService calulatorService = new CalulatorService();
		  calulatorService.evalCalculatorExpression(testData.invalidDataToEvaluate[1]);
	  });
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
		  CalulatorService calulatorService = new CalulatorService();
		  calulatorService.evalCalculatorExpression(testData.invalidDataToEvaluate[2]);
	  });
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
		  CalulatorService calulatorService = new CalulatorService();
		  calulatorService.evalCalculatorExpression(testData.invalidDataToEvaluate[3]);
	  });
	  Assertions.assertThrows(ArithmeticException.class, () -> {
		  CalulatorService calulatorService = new CalulatorService();
		  calulatorService.evalCalculatorExpression(testData.invalidDataToEvaluate[4]);
	  });
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
		  CalulatorService calulatorService = new CalulatorService();
		  calulatorService.evalCalculatorExpression(testData.invalidDataToEvaluate[5]);
	  });
	}
 
	
	@Test
	@DisplayName("Valid Division Expression")
	public void testValidDivisionExpressions()  {
		
		int j=7;
		for(int i=-3;i<3;i++,j--) {
			if(i==0||j==0) continue;
			String exp = "div("+j+","+i+")";
			System.out.println("Expression: "+ exp + " expected: "+ (j/i) );
			String result = null;
			try {
				CalulatorService calulatorService = new CalulatorService();
				result = calulatorService.evalCalculatorExpression(exp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Assertions.assertTrue((""+(j/i)).equals(result));	
			System.out.println();
			
		} 
	}  
	
	
}
