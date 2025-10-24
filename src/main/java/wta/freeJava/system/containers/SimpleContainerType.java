package wta.freeJava.system.containers;

import java.util.regex.Pattern;

public interface SimpleContainerType<T>{
	T start();
	T end();
	boolean test(String code, int pos, boolean isStart);

	record CharContainerType(Character start, Character end) implements SimpleContainerType<Character> {
		@Override
		public boolean test(String code, int pos, boolean isStart) {
			return code.charAt(pos)==(isStart ? start : end);
		}
	}
	record StringContainerType(String start, String end) implements SimpleContainerType<String> {
		@Override
		public boolean test(String code, int pos, boolean isStart) {
			return code.startsWith((isStart ? start : end), pos);
		}
	}
	record RegexContainerType(Pattern start, int startLen, Pattern end, int endLen) implements SimpleContainerType<Pattern> {
		@Override
		public boolean test(String code, int pos, boolean isStart) {
			return (isStart ? start : end).matcher(code.substring(pos, pos+(isStart ? startLen : endLen))).lookingAt();
		}
	}
}
