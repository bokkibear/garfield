package com.atropos.generator;

import com.atropos.garfield.Credits;
import com.atropos.generator.code.EntityCodeUnit;
import com.atropos.generator.code.GameInfoCodeUnit;
import com.atropos.generator.code.GameLogicCodeUnit;

public interface CodeGenerator {

	EntityCodeUnit generateEntityCodeUnit(String entityName, String packageName);

	GameLogicCodeUnit generateGameLogic(String gameName, GameInfoCodeUnit gameInfo, String packageName);

	GameInfoCodeUnit generateGameInfo(String name, Iterable<Credits> credits);
	
}
