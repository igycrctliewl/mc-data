package mb.minecraft.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.VillagerDao;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.VillagerMapper;
import mb.minecraft.model.Villager;
import mb.minecraft.service.impl.VillagerServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class VillagerServiceTest {

	@InjectMocks
	VillagerServiceImpl villagerService;

	@Mock
	VillagerDao villagerDao;

	@Mock
	VillagerMapper villagerMapper;

	@Before
	public void init() {
		when( villagerMapper.map( any( Villager.class ) ) ).thenAnswer( i -> mapVillager( i.getArgument(0) ) );
	}


	@Test
	public void testRetrieveVillagerById() {
		when( villagerDao.selectOneById( any() )).thenReturn( prepareOneVillager() );
		VillagerDto villager = villagerService.retrieveVillager( 1L );
		assertNotNull( villager );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "MikeBro", villager.getName() );
		assertFalse( villager.isTagged() );
		assertNull( villager.getType() );
		assertNull( villager.getVillage() );
	}

	@Test
	public void testRetrieveVillagerByName() {
		when( villagerDao.selectOneByName( any() )).thenReturn( prepareOneVillager() );
		VillagerDto villager = villagerService.retrieveVillager( "name" );
		assertNotNull( villager );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "MikeBro", villager.getName() );
		assertFalse( villager.isTagged() );
		assertNull( villager.getType() );
		assertNull( villager.getVillage() );
	}

	@Test
	public void testRetrieveAllVillagers() {
		when( villagerDao.selectAll() ).thenReturn( prepareVillagerList() );
		List<VillagerDto> villagers = villagerService.retrieveAllVillagers();
		assertNotNull( villagers );
		assertEquals( 3, villagers.size() );

		VillagerDto v1 = villagers.stream()
				.filter( v -> v.getName().equals( "MikeBro" ) )
				.findFirst()
				.get();
		assertEquals( 101L, v1.getId().longValue() );
		assertEquals( "MikeBro", v1.getName() );
		assertFalse( v1.isTagged() );
		assertNull( v1.getType() );
		assertNull( v1.getVillage() );

		VillagerDto v2 = villagers.stream()
				.filter( v -> v.getName().equals( "Liam Z" ) )
				.findFirst()
				.get();
		assertEquals( 103L, v2.getId().longValue() );
		assertEquals( "Liam Z", v2.getName() );
		assertTrue( v2.isTagged() );
		assertNull( v2.getType() );
		assertNull( v2.getVillage() );
	}

	@Test
	public void testFindOrCreateExisting() {
		when( villagerDao.selectOneByName( any() )).thenReturn( prepareOneVillager() );
		VillagerDto villager = villagerService.findOrCreateVillager( "name" );
		assertNotNull( villager );
		assertEquals( 101L, villager.getId().longValue() );
		assertEquals( "MikeBro", villager.getName() );
		assertFalse( villager.isTagged() );
		assertNull( villager.getType() );
		assertNull( villager.getVillage() );
	}

	@Test
	public void testFindOrCreateNew() {
		when( villagerDao.selectOneByName( any() )).thenReturn( null );
		when( villagerMapper.map( isNull( Villager.class ) ) ).thenReturn( null );
		when( villagerMapper.map( any( VillagerDto.class ) ) ).thenAnswer( i -> mapVillagerDto( i.getArgument(0) ) );
		when( villagerDao.insertOne( any( Villager.class ) )).thenAnswer( i -> i.getArgument(0) );
		when( villagerDao.getNextIdSeq() ).thenReturn( 555L );
		VillagerDto newVillager = villagerService.findOrCreateVillager( "Sparky" );
		assertNotNull( newVillager );
		assertEquals( 555L, newVillager.getId().longValue() );
		assertEquals( "Sparky", newVillager.getName() );
	}

	@Test
	public void testCreateNewVillager() {
		when( villagerDao.insertOne( any( Villager.class ) )).thenAnswer( i -> i.getArgument(0) );
		when( villagerMapper.map( any( VillagerDto.class ) ) ).thenAnswer( i -> mapVillagerDto( i.getArgument(0) ) );
		when( villagerDao.getNextIdSeq() ).thenReturn( 212L );
		VillagerDto newVillager = VillagerDto.builder()
				.name( "Shelia" )
				.tagged( true )
				.build();
		VillagerDto villager = villagerService.createNewVillager( newVillager );
		assertNotNull( villager );
		assertEquals( 212L, villager.getId().longValue() );
		assertEquals( "Shelia", villager.getName() );
		assertTrue( villager.isTagged() );
		assertNull( villager.getType() );
		assertNull( villager.getVillage() );
	}

	@Test
	public void testDeleteVillagerFail() {
		when( villagerDao.deleteOne( any( Villager.class ) )).thenReturn( false );
		when( villagerMapper.map( any( VillagerDto.class ) ) ).thenAnswer( i -> mapVillagerDto( i.getArgument(0) ) );
		VillagerDto deleteVillager = VillagerDto.builder()
				.name( "Malcolm" )
				.build();
		boolean villagerDeleted = villagerService.removeVillager( deleteVillager );
		assertFalse( villagerDeleted );
	}

	@Test
	public void testDeleteVillagerSuccess() {
		when( villagerDao.deleteOne( any( Villager.class ) )).thenReturn( true );
		when( villagerMapper.map( any( VillagerDto.class ) ) ).thenAnswer( i -> mapVillagerDto( i.getArgument(0) ) );
		VillagerDto deleteVillager = VillagerDto.builder()
				.name( "MikeBro" )
				.build();
		boolean villagerDeleted = villagerService.removeVillager( deleteVillager );
		assertTrue( villagerDeleted );
	}

	@Test
	public void testUpdateVillagerFail() {
		VillagerDto updateVillager = VillagerDto.builder()
				.name( "MikeBro" )
				.build();
		// updateVillager has not been implemented
		Exception ex = assertThrows( UnsupportedOperationException.class, () -> {
			villagerService.updateVillager( updateVillager );
		});
		assertEquals( "VillagerService#updateVillager is not supported in this version.", ex.getMessage() );
	}



	private static Villager prepareOneVillager() {
		Villager v = buildVillager( 101L, "MikeBro" );
		return v;
	}
	
	private static List<Villager> prepareVillagerList() {
		List<Villager> v = new ArrayList<>();
		v.add( buildVillager( 101L, "MikeBro" ) );
		v.add( buildVillager( 102L, "Steve" ) );

		Villager v3 = buildVillager( 103L, "Liam Z" );
		v3.setTagged( true );
		v.add( v3 );
		return v;
	}

	private static Villager buildVillager( Long id, String name ) {
		return Villager.builder()
				.id( id )
				.name( name )
				.tagged( false )
				.build();
	}


	private static VillagerDto mapVillager( Villager v ) {
		return VillagerDto.builder()
				.id( v.getId() )
				.name( v.getName() )
				.tagged( v.isTagged() )
				.build();
	}

	private static Villager mapVillagerDto( VillagerDto v ) {
		return Villager.builder()
				.id( v.getId() )
				.name( v.getName() )
				.tagged( v.isTagged() )
				.build();
	}
}
