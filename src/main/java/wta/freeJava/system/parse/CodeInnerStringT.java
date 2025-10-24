package wta.freeJava.system.parse;

public class CodeInnerStringT {
	public final String parent;
	public final Integer finish;
	public final String self;

	private CodeInnerStringT(String parent, int finish) {
		this.parent = parent;
		this.finish = finish;
		this.self = parent.substring(0, finish);
	}

	private CodeInnerStringT(String parent) {
		this.parent = parent;
		this.finish = null;
		this.self = parent;
	}

	public static CodeInnerStringT ofAll(String parent){
		return new CodeInnerStringT(parent);
	}

	public static CodeInnerStringT ofBack(String parent, int finish){
		return new CodeInnerStringT(parent, finish);
	}

	public static CodeInnerStringT of(String parent, int finish){
		finish++;
		return ofBack(parent, finish);
	}

	public String getOutParent(){
		if (finish==null || finish>=parent.length()) return null;
		return parent.substring(finish);
	}
}