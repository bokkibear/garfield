package com.atropos.generator.ceylon.code;

import com.atropos.generator.ceylon.CeylonLanguageUtils;

public class CeylonEntityImplementation extends CeylonCodeFile {

	private final String entityName;
	private final String interfaceClassName;
	
	public CeylonEntityImplementation(final String entityName, final String interfaceClassName) {
		this.entityName = entityName;
		this.interfaceClassName = interfaceClassName;
	}
	
	@Override
	public String getCode() {
		return "class " + getClassname() + "() satisfies " + this.interfaceClassName + " {\n}\n";
	}

	@Override
	public String getClassname() {
		return CeylonLanguageUtils.toValidClassname(this.entityName + "Impl");
	}

}
