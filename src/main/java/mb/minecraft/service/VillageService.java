package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.VillageDto;

/**
 * @author mikebro
 */
public interface VillageService {

	public VillageDto retrieveVillage( int id );
	public VillageDto retrieveVillage( String name );
	public VillageDto findOrCreateVillage( String name );
	public VillageDto createNewVillage( VillageDto village );
	public List<VillageDto> retrieveAllVillages();
	public VillageDto saveVillage( VillageDto village );
	public boolean removeVillage( VillageDto village );
}
