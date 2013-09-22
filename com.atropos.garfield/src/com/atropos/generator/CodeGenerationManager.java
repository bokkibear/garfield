package com.atropos.generator;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;

import com.atropos.garfield.Credits;
import com.atropos.garfield.Entity;
import com.atropos.garfield.Game;
import com.atropos.generator.code.CodeFile;
import com.atropos.generator.code.CodeUnit;
import com.atropos.generator.code.EntityCodeUnit;
import com.atropos.generator.code.GameInfoCodeUnit;
import com.atropos.generator.code.GameLogicCodeUnit;

public final class CodeGenerationManager {
	
	private final Resource resource;
	private final CodeGenerator codeGenerator;
	private final IFileSystemAccess fileSystem;	

	public CodeGenerationManager(final Resource resource, final IFileSystemAccess fileSystem, final CodeGenerator codeGenerator) {		
		this.resource = resource;		
		this.fileSystem = fileSystem;
		this.codeGenerator = codeGenerator;
	}
	
	public void doCodeGeneration() {
				
		Collection<EntityCodeUnit> entities = createEntitySpecs();		
		
		GameInfoCodeUnit gameInfo = createGameInfo();		
		GameLogicCodeUnit game = createGameCore(gameInfo);
		
		writeCode(gameInfo);
		writeCode(entities);
		writeCode(game);
	}
	
	private GameInfoCodeUnit createGameInfo() {
		Game game = getModelObject(Game.class);
		Iterable<Credits> credits = getModelObjects(Credits.class);
		GameInfoCodeUnit gameInfo = codeGenerator.generateGameInfo(game.getName(), credits);
		
		return gameInfo;
	}
	
	private GameLogicCodeUnit createGameCore(GameInfoCodeUnit gameInfo) {				
		
		Game game = getModelObject(Game.class);
		
		GameLogicCodeUnit gameLogic = codeGenerator.generateGameLogic(game.getName(), gameInfo, "");
		for (Entity entity : getModelEntities()) {							
			gameLogic.addEntitySupport(entity.getName());			
		}				
		return gameLogic;
	}
	
	private Collection<EntityCodeUnit> createEntitySpecs() {		
		final Collection<EntityCodeUnit> entities = new LinkedList<EntityCodeUnit>();
		for (Entity entity : getModelEntities()) {
			
			EntityCodeUnit entityCodeUnit = codeGenerator.generateEntityCodeUnit(entity.getName(), "");
			entities.add(entityCodeUnit);			
						
		}		
		return entities;
	}
	
	private void writeCode(Iterable<? extends CodeUnit> units) {
		for (CodeUnit codeUnit : units) {
			writeCode(codeUnit);
		}
	}
	
	private void writeCode(CodeUnit unit) {
		for (CodeFile file : unit.getFiles()) {
			fileSystem.generateFile(file.getFilename(), file.getCode());
		}		
	}
	
	private Iterable<Entity> getModelEntities() {
		return getModelObjects(Entity.class);
	}
	
	@SuppressWarnings("unchecked")
	private <E extends EObject> E getModelObject(Class<E> modelClass) {
		TreeIterator<EObject> allContents = resource.getAllContents();
		while( allContents.hasNext()) {
			EObject next = allContents.next();
			if (modelClass.isAssignableFrom(next.getClass())) {
				return (E)next;
			}
		}
		throw new RuntimeException("No model object of class " + modelClass + " was found!");
	}
	
	@SuppressWarnings("unchecked")
	private <E extends EObject> Iterable<E> getModelObjects(Class<E> modelClass) {
		Collection<E> results = new LinkedList<E>(); 
		TreeIterator<EObject> allContents = resource.getAllContents();
		while( allContents.hasNext()) {
			EObject next = allContents.next();
			if (modelClass.isAssignableFrom(next.getClass())) {
				results.add((E) next);
			}
		}
		return results;
	}
	
}
