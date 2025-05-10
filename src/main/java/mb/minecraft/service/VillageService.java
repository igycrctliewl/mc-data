package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.VillageDto;

/**
 * There is no update method.  This should be immutable like VillagerType
 * @author mikebro
 */
public interface VillageService {

	public VillageDto retrieveVillage( Long id );
	public VillageDto retrieveVillage( String name );
	public VillageDto findOrCreateVillage( String name );
	public VillageDto createNewVillage( VillageDto village );
	public List<VillageDto> retrieveAllVillages();
	public boolean removeVillage( VillageDto village );
}
