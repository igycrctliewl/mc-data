package mb.minecraft.mapper;

import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.model.VillagerType;





/**
 * VillagerTypeMapper manages transformations between the VillagerType entity
 * and the VillagerTypeDto.
 * Part of entity redesign.
 * @author mikebro
 */
public class VillagerTypeMapper {

	public static VillagerType map( VillagerTypeDto dto ) {
		if( dto == null ) {
			return null;
		}
		VillagerType v = VillagerType.builder()
				  .id( dto.getId() )
				  .profession( dto.getProfession() )
				  .build();
		return v;
	}

	public static VillagerTypeDto map( VillagerType villagerType ) {
		if( villagerType == null ) {
			return null;
		}
		VillagerTypeDto dto = VillagerTypeDto.builder()
				  .id( villagerType.getId() )
				  .profession( villagerType.getProfession() )
				  .build();
		return dto;
	}
}
