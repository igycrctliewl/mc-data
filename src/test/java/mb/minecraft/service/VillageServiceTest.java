package mb.minecraft.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
	public void testRetrieveVillage() {
		when( villageDao.selectOneById( any() )).thenReturn( prepareVillage() );
		VillageDto village = villageService.retrieveVillage( 1L );
		assertNotNull( village );
		assertEquals( 123L, village.getId().longValue() );
		assertEquals( "Pacifica", village.getName() );
	}



	private Village prepareVillage() {
		Village v = Village.builder()
				.id( 123L )
				.name( "Pacifica" )
				.build();
		return v;
	}
}
