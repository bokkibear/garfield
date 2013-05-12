package com.atropos.garfield;

import javax.inject.Inject;

import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.atropos.GarfieldInjectorProvider;

@RunWith(XtextRunner.class)
@InjectWith(GarfieldInjectorProvider.class)
public class EntityDeclarationTest extends AbstractXtextTests {
	
	@Inject
	private ParseHelper<Model> parser;		
	
	@Test
	public void testSingleEntityDeclaration() throws Exception {
		
		Model parsedModel = parser.parse("entity MyEntity {}");		
		assertNotNull(parsedModel);
		assertEquals(1, parsedModel.getEntities().size());		
		
		Entity firstEntity = parsedModel.getEntities().get(0);		
		assertEquals("MyEntity", firstEntity.getName());
		
	}

}
