package com.atropos.garfield;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.validation.ValidatorTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.atropos.GarfieldStandaloneSetup;
import com.atropos.validation.GarfieldValidator;

@RunWith(XtextRunner.class)
public class EntityValidationTest extends AbstractXtextTests {
	
	private final GarfieldFactory factory = GarfieldFactory.eINSTANCE;
	private ValidatorTester<GarfieldValidator> validatorTester;
	
	@Before
	public void setup() throws Exception {
		with(GarfieldStandaloneSetup.class);
		GarfieldValidator validator = get(GarfieldValidator.class);
		validatorTester = new ValidatorTester<GarfieldValidator>(validator, getInjector());
	}
	
	@Test
	public void testEntityNamesMustBeUnique()  {
		
		Model model = createModelWithEntities("MyEntity", "MyEntity");		
		
		for (Entity eachEntity : Utilities.getEntitiesOf(model.getGame())) {
			validatorTester.validator().checkEntityNameIsUnique(eachEntity);
			validatorTester.diagnose().assertErrorContains("Entity names must be unique!");
		}
		
	}
	
	@Test
	public void testEntityNamesMayBeDifferent()  {
		
		Model model = createModelWithEntities("MyEntity1", "MyEntity2");		
		
		for (Entity eachEntity : Utilities.getEntitiesOf(model.getGame())) {
			validatorTester.validator().checkEntityNameIsUnique(eachEntity);
			validatorTester.diagnose().assertOK();
		}
		
	}
	
	private Model createModelWithEntities(String... entityNames) {
		Model model = factory.createModel();
		Game game = factory.createGame();
		model.setGame(game);				
		
		EList<Entity> entities = new BasicEList<Entity>();		
		for (String entityName : entityNames) {
			Entity entity = GarfieldFactory.eINSTANCE.createEntity();
			entity.setName(entityName);
			entities.add(entity);
		}
		game.eSet(GarfieldPackage.eINSTANCE.getGame_Declarations(), entities);
		return model;
	}

}
