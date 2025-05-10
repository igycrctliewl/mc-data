package mb.minecraft.service.impl;

import java.util.ArrayList;
import java.util.List;
import mb.minecraft.dao.VillagerTypeDao;
import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.mapper.VillagerTypeMapper;
import mb.minecraft.model.VillagerType;
import mb.minecraft.service.VillagerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mikebro
 */
@Service
public class VillagerTypeServiceImpl implements VillagerTypeService {

	@Autowired
	private VillagerTypeDao villagerTypeDao;

	private VillagerTypeServiceImpl() {}


	@Override
	public VillagerTypeDto retrieveVillagerType( Long id ) {
		return VillagerTypeMapper.map( villagerTypeDao.selectOneById( id ) );
	}

	@Override
	public VillagerTypeDto retrieveVillagerType( String profession ) {
		return VillagerTypeMapper.map( villagerTypeDao.selectOneByName( profession ) );
	}

	@Override
	public VillagerTypeDto findOrCreateVillagerType( String profession ) {
		VillagerTypeDto lookupType = this.retrieveVillagerType( profession );
		if( lookupType == null ) {
			lookupType = VillagerTypeDto.builder()
					  .profession( profession )
					  .build();
			lookupType = this.createNewVillagerType( lookupType );
		}
		return lookupType;
	}

	@Override
	public VillagerTypeDto createNewVillagerType( VillagerTypeDto villagerType ) {
		VillagerType type = villagerTypeDao.insertOne( VillagerType.builder()
				  .id( villagerTypeDao.getNextIdSeq() )
				  .profession( villagerType.getProfession() )
				  .build() );
		return VillagerTypeMapper.map( type );
	}

	@Override
	public List<VillagerTypeDto> retrieveAllVillagerTypes() {
		List<VillagerTypeDto> list = new ArrayList<>();
		for( VillagerType t : villagerTypeDao.selectAll() ) {
			list.add( VillagerTypeMapper.map( t ) );
		}
		return list;
	}

	@Override
	public Boolean removeVillagerType( VillagerTypeDto villagerType ) {
		return villagerTypeDao.deleteOne( VillagerTypeMapper.map( villagerType ) );
	}

}
