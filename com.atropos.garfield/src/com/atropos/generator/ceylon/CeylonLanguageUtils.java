package com.atropos.generator.ceylon;

public final class CeylonLanguageUtils {

	private CeylonLanguageUtils() {}
	
	public static String toValidClassname(String identifier) {
		String result = identifier.substring(0, 1).toUpperCase() + identifier.substring(1);		
		return result;
	}
	
}
