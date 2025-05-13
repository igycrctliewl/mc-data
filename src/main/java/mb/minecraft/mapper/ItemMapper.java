package mb.minecraft.mapper;

import mb.minecraft.dto.ItemDto;
import mb.minecraft.model.Item;

/**
 * ItemMapper manages transformations between the Item entity and the ItemDto.
 * Part of entity redesign.
 * @author mikebro
 */
public class ItemMapper {

	public static Item map( ItemDto dto ) {
		if( dto == null ) {
			return null;
		}
		return Item.builder()
				.id( dto.getId() )
				.name( dto.getName() )
				.imageSource( dto.getImageSource() )
				.build();
	}

	public static ItemDto map( Item item ) {
		if( item == null ) {
			return null;
		}
		return ItemDto.builder()
				.id( item.getId() )
				.name( item.getName() )
				.imageSource( item.getImageSource() )
				.build();
	}

}
