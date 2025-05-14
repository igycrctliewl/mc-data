package mb.minecraft.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VillagerDtoTest {

	private static final VillagerTypeDto VILLAGER_TYPE = VillagerTypeDto.builder()
			.id( 301L )
			.profession( "Cleric" )
			.build();

	private static final VillageDto VILLAGE = VillageDto.builder()
			.id( 201L )
			.name( "Deep Water Cove" )
			.build();

	@Test
	public void testDtoByBuilder() {
		VillagerDto villager = VillagerDto.builder()
				.id( 101L )
				.name( "Liam Z" )
				.tagged( true )
				.type( VILLAGER_TYPE )
				.village( VILLAGE )
				.build();
		assertNotNull( villager );
		assertNotNull( villager.toString() );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "Liam Z", villager.getName() );
		assertTrue( villager.isTagged() );
		assertEquals( VILLAGER_TYPE, villager.getType() );
		assertEquals( VILLAGE, villager.getVillage() );
	}

	@Test
	public void testDtoByBuilderIdNull() {
		VillagerDto villager = VillagerDto.builder()
				.name( "Liam Z" )
				.tagged( true )
				.type( VILLAGER_TYPE )
				.village( VILLAGE )
				.build();
		assertNotNull( villager );
		assertNotNull( villager.toString() );
		assertNull( villager.getId() );
		assertEquals( "Liam Z", villager.getName() );
		assertTrue( villager.isTagged() );
		assertEquals( VILLAGER_TYPE, villager.getType() );
		assertEquals( VILLAGE, villager.getVillage() );
	}

	@Test
	public void testDtoBySetters() {
		VillagerDto villager = new VillagerDto();
		villager.setId( 101L );
		villager.setName( "Liam Z" );
		villager.setTagged( true );
		villager.setType( VILLAGER_TYPE );
		villager.setVillage( VILLAGE );
		assertNotNull( villager );
		assertNotNull( villager.toString() );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "Liam Z", villager.getName() );
		assertTrue( villager.isTagged() );
		assertEquals( VILLAGER_TYPE, villager.getType() );
		assertEquals( VILLAGE, villager.getVillage() );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		VillagerDto villager = new VillagerDto();
		assertNotNull( villager );
		assertNotNull( villager.toString() );
		assertNull( villager.getId() );
		assertNull( villager.getName() );
		assertFalse( villager.isTagged() );
		assertNull( villager.getType() );
		assertNull( villager.getVillage() );
	}

}
