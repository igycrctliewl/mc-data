package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VillageTest {

	@Test
	public void testEntityBuilder() {
		Village village = Village.builder()
				.id( 10L )
				.name("Montara")
				.build();
		assertNotNull( village );
		assertNotNull( village.toString() );
		assertEquals( 10L, village.getId().longValue() );
		assertEquals( "Montara", village.getName() );
	}

	@Test
	public void testEntitySetters() {
		Village village = new Village();
		village.setId( 10L );
		village.setName("Montara");
		assertNotNull( village );
		assertNotNull( village.toString() );
		assertEquals( 10L, village.getId().longValue() );
		assertEquals( "Montara", village.getName() );
	}

	@Test
	public void testEntityNullProperties() {
		Village village = new Village();
		assertNotNull( village );
		assertNotNull( village.toString() );
		assertNull( village.getId() );
		assertNull( village.getName() );
	}

}
