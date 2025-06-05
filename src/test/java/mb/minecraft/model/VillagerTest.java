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
				.id( 101 )
				.name("Gary")
				.villageId( 1 )
				.typeId( 2 )
				.build();
		assertNotNull( villager );
		assertEquals( 101, villager.getId().intValue() );
		assertEquals( "Gary", villager.getName() );
		assertEquals( 1, villager.getVillageId().intValue() );
		assertEquals( 2, villager.getTypeId().intValue() );
		assertFalse( villager.isTagged() );
	}

	@Test
	public void testEntitySetters() {
		Villager villager = new Villager();
		villager.setId( 101 );
		villager.setName("Gary");
		villager.setTagged( true );
		villager.setVillageId( 1 );
		villager.setTypeId( 2 );
		assertNotNull( villager );
		assertEquals( 101, villager.getId().intValue() );
		assertEquals( "Gary", villager.getName() );
		assertEquals( 1, villager.getVillageId().intValue() );
		assertEquals( 2, villager.getTypeId().intValue() );
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
