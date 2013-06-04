package com.atropos.generator.ceylon.code;

import java.util.Arrays;
import java.util.Collection;

import com.atropos.generator.code.CodeFile;
import com.atropos.generator.code.GameLogicCodeUnit;

public class CeylonGame implements GameLogicCodeUnit {

	private final String gameName;
	private CeylonGameLogic game;
	private CeylonGameConfiguration gameConfig;	
	
	public CeylonGame(final String gameName) {
		this.gameName = gameName;
		gameConfig = new CeylonGameConfiguration(this.gameName);
		game = new CeylonGameLogic(this.gameName, gameConfig.getClassname());
	}
	
	@Override
	public String getGameName() {
		return this.gameName;
	}

	@Override
	public void addEntitySupport(String entityName) {
		this.game.addSupportedEntity(entityName);
	}

	@Override
	public Collection<CodeFile> getFiles() {
						
		return Arrays.<CodeFile>asList( gameConfig, game );
		
	}

}
