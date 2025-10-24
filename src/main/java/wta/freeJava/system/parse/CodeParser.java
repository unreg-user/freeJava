package wta.freeJava.system.parse;

import wta.freeJava.system.codeStructure.JavafFile;
import wta.freeJava.system.containers.Bracket;

import static wta.freeJava.system.containers.Bracket.brackets;

public class CodeParser extends SimpleParser{
	public JavafFile fileFJ=new JavafFile();
	private int level=0;

	public CodeParser(String text) {
		super(text);
	}

	public JavafFile parseAll(){
		while (canParse()){
			for (Bracket<?> bI : brackets){
				bI.containerType().test(text, pos, true);
			}
			next();
		}
		if (level!=0) throw
		return fileFJ;
	}

	private JavafFile parseAll(String code){
		return fileFJ;
	}
}
