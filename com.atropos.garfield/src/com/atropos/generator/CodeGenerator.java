package com.atropos.generator;

import com.atropos.generator.code.EntityCodeUnit;
import com.atropos.generator.code.GameLogicCodeUnit;

public interface CodeGenerator {

	EntityCodeUnit generateEntityCodeUnit(String entityName, String packageName);

	GameLogicCodeUnit generateGameLogic(String gameName, String packageName);
	
}
