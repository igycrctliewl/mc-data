package mb.minecraft.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dto.ItemDto;
import mb.minecraft.model.Item;


@RunWith(MockitoJUnitRunner.class)
public class ItemMapperTest {

	@Test
	public void testMapEntityToDto() {
		ItemDto dto = ItemMapper.map( prepareItemEntity() );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 101L, dto.getId().longValue() );
		assertEquals( "Emerald", dto.getName() );
		assertEquals( "https://minecraft.wiki/images/Emerald_JE3_BE3.png", dto.getImageSource() );
		assertNotNull( dto.getImage() );
	}

	@Test
	public void testMapEntityToDtoNull() {
		Item entity = null;
		ItemDto dto = ItemMapper.map( entity );
		assertNull( dto );
	}

	@Test
	public void testMapDtoToEntity() {
		ItemDto dto = prepareItemDto();
		Item entity = ItemMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 101L, entity.getId().longValue() );
		assertEquals( "Emerald", entity.getName() );
		assertEquals( "https://minecraft.wiki/images/Emerald_JE3_BE3.png", entity.getImageSource() );
	}

	@Test
	public void testMapDtoToEntityNull() {
		ItemDto dto = null;
		Item entity = ItemMapper.map( dto );
		assertNull( entity );
	}



	private static Item prepareItemEntity() {
		return Item.builder()
				.id( 101L )
				.name( "Emerald" )
				.imageSource( "https://minecraft.wiki/images/Emerald_JE3_BE3.png" )
				.build();
	}

	private static ItemDto prepareItemDto() {
		return ItemDto.builder()
				.id( 101L )
				.name("Emerald")
				.imageSource( "https://minecraft.wiki/images/Emerald_JE3_BE3.png" )
				.build();
	}

}
