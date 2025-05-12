package mb.minecraft.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.VillagerTypeDao;
import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.model.VillagerType;
import mb.minecraft.service.impl.VillagerTypeServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class VillagerTypeServiceTest {

	@InjectMocks
	VillagerTypeServiceImpl villagerTypeService;

	@Mock
	VillagerTypeDao villagerTypeDao;


	@Test
	public void testRetrieveAllVillagerTypes() {
		when( villagerTypeDao.selectAll() ).thenReturn( prepareVillagerTypeList() );
		List<VillagerTypeDto> vTypes = villagerTypeService.retrieveAllVillagerTypes();
		assertNotNull( vTypes );
		assertEquals( 3, vTypes.size() );

		VillagerTypeDto v1 = vTypes.stream()
				.filter( v -> v.getId().equals( 11L ) )
				.findFirst()
				.get();
		assertEquals( 11L, v1.getId().longValue() );
		assertEquals( "Cleric", v1.getProfession() );

		VillagerTypeDto v2 = vTypes.stream()
				.filter( v -> v.getId().equals( 13L ) )
				.findFirst()
				.get();
		assertEquals( 13L, v2.getId().longValue() );
		assertEquals( "Blacksmith", v2.getProfession() );
	}

	@Test
	public void testRetrieveVillagerTypeById() {
		when( villagerTypeDao.selectOneById( any() )).thenReturn( prepareOneVillagerType() );
		VillagerTypeDto type = villagerTypeService.retrieveVillagerType( 1L );
		assertNotNull( type );
		assertEquals( 1L, type.getId().longValue() );
		assertEquals( "Leatherworker", type.getProfession() );
	}

	@Test
	public void testRetrieveVillagerTypeByProfession() {
		when( villagerTypeDao.selectOneByName( any() )).thenReturn( prepareOneVillagerType() );
		VillagerTypeDto type = villagerTypeService.retrieveVillagerType( "name" );
		assertNotNull( type );
		assertEquals( 1L, type.getId().longValue() );
		assertEquals( "Leatherworker", type.getProfession() );
	}

	@Test
	public void testFindOrCreateExisting() {
		when( villagerTypeDao.selectOneByName( any() )).thenReturn( prepareOneVillagerType() );
		VillagerTypeDto type = villagerTypeService.findOrCreateVillagerType( "name" );
		assertNotNull( type );
		assertEquals( 1L, type.getId().longValue() );
		assertEquals( "Leatherworker", type.getProfession() );
	}

	@Test
	public void testFindOrCreateNew() {
		when( villagerTypeDao.selectOneByName( any() )).thenReturn( null );
		when( villagerTypeDao.insertOne( any( VillagerType.class ) )).thenAnswer( i -> i.getArgument(0) );
		when( villagerTypeDao.getNextIdSeq() ).thenReturn( 55L );
		VillagerTypeDto newType = villagerTypeService.findOrCreateVillagerType( "Shepherd" );
		assertNotNull( newType );
		assertEquals( 55L, newType.getId().longValue() );
		assertEquals( "Shepherd", newType.getProfession() );
	}

	@Test
	public void testCreateNewVillagerType() {
		when( villagerTypeDao.insertOne( any( VillagerType.class ) )).thenAnswer( i -> i.getArgument(0) );
		when( villagerTypeDao.getNextIdSeq() ).thenReturn( 22L );
		VillagerTypeDto newVillagerType = VillagerTypeDto.builder()
				.profession( "Cartographer" )
				.build();
		VillagerTypeDto newType = villagerTypeService.createNewVillagerType( newVillagerType );
		assertNotNull( newType );
		assertEquals( 22L, newType.getId().longValue() );
		assertEquals( "Cartographer", newType.getProfession() );
	}

	@Test
	public void testDeleteVillagerTypeFail() {
		when( villagerTypeDao.deleteOne( any( VillagerType.class ) )).thenReturn( false );
		VillagerTypeDto deleteVillagerType = VillagerTypeDto.builder()
				.profession( "Nitwit" )
				.build();
		boolean villagerTypeDeleted = villagerTypeService.removeVillagerType( deleteVillagerType );
		assertFalse( villagerTypeDeleted );
	}

	@Test
	public void testDeleteVillagerTypeSuccess() {
		when( villagerTypeDao.deleteOne( any( VillagerType.class ) )).thenReturn( true );
		VillagerTypeDto deleteVillagerType = VillagerTypeDto.builder()
				.profession( "Ice Cream Man" )
				.build();
		boolean villagerTypeDeleted = villagerTypeService.removeVillagerType( deleteVillagerType );
		assertTrue( villagerTypeDeleted );
	}


	private static VillagerType prepareOneVillagerType() {
		VillagerType type = buildVillagerType( 1L, "Leatherworker" );
		return type;
	}

	private static List<VillagerType> prepareVillagerTypeList() {
		List<VillagerType> types = Arrays.asList(
				buildVillagerType( 11L, "Cleric" ),
				buildVillagerType( 12L, "Farmer" ),
				buildVillagerType( 13L, "Blacksmith" ) );
		return types;
	}

	private static VillagerType buildVillagerType( Long id, String profession ) {
		return VillagerType.builder()
				.id( id )
				.profession( profession )
				.build();
	}

}
