package mb.minecraft.mapper.impl;

import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.VillagerMapper;
import mb.minecraft.model.Villager;
import mb.minecraft.service.VillageService;
import mb.minecraft.service.VillagerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * VillagerMapper manages transformations between the Village entity and the VillageDto.
 * Part of entity redesign.
 * @author mikebro
 */
@Component
public class VillagerMapperImpl implements VillagerMapper {

	@Autowired
	private VillageService villageService;

	@Autowired
	private VillagerTypeService villagerTypeService;

	@Override
	public Villager map( VillagerDto dto ) {
		if( dto == null ) {
			return null;
		}
		Villager v = Villager.builder()
				.id( dto.getId() )
				.name( dto.getName() )
				.tagged( dto.isTagged() )
				.build();
		if( dto.getVillage() != null ) {
			v.setVillageId( dto.getVillage().getId() );
		}
		if( dto.getType() != null ) {
			v.setTypeId( dto.getType().getId() );
		}
		return v;
	}

	@Override
	public VillagerDto map( Villager villager ) {
		if( villager == null ) {
			return null;
		}
		VillagerDto dto = VillagerDto.builder()
				.id( villager.getId() )
				.name( villager.getName() )
				.tagged( villager.isTagged() )
				.build();
		if( villager.getVillageId() != null ) {
			dto.setVillage( villageService.retrieveVillage( villager.getVillageId() ) );
		}
		if( villager.getVillageId() != null ) {
			dto.setType( villagerTypeService.retrieveVillagerType( villager.getTypeId() ) );
		}
		return dto;
	}

}
