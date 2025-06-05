package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.VillagerTypeDto;

/**
 *
 * @author mikebro
 */
public interface VillagerTypeService {

	public VillagerTypeDto retrieveVillagerType( int id );
	public VillagerTypeDto retrieveVillagerType( String profession );
	public VillagerTypeDto findOrCreateVillagerType( String profession );
	public VillagerTypeDto createNewVillagerType( VillagerTypeDto villagerType );
	public List<VillagerTypeDto> retrieveAllVillagerTypes();
	public boolean removeVillagerType( VillagerTypeDto villagerType );
}
