package wta.freeJava.interal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static boolean matchesAt(String text, String regex, int startPos) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		matcher.region(startPos, text.length()); // ограничиваем поиск с startPos до конца
		return matcher.lookingAt(); // проверяет, совпадает ли с начала региона
	}
}
