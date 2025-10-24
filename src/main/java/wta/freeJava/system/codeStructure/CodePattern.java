package wta.freeJava.system.codeStructure;

import wta.freeJava.system.containers.Quote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public interface CodePattern{
	record CodeWord(String word) implements CodePattern{
		public static ArrayList<CodeWord> getWords(String string){
			return new ArrayList<>(Arrays.stream(string.split("\\s+"))
				  .map(CodeWord::new)
				  .toList());
		}
	}
	record CodeQuoteInner(String inner) implements CodePattern{ }
	record CodeBracketInner(Object type, ArrayList<CodeObject> inner) implements CodePattern{ }
}
