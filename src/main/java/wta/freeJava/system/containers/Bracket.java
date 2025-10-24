package wta.freeJava.system.containers;

import java.util.ArrayList;

public record Bracket<T>(SimpleContainerType<T> containerType){
	public static final ArrayList<Bracket<?>> brackets=new ArrayList<>();

	static {
		brackets.add(new Bracket<>(new SimpleContainerType.CharContainerType('{', '}')));
		brackets.add(new Bracket<>(new SimpleContainerType.CharContainerType('(', ')')));
		brackets.add(new Bracket<>(new SimpleContainerType.CharContainerType('[', ']')));
	}
}
