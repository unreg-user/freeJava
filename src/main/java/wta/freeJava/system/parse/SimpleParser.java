package wta.freeJava.system.parse;

public class SimpleParser {
	public final String text;
	public int pos=0;

	public SimpleParser(String text){
		this.text=text;
	}

	public void next(){
		pos++;
	}

	public void next(int value){
		pos+=value;
	}

	public char get(){
		return text.charAt(pos);
	}

	public boolean canParse(){
		return text.length()>pos;
	}
}
