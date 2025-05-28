package mb.minecraft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mb.minecraft.dao.VillageDao;
import mb.minecraft.dto.VillageDto;
import mb.minecraft.mapper.VillageMapper;
import mb.minecraft.model.Village;
import mb.minecraft.service.VillageService;

/**
 *
 * @author mikebro
 */
@Service
public class VillageServiceImpl implements VillageService {

	@Autowired
	private VillageDao villageDao;

	private VillageServiceImpl() {}


	@Override
	public VillageDto retrieveVillage( Long id ) {
		return VillageMapper.map( villageDao.selectOneById( id ) );
	}

	@Override
	public VillageDto retrieveVillage( String name ) {
		return VillageMapper.map( villageDao.selectOneByName( name ) );
	}

	@Override
	public VillageDto findOrCreateVillage( String name ) {
		VillageDto lookupVillage = this.retrieveVillage( name );
		if( lookupVillage == null ) {
			lookupVillage = VillageDto.builder()
					.name( name )
					.build();
			lookupVillage = this.createNewVillage( lookupVillage );
		}
		return lookupVillage;
	}

	@Override
	public VillageDto createNewVillage( VillageDto dto ) {
		Village village = villageDao.insertOne( Village.builder()
				.id( dto.getId() )
				.name( dto.getName() )
				.build() );
		return VillageMapper.map( village );
	}

	@Override
	public List<VillageDto> retrieveAllVillages() {
		List<Village> villages = villageDao.selectAll();
		List<VillageDto> dtoList = new ArrayList<>();
		for( Village v : villages ) {
			dtoList.add( VillageMapper.map( v ) );
		}
		return dtoList;
	}

	@Override
	public boolean removeVillage( VillageDto dto ) {
		Village village = VillageMapper.map( dto );
		return villageDao.deleteOne( village );
	}

	@Override
	public VillageDto saveVillage( VillageDto dto ) {
		Village village = VillageMapper.map( dto );
		return VillageMapper.map( villageDao.update( village ) );
	}

}
