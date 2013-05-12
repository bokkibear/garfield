import com.atropos.GarfieldInjectorProvider
import com.atropos.garfield.Model
import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(typeof(GarfieldInjectorProvider))
@RunWith(typeof(XtextRunner))
class EntityDeclarationTest {
	@Inject
	ParseHelper<Model> parser;
	
	@Test
	def void parseDomainmodel() {
		val model = parser.parse(
			"entity MyEntity {
			}");

		
		Assert::assertEquals(1, model.entities.size);
		val firstEntity = model.entities.head;
		Assert::assertEquals("MyEntity", firstEntity.name);	
	}
}