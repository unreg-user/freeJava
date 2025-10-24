package wta.freeJava.system.containers;

import java.util.ArrayList;

public record Quote<T>(SimpleContainerType<T> containerType){
	public static final ArrayList<Quote<?>> quotes=new ArrayList<>();

	static {
		quotes.add(new Quote<>(new SimpleContainerType.CharContainerType('"', '"')));
		quotes.add(new Quote<>(new SimpleContainerType.CharContainerType('\'', '\'')));
	}
}
