package mb.minecraft.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.model.VillagerType;


@RunWith(MockitoJUnitRunner.class)
public class VillagerTypeMapperTest {

	@Test
	public void testMapEntityToDto() {
		VillagerTypeDto dto = VillagerTypeMapper.map( prepareVillagerTypeEntity() );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 101L, dto.getId().longValue() );
		assertEquals( "Farmer", dto.getProfession() );
	}

	@Test
	public void testMapEntityToDtoNull() {
		VillagerType entity = null;
		VillagerTypeDto dto = VillagerTypeMapper.map( entity );
		assertNull( dto );
	}

	@Test
	public void testMapDtoToEntity() {
		VillagerTypeDto dto = prepareVillagerTypeDto();
		VillagerType entity = VillagerTypeMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 101L, entity.getId().longValue() );
		assertEquals( "Farmer", entity.getProfession() );
	}

	@Test
	public void testMapDtoToEntityNull() {
		VillagerTypeDto dto = null;
		VillagerType entity = VillagerTypeMapper.map( dto );
		assertNull( entity );
	}



	private static VillagerType prepareVillagerTypeEntity() {
		return VillagerType.builder()
				.id( 101L )
				.profession( "Farmer" )
				.build();
	}

	private static VillagerTypeDto prepareVillagerTypeDto() {
		return VillagerTypeDto.builder()
				.id( 101L )
				.profession( "Farmer" )
				.build();
	}

}
