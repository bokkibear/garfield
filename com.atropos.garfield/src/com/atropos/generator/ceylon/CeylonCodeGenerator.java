package com.atropos.generator.ceylon;

import com.atropos.generator.CodeGenerator;
import com.atropos.generator.ceylon.code.CeylonEntity;
import com.atropos.generator.ceylon.code.CeylonGame;
import com.atropos.generator.code.EntityCodeUnit;
import com.atropos.generator.code.GameLogicCodeUnit;

public class CeylonCodeGenerator implements CodeGenerator {

	@Override
	public EntityCodeUnit generateEntityCodeUnit(String entityName,
			String packageName) {

		return new CeylonEntity(entityName);
		
	}

	@Override
	public GameLogicCodeUnit generateGameLogic(String gameName, String packageName) {

		return new CeylonGame(gameName);
		
	}
		


	
}
