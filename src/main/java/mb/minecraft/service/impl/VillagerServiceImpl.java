package mb.minecraft.service.impl;

import java.util.ArrayList;
import java.util.List;
import mb.minecraft.dao.VillagerDao;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.VillagerMapper;
import mb.minecraft.model.Villager;
import mb.minecraft.service.VillagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mikebro
 */
@Service
public class VillagerServiceImpl implements VillagerService {

	@Autowired
	private VillagerDao villagerDao;

	@Autowired
	private VillagerMapper villagerMapper;

	private VillagerServiceImpl() {}



	@Override
	public VillagerDto retrieveVillager( Long id ) {
		return villagerMapper.map( villagerDao.selectOneById( id ) );
	}

	@Override
	public VillagerDto retrieveVillager( String name ) {
		return villagerMapper.map( villagerDao.selectOneByName( name ) );
	}

	@Override
	public VillagerDto findOrCreateVillager( String name ) {
		VillagerDto lookupVillager = this.retrieveVillager( name );
		if( lookupVillager == null ) {
			lookupVillager = VillagerDto.builder()
					  .name( name )
					  .build();
			lookupVillager = this.createNewVillager( lookupVillager );
		}
		return lookupVillager;
	}

	@Override
	public VillagerDto createNewVillager( VillagerDto dto ) {
		dto.setId( villagerDao.getNextIdSeq() );
		Villager villager = villagerDao.insertOne( villagerMapper.map( dto ) );
		return villagerMapper.map( villager );
	}

	@Override
	public List<VillagerDto> retrieveAllVillagers() {
		List<Villager> villagers = villagerDao.selectAll();
		List<VillagerDto> dtoList = new ArrayList<>();
		for( Villager v : villagers ) {
			dtoList.add( villagerMapper.map( v ) );
		}
		return dtoList;
	}

	@Override
	public Boolean removeVillager( VillagerDto dto ) {
		Villager villager = villagerMapper.map( dto );
		return villagerDao.deleteOne( villager );
	}

	@Override
	public VillagerDto updateVillager( VillagerDto villager ) {
		// TODO: implement new service method
		throw new UnsupportedOperationException( "Not supported yet." );
	}

}
