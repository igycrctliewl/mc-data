package mb.minecraft.mapper;

import org.springframework.stereotype.Component;

import mb.minecraft.dto.VillagerDto;
import mb.minecraft.model.Villager;

/**
 * VillagerMapper manages transformations between the Village entity and the VillageDto.
 * Part of entity redesign.
 * @author mikebro
 */
@Component
public interface VillagerMapper {

	public Villager map( VillagerDto dto );
	public VillagerDto map( Villager villager );

}
