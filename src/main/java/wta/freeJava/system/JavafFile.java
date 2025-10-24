package wta.freeJava.system;

import java.util.*;

public class JavafFile {
	private static final HashMap<Character, Character> brackets=new HashMap<>();
	private static final Set<Character> bracketsSet;
	public ArrayList<CodeObject> inner=new ArrayList<>();

	public static JavafFile compileFor(String code){
		System.out.println(parseByBrackets(code));
		return new JavafFile();
	}

	public static ArrayList<String> parseByBrackets(String code){
		String iCode=code;
		ArrayList<String> elements=new ArrayList<>();
		while (iCode!=null){
			CodeInnerStringT i = parseByBrackets0(code);
			if (i==null) break;
			elements.add(i.self);
			iCode = i.getOutParent();
		}
		return elements;
	}

	/*/private static String parseByBrackets0(String code, Set<Character> bracketsSet){
		int i=1;
		int pos=0;
		Character symbol;
		Character bracket=null;
		Character bracket2=null;
		int len=code.length();

		while (true){
			symbol=code.charAt(i);
			if (pos==0 & bracketsSet.contains(symbol)){
				bracket=symbol;
				bracket2=brackets.get(symbol);
			}
			if (symbol==bracket){
				pos++;
			}
			if (symbol==bracket2){
				pos--;
			}
			if (pos==0 && symbol==';'){
				return code.substring(0, symbol);
			}
			i++;
			if (pos==0 && i==len){
				return null;
			}
		}
	}/*/

	private static CodeInnerStringT parseByBrackets0(String code) {
		if (code.isEmpty()) return null;

		int i = 0;
		int len = code.length();
		int pos = 0;
		Character openBracket = null;
		Character closeBracket = null;

		boolean inDoubleQuote = false;
		boolean inSingleQuote = false;

		while (i < len) {
			Character ch = code.charAt(i);

			// Обработка кавычек и экранирования — ТОЛЬКО для переключения состояния
			if (!inSingleQuote && !inDoubleQuote) {
				// Вне литералов — смотрим на скобки и ';'
				if (pos == 0 && bracketsSet.contains(ch)) {
					openBracket = ch;
					closeBracket = brackets.get(ch);
					pos = 1;
				} else if (ch == openBracket) {
					pos++;
				} else if (ch == closeBracket) {
					pos--;
					// После закрытия скобок может идти ';'
					if (pos == 0 && i + 1 < len && code.charAt(i + 1) == ';') {
						return new CodeInnerStringT(code, i); // включая ';'
					}
				} else if (pos == 0 && ch == ';') {
					return new CodeInnerStringT(code, i);
				}
			}

			// Обновление состояния литералов — ДАЖЕ если мы внутри скобок!
			// Потому что строки могут быть внутри аргументов: func("a(b);");
			if (ch == '"' && !inSingleQuote) {
				inDoubleQuote = !inDoubleQuote;
			} else if (ch == '\'' && !inDoubleQuote) {
				inSingleQuote = !inSingleQuote;
			} else if ((inDoubleQuote || inSingleQuote) && ch == '\\') {
				// Пропускаем следующий символ (экранированный)
				i++; // пропустить экранированный символ
			}

			i++;
		}

		return null; // незавершённая конструкция
	}

	static {
		brackets.putAll(Map.of('(', ')', '[', ']', '{', '}'));
		bracketsSet=brackets.keySet();
	}
}
