package com.atropos.generator.ceylon.code;

import com.atropos.generator.ceylon.CeylonLanguageUtils;

public class CeylonEntityInterface extends CeylonCodeFile {

	private final String entityName;
	
	public CeylonEntityInterface(String entityName) {		
		this.entityName = entityName;
	}

	@Override
	public String getCode() {
		return "shared interface " + this.getClassname() + " {\n}\n";
	}

	@Override
	public String getClassname() {
		return CeylonLanguageUtils.toValidClassname(entityName);
	}	


}
