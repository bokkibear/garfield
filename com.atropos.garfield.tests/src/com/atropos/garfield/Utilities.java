package com.atropos.garfield;

import java.util.LinkedList;
import java.util.List;

public final class Utilities {

	public static List<Entity> getEntitiesOf(Game g) {
		List<Entity> results = new LinkedList<Entity>();
		for (GameSpecificDeclaration declaration : g.getDeclarations()) {
			if ( declaration instanceof Entity ) {
				results .add( (Entity) declaration);
			}
		}
		return results;
	}
	
}
