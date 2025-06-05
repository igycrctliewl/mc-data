package mb.minecraft.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.VillageDao;
import mb.minecraft.dto.VillageDto;
import mb.minecraft.model.Village;
import mb.minecraft.service.impl.VillageServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class VillageServiceTest {

	@InjectMocks
	VillageServiceImpl villageService;

	@Mock
	VillageDao villageDao;


	@Test
	public void testRetrieveVillageById() {
		when( villageDao.selectOneById( anyInt() )).thenReturn( prepareOneVillage() );
		VillageDto village = villageService.retrieveVillage( 1 );
		assertNotNull( village );
		assertEquals( 123, village.getId().intValue() );
		assertEquals( "Pacifica", village.getName() );
	}

	@Test
	public void testRetrieveVillageByName() {
		when( villageDao.selectOneByName( any() )).thenReturn( prepareOneVillage() );
		VillageDto village = villageService.retrieveVillage( "name" );
		assertNotNull( village );
		assertEquals( 123, village.getId().intValue() );
		assertEquals( "Pacifica", village.getName() );
	}

	@Test
	public void testRetrieveAllVillages() {
		when( villageDao.selectAll() ).thenReturn( prepareVillageList() );
		List<VillageDto> villages = villageService.retrieveAllVillages();
		assertNotNull( villages );
		assertEquals( 3, villages.size() );

		VillageDto v1 = villages.stream()
				.filter( v -> v.getName().equals( "Pacifica" ) )
				.findFirst()
				.get();
		assertEquals( 123, v1.getId().intValue() );
		assertEquals( "Pacifica", v1.getName() );

		VillageDto v2 = villages.stream()
				.filter( v -> v.getName().equals( "Deep Water Cove" ) )
				.findFirst()
				.get();
		assertEquals( 234, v2.getId().intValue() );
		assertEquals( "Deep Water Cove", v2.getName() );
	}

	@Test
	public void testFindOrCreateExisting() {
		when( villageDao.selectOneByName( any() )).thenReturn( prepareOneVillage() );
		VillageDto village = villageService.findOrCreateVillage( "name" );
		assertNotNull( village );
		assertEquals( 123, village.getId().intValue() );
		assertEquals( "Pacifica", village.getName() );
	}

	@Test
	public void testFindOrCreateNew() {
		when( villageDao.selectOneByName( any() )).thenReturn( null );
		when( villageDao.insertOne( any( Village.class ) )).thenAnswer( i -> {
			Village v = i.getArgument(0);
			v.setId( 555 );
			return v;
		});
		VillageDto village = villageService.findOrCreateVillage( "Northern Outpost" );
		assertNotNull( village );
		assertEquals( 555, village.getId().intValue() );
		assertEquals( "Northern Outpost", village.getName() );
	}

	@Test
	public void testCreateNewVillage() {
		when( villageDao.insertOne( any( Village.class ) )).thenAnswer( i -> {
			Village v = i.getArgument(0);
			v.setId( 415 );
			return v;
		});
		VillageDto newVillage = VillageDto.builder()
				.name( "San Francisco" )
				.build();
		VillageDto village = villageService.createNewVillage( newVillage );
		assertNotNull( village );
		assertEquals( 415, village.getId().intValue() );
		assertEquals( "San Francisco", village.getName() );
	}

	@Test
	public void testDeleteVillageFail() {
		when( villageDao.deleteOne( any( Village.class ) )).thenReturn( false );
		VillageDto newVillage = VillageDto.builder()
				.name( "San Francisco" )
				.build();
		boolean villageDeleted = villageService.removeVillage( newVillage );
		assertFalse( villageDeleted );
	}

	@Test
	public void testDeleteVillageSuccess() {
		when( villageDao.deleteOne( any( Village.class ) )).thenReturn( true );
		VillageDto newVillage = VillageDto.builder()
				.name( "San Francisco" )
				.build();
		boolean villageDeleted = villageService.removeVillage( newVillage );
		assertTrue( villageDeleted );
	}



	private static Village prepareOneVillage() {
		Village v = buildVillage( 123, "Pacifica" );
		return v;
	}
	
	private static List<Village> prepareVillageList() {
		List<Village> v = Arrays.asList(
				buildVillage( 12, "Southland" ),
				buildVillage( 123, "Pacifica" ),
				buildVillage( 234, "Deep Water Cove" )
		);
		return v;
	}

	private static Village buildVillage( int id, String name ) {
		return Village.builder()
				.id( id )
				.name( name )
				.build();
	}
}
