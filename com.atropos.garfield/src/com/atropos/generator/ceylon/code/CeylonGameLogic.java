package com.atropos.generator.ceylon.code;

import java.util.HashSet;
import java.util.Set;

import com.atropos.generator.ceylon.CeylonLanguageUtils;

public class CeylonGameLogic extends CeylonCodeFile {

	private final String gameName;
	private final String gameConfigClassname;
	private final Set<String> supportedEntities = new HashSet<String>();
	private final String gameInfoClassname;
		
	public CeylonGameLogic(String gameName, String gameConfigClassname, String gameInfoClassname) {		
		this.gameName = gameName;
		this.gameConfigClassname = gameConfigClassname;
		this.gameInfoClassname = gameInfoClassname;		
	}

	@Override
	public String getCode() {
		String code = "";
		code += "shared class " + getClassname() + "(" + gameConfigClassname + " configuration) {\n";
		
		code += "   shared variable " + gameInfoClassname + " info = " + gameInfoClassname + "();\n";
		
		for (String entityName : supportedEntities) {
			CeylonEntity entity = new CeylonEntity(entityName);			
			String entityCollectionName = getEntityCollectionName(entityName);			
			String entityInterfaceClassname = entity.getEntityInterfaceClassname();
			
			code+= "   shared variable Set<" + entityInterfaceClassname + "> " + entityCollectionName + " = LazySet<" + entityInterfaceClassname + ">(empty);\n";
		}
		
		code += "}\n";
		return code;
	}

	private String getEntityCollectionName(String entityName) {
		return "all" + CeylonLanguageUtils.toValidClassname(entityName);
	}

	@Override
	public String getClassname() {
		return CeylonLanguageUtils.toValidClassname(gameName);
	}
	
	public void addSupportedEntity(String entityName) {
		this.supportedEntities.add(entityName);
	}

}
