package com.demo.cal.constants;

import java.util.Arrays;
import java.util.HashSet;

public class CalConstants {

	public static final String VALID_VARIABLE_NAME = "[a-zA-Z]";
	
	public static final char OPEN_PARENTHESIS_DELIMITER = '(';
	public static final char CLOSE_PARENTHESIS_DELIMITER = ')';
	
	public static final String ADD_OPERATION = "add";
	public static final String SUB_OPERATION = "sub";
	public static final String MULT_OPERATION = "mult";
	public static final String DIV_OPERATION = "div";
	public static final String LET_OPERATION = "let";
	
	public static final HashSet<Character> DELIMITER_SET = new HashSet<>(Arrays.asList(',', OPEN_PARENTHESIS_DELIMITER, CLOSE_PARENTHESIS_DELIMITER));
	public static final HashSet<String> ARTHMETIC_OPERATIONS_SET = new HashSet<>(Arrays.asList(ADD_OPERATION, SUB_OPERATION, MULT_OPERATION, DIV_OPERATION));
	public static final HashSet<String> ASSIGN_OPERATIONS_SET = new HashSet<>(Arrays.asList(LET_OPERATION));

	public static final String USAGE_MSG = "Usage:\n\t java -jar cal-1.0-jar-with-dependencies.jar \"add(3,5)\" \n\t java -DlogLevel=INFO -jar cal-1.0-jar-with-dependencies.jar \"sub(3,5)\" \n\t java -DlogLevel=ERROR -jar cal-1.0-jar-with-dependencies.jar \"div(3,5)\" \n\t java -DlogLevel=DEBUG -jar cal-1.0-jar-with-dependencies.jar \"mult(3,5)\" \n";

}
