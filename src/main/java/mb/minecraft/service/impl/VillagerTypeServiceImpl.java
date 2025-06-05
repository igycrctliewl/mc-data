package mb.minecraft.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mb.minecraft.dao.VillagerTypeDao;
import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.mapper.VillagerTypeMapper;
import mb.minecraft.model.VillagerType;
import mb.minecraft.service.VillagerTypeService;

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
	public VillagerTypeDto retrieveVillagerType( int id ) {
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
	/* It occurs to me that this should be a private method.
	 * Find-or-create allows the caller to create new types, but prevents the
	 * caller from creating duplicate types.  This method opens the possibility
	 * of creating duplicate types.
	 * 
	 * Nope, I'm overruling that earlier concern.  The responsiblity for preventing
	 * duplicate villager types (duplicate professions) lies with the Dao implementation.
	 * This method is fine.
	 */
	public VillagerTypeDto createNewVillagerType( VillagerTypeDto villagerType ) {
		VillagerType type = villagerTypeDao.insertOne( VillagerTypeMapper.map( villagerType ) );
		return VillagerTypeMapper.map( type );
	}

	@Override
	public List<VillagerTypeDto> retrieveAllVillagerTypes() {
		List<VillagerTypeDto> list = villagerTypeDao.selectAll().stream()
				.map( t -> VillagerTypeMapper.map( t ) )
				.collect( Collectors.toList() );
		return list;
	}

	@Override
	public boolean removeVillagerType( VillagerTypeDto villagerType ) {
		return villagerTypeDao.deleteOne( VillagerTypeMapper.map( villagerType ) );
	}

}
