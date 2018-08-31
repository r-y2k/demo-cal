package com.demo.cal.data;

public class CalculatorTestData {
	
	/*
	 * Valid cases
	 * 2D array format expression , expected value
	 */
	public  final String [][] validDataToEvaluate = {
			{"add(1, 2)"                                               ,"3"},
			{"add(1, mult(2, 3))"                                      ,"7"},
			{"mult(add(2, 2), div(9, 3))"                             ,"12"},
			{"let(a, 5, add(a, a))"                                   ,"10"},
			{"let(a, 5, let(b, mult(a, 10), add(b, a)))"              ,"55"},
			{"let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))"   ,"40"},
			{"let(a, let(b, -1, add(b, b)), let(b, 20, add(a, b)))"   ,"18"},
			{"let(a, let(b, -1, sub(-5, b)), let(b, 20, add(a, b)))"  ,"16"},
			{"let(a, let(b, sub(10,11), sub(-5, b)), let(b, 20, add(a, b)))"  ,"16"},
			{"let(a, let(b, add(4.321e+4,3), sub(-5, b)), let(b, 20, add(a, b)))"  ,"-43198"},
	};
	
	/*
	 * Edge cases: integer max value add, sub, div and mult 
	 * float numbers, rounding div
	 * 2D array format expression , expected value
	 */
	public  final  String [][] validEdgeDataEvaluate = {
			{"let(a, let(b, sub(2147483647,-2147483648), sub(b,-5)), let(b, 20, add(a, b)))", "4294967320"},
			{"let(a, let(b, add(2147483647,-2147483648), sub(b,-5)), let(b, 20, add(a, b)))", "24"},
			{"let(a, let(b, add(2147483647,1), sub(b,-5)), let(b, 20, add(a, b)))", "2147483673"},
			{"let(a, let(b, div(2147483647,1), sub(b,-5)), let(b, 20, add(a, b)))", "2147483672"},
			{"let(a, let(b, mult(2147483647,2147483647), sub(b,-5)), let(b, 20, add(a, b)))", "4611686014132420634"},
			{"let(a, let(b, div(10,3), sub(-5, b)), let(b, 20, add(a, b)))"  ,"11"},
			{"let(a, let(b, add(10.2,3), sub(-5, b)), let(b, 2.25, add(a, b)))" ,"-15"},
			
	};
	
	
	/*
	 * Invalid data Exception 
	 * cases: using key words like add, mult, sub, let, div as variable names
	 * out of scope variable or not valid expression 
	 * division by 0
	 * 1D array format expression
	 */
	public  final  String [] invalidDataToEvaluate = {
			"let(add, 5, add(add, add))", 	
			"add(let(a,10, add(a,a)),let(b,10, add(a,b)))" ,
			"let(",
			"let((((((((((",
			"add(let(a,10, div(a,0)),let(b,20, add(a,b)))",
			null,
	};

}
