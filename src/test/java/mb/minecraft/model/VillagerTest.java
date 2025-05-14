package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VillagerTest {

	@Test
	public void testEntityBuilder() {
		Villager villager = Villager.builder()
				.id( 101L )
				.name("Gary")
				.villageId( 1L )
				.typeId( 2L )
				.build();
		assertNotNull( villager );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "Gary", villager.getName() );
		assertEquals( 1L, villager.getVillageId().longValue() );
		assertEquals( 2L, villager.getTypeId().longValue() );
		assertFalse( villager.isTagged() );
	}

	@Test
	public void testEntitySetters() {
		Villager villager = new Villager();
		villager.setId( 101L );
		villager.setName("Gary");
		villager.setTagged( true );
		villager.setVillageId( 1L );
		villager.setTypeId( 2L );
		assertNotNull( villager );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "Gary", villager.getName() );
		assertEquals( 1L, villager.getVillageId().longValue() );
		assertEquals( 2L, villager.getTypeId().longValue() );
		assertTrue( villager.isTagged() );
	}

	@Test
	public void testEntityNullProperties() {
		Villager villager = new Villager();
		assertNotNull( villager );
		assertNotNull( villager.toString() );
		assertNull( villager.getId() );
		assertNull( villager.getName() );
		assertFalse( villager.isTagged() );
		assertNull( villager.getVillageId() );
		assertNull( villager.getTypeId() );
	}

}
