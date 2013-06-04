package com.atropos.generator.ceylon.code;

import com.atropos.generator.code.CodeFile;

public abstract class CeylonCodeFile implements CodeFile {

	public abstract String getClassname();
	
	@Override
	public String getFilename() {
		return this.getClassname() + ".ceylon";
	}	

}
