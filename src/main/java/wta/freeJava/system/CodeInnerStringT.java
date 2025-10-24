package wta.freeJava.system;

public class CodeInnerStringT {
	public final String parent;
	public final int finish;
	public final String self;

	public CodeInnerStringT(String parent, int finish) {
		finish += 1;

		this.parent = parent;
		this.finish = finish;
		this.self = parent.substring(0, finish);
	}

	public String getOutParent(){
		if (finish>=parent.length()) return null;
		return parent.substring(finish);
	}
}