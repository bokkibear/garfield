package com.atropos.generator.ceylon.code;

import java.util.Arrays;
import java.util.Collection;

import com.atropos.generator.code.CodeFile;
import com.atropos.generator.code.EntityCodeUnit;

public class CeylonEntity implements EntityCodeUnit {

	private final String entityName;
	private final CeylonEntityInterface entityInterface;
	private final CeylonEntityImplementation entityImplementation;
	
	public CeylonEntity(final String entityName) {
		this.entityName = entityName;
		entityInterface = new CeylonEntityInterface(this.entityName);				
		entityImplementation = new CeylonEntityImplementation(entityName, entityInterface.getClassname());
	}
	
	@Override
	public Collection<CodeFile> getFiles() {											
		return Arrays.<CodeFile>asList(entityInterface, entityImplementation);
	}

	@Override
	public String getEntityName() {
		return this.entityName;
	}
	
	public String getEntityInterfaceClassname() {
		return entityInterface.getClassname();
	}
	
	public String getEntityImplementationClassname() {
		return entityImplementation.getClassname();
	}

}
