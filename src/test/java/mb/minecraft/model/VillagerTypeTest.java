package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VillagerTypeTest {

	@Test
	public void testEntityBuilder() {
		VillagerType villagerType = VillagerType.builder()
				.id( 201L )
				.profession("Farmer")
				.build();
		assertNotNull( villagerType );
		assertNotNull( villagerType.toString() );
		assertEquals( 201L, villagerType.getId().longValue() );
		assertEquals( "Farmer", villagerType.getProfession() );
	}

	@Test
	public void testEntityNullProperties() {
		VillagerType villagerType = new VillagerType();
		assertNotNull( villagerType );
		assertNotNull( villagerType.toString() );
		assertNull( villagerType.getId() );
		assertNull( villagerType.getProfession() );
	}

}
