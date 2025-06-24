package mb.minecraft.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mb.minecraft.dao.VillagerDao;
import mb.minecraft.dto.VillageDto;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.VillageMapper;
import mb.minecraft.mapper.VillagerMapper;
import mb.minecraft.model.Villager;
import mb.minecraft.service.VillagerService;

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
	public VillagerDto retrieveVillager( int id ) {
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
		Villager villager = villagerDao.insertOne( villagerMapper.map( dto ) );
		return villagerMapper.map( villager );
	}

	@Override
	public List<VillagerDto> retrieveAllVillagers() {
		List<Villager> villagers = villagerDao.selectAll();
		List<VillagerDto> dtoList = villagers.stream()
				.map( v -> villagerMapper.map( v ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public List<VillagerDto> retrieveAllVillagers( VillageDto village ) {
		List<Villager> villagers = villagerDao.selectAll( VillageMapper.map( village ) );
		List<VillagerDto> dtoList = villagers.stream()
				.map( v -> villagerMapper.map( v ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public boolean removeVillager( VillagerDto dto ) {
		Villager villager = villagerMapper.map( dto );
		return villagerDao.deleteOne( villager );
	}

	@Override
	public VillagerDto saveVillager( VillagerDto dto ) {
		Villager villager = villagerMapper.map( dto );
		return villagerMapper.map( villagerDao.update( villager ) );
	}

}
