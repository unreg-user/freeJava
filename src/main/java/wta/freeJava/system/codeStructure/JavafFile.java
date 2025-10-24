package wta.freeJava.system.codeStructure;

import wta.freeJava.system.parse.CodeParser;

public class JavafFile extends CommonJFCodeObject{
	public static JavafFile compileFor(String code){
		return new CodeParser(code).parseAll();
	}
}
