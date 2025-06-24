package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.VillageDto;
import mb.minecraft.dto.VillagerDto;

/**
 *
 * @author mikebro
 */
public interface VillagerService {

	public VillagerDto retrieveVillager( int id );
	public VillagerDto retrieveVillager( String name );
	public VillagerDto findOrCreateVillager( String name );
	public VillagerDto createNewVillager( VillagerDto villager );
	public List<VillagerDto> retrieveAllVillagers();
	public List<VillagerDto> retrieveAllVillagers( VillageDto village );
	public VillagerDto saveVillager( VillagerDto villager );
	public boolean removeVillager( VillagerDto villager );
}
