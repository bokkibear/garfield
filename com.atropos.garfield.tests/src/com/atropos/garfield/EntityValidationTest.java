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
		
		for (Entity eachEntity : model.getEntities()) {
			validatorTester.validator().checkEntityNameIsUnique(eachEntity);
			validatorTester.diagnose().assertErrorContains("Entity names must be unique!");
		}
		
	}
	
	private Model createModelWithEntities(String... entityNames) {
		Model model = GarfieldFactory.eINSTANCE.createModel();
		EList<Entity> entities = new BasicEList<Entity>();
		for (String entityName : entityNames) {
			Entity entity = GarfieldFactory.eINSTANCE.createEntity();
			entity.setName(entityName);
		}
		model.eSet(GarfieldPackage.eINSTANCE.getModel_Entities(), entities);
		return model;
	}

}
