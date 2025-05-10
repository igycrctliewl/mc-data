package mb.minecraft.mapper;

import mb.minecraft.dto.VillageDto;
import mb.minecraft.model.Village;

/**
 * VillageMapper manages transformations between the Village entity and the VillageDto.
 * Part of entity redesign.
 * @author mikebro
 */
public class VillageMapper {

	public static Village map( VillageDto dto ) {
		if( dto == null ) {
			return null;
		}
		return Village.builder()
				.id( dto.getId() )
				.name( dto.getName() )
				.build();
	}

	public static VillageDto map( Village item ) {
		if( item == null ) {
			return null;
		}
		return VillageDto.builder()
				.id( item.getId() )
				.name( item.getName() )
				.build();
	}
}
