package wta.freeJava.system;

import java.util.HashMap;
import java.util.function.Predicate;

public class Registries {
	public static final HashMap<String, CompilerStructure> keyWords=new HashMap<>();
	public static final HashMap<String, Predicate<CompilerStructure>> modifiers=new HashMap<>();
}
