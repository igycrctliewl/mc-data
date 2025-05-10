package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.VillagerDto;

/**
 *
 * @author mikebro
 */
public interface VillagerService {

	public VillagerDto retrieveVillager( Long id );
	public VillagerDto retrieveVillager( String name );
	public VillagerDto findOrCreateVillager( String name );
	public VillagerDto createNewVillager( VillagerDto villager );
	public List<VillagerDto> retrieveAllVillagers();
	public VillagerDto updateVillager( VillagerDto villager );
	public Boolean removeVillager( VillagerDto villager );
}
