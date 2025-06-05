package mb.minecraft.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dto.VillageDto;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.mapper.impl.VillagerMapperImpl;
import mb.minecraft.model.Villager;
import mb.minecraft.service.VillageService;
import mb.minecraft.service.VillagerTypeService;


@RunWith(MockitoJUnitRunner.class)
public class VillagerMapperTest {

	@InjectMocks
	VillagerMapperImpl villagerMapper;

	@Mock
	VillageService villageService;

	@Mock
	VillagerTypeService villagerTypeService;


	@Test
	public void testMapEntityToDto() {
		when( villageService.retrieveVillage( 1 ) ).thenReturn( prepareVillageDto() );
		when( villagerTypeService.retrieveVillagerType( 2 ) ).thenReturn( prepareVillagerTypeDto() );
		VillagerDto dto = villagerMapper.map( prepareVillagerEntity() );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 101, dto.getId().intValue() );
		assertEquals( "Gary", dto.getName() );
		assertFalse( dto.isTagged() );
		assertEquals( "San Mateo", dto.getVillage().getName() );
		assertEquals( "Farmer", dto.getType().getProfession() );
	}

	@Test
	public void testMapBasicEntityToDto() {
		Villager v = Villager.builder()
				.id( 101 )
				.name( "Gary" )
				.build();
		VillagerDto dto = villagerMapper.map( v );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 101, dto.getId().intValue() );
		assertEquals( "Gary", dto.getName() );
		assertFalse( dto.isTagged() );
		assertNull( dto.getVillage() );
		assertNull( dto.getType() );
	}

	@Test
	public void testMapEntityToDtoNull() {
		Villager v = null;
		VillagerDto dto = villagerMapper.map( v );
		assertNull( dto );
	}

	@Test
	public void testMapDtoToEntity() {
		VillagerDto dto = prepareVillagerDto();
		Villager entity = villagerMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 101, entity.getId().intValue() );
		assertEquals( "Gary", entity.getName() );
		assertFalse( entity.isTagged() );
		assertEquals( 1, entity.getVillageId().intValue() );
		assertEquals( 2, entity.getTypeId().intValue() );
	}

	@Test
	public void testMapBasicDtoToEntity() {
		VillagerDto dto = VillagerDto.builder()
				.id( 101 )
				.name("Gary")
				.build();
		Villager entity = villagerMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 101, entity.getId().intValue() );
		assertEquals( "Gary", entity.getName() );
		assertFalse( entity.isTagged() );
		assertNull( entity.getVillageId() );
		assertNull( entity.getTypeId() );
	}

	@Test
	public void testMapDtoToEntityNull() {
		VillagerDto dto = null;
		Villager entity = villagerMapper.map( dto );
		assertNull( entity );
	}



	private static Villager prepareVillagerEntity() {
		return Villager.builder()
				.id( 101 )
				.name("Gary")
				.villageId( 1 )
				.typeId( 2 )
				.build();
	}

	private static VillagerDto prepareVillagerDto() {
		return VillagerDto.builder()
				.id( 101 )
				.name("Gary")
				.village( prepareVillageDto() )
				.type( prepareVillagerTypeDto() )
				.build();
	}

	private static VillageDto prepareVillageDto() {
		return VillageDto.builder()
				.id( 1 )
				.name("San Mateo")
				.build();
	}

	private static VillagerTypeDto prepareVillagerTypeDto() {
		return VillagerTypeDto.builder()
				.id( 2 )
				.profession("Farmer")
				.build();
	}

}
