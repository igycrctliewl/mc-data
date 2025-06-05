package mb.minecraft.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dto.VillageDto;
import mb.minecraft.model.Village;


@RunWith(MockitoJUnitRunner.class)
public class VillageMapperTest {

	@Test
	public void testMapEntityToDto() {
		VillageDto dto = VillageMapper.map( prepareVillageEntity() );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 101, dto.getId().intValue() );
		assertEquals( "Pacifica", dto.getName() );
	}

	@Test
	public void testMapEntityToDtoNull() {
		Village entity = null;
		VillageDto dto = VillageMapper.map( entity );
		assertNull( dto );
	}

	@Test
	public void testMapDtoToEntity() {
		VillageDto dto = prepareVillageDto();
		Village entity = VillageMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 101, entity.getId().intValue() );
		assertEquals( "Pacifica", entity.getName() );
	}

	@Test
	public void testMapDtoToEntityNull() {
		VillageDto dto = null;
		Village entity = VillageMapper.map( dto );
		assertNull( entity );
	}



	private static Village prepareVillageEntity() {
		return Village.builder()
				.id( 101 )
				.name( "Pacifica" )
				.build();
	}

	private static VillageDto prepareVillageDto() {
		return VillageDto.builder()
				.id( 101 )
				.name( "Pacifica" )
				.build();
	}

}
