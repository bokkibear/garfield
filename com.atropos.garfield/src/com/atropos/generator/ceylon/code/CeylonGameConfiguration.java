package com.atropos.generator.ceylon.code;

import com.atropos.generator.ceylon.CeylonLanguageUtils;

public class CeylonGameConfiguration extends CeylonCodeFile {

	private final String gameName;
	
	public CeylonGameConfiguration(final String gameName) {
		this.gameName = gameName;
	}
	
	@Override
	public String getCode() {
		return "shared class " + getClassname() + "() {\n}\n";
	}

	@Override
	public String getClassname() {
		String configurationName = this.gameName + "Configuration";
		return CeylonLanguageUtils.toValidClassname(configurationName);
	}

}
