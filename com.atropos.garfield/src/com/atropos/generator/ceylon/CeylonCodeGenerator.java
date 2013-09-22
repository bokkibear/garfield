package com.atropos.generator.ceylon;

import com.atropos.garfield.Credits;
import com.atropos.generator.CodeGenerator;
import com.atropos.generator.ceylon.code.CeylonEntity;
import com.atropos.generator.ceylon.code.CeylonGame;
import com.atropos.generator.ceylon.code.CeylonGameInfo;
import com.atropos.generator.code.EntityCodeUnit;
import com.atropos.generator.code.GameInfoCodeUnit;
import com.atropos.generator.code.GameLogicCodeUnit;

public class CeylonCodeGenerator implements CodeGenerator {

	@Override
	public EntityCodeUnit generateEntityCodeUnit(String entityName,
			String packageName) {

		return new CeylonEntity(entityName);
		
	}

	@Override
	public GameLogicCodeUnit generateGameLogic(String gameName,
			GameInfoCodeUnit gameInfo, String packageName) {
		return new CeylonGame(gameName, gameInfo);
	}	


	@Override
	public GameInfoCodeUnit generateGameInfo(String gameName,
			Iterable<Credits> credits) {
		
		StringBuilder s = new StringBuilder();
		for( Credits credit : credits ) {
			
			if ( s.length() > 0 ) s.append(", ");
			s.append(credit.getValue());
			
		}
		
		return new CeylonGameInfo(s.toString(), gameName);
		
	}

	

	
}
