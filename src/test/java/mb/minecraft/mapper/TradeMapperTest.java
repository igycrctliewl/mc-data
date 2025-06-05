package mb.minecraft.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.impl.TradeMapperImpl;
import mb.minecraft.model.Trade;
import mb.minecraft.service.VillagerService;


@RunWith(MockitoJUnitRunner.class)
public class TradeMapperTest {

	@InjectMocks
	TradeMapperImpl tradeMapper;

	@Mock
	VillagerService villagerService;


	@Test
	public void testMapEntityToDto() {
		when( villagerService.retrieveVillager( 701 ) ).thenReturn( VILLAGER );
		TradeDto dto = tradeMapper.map( TRADE_E );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 3, dto.getId().intValue() );
		assertEquals( "Gary", dto.getVillager().getName() );
		assertEquals( 1, dto.getTradeSeqno().intValue() );
	}

	@Test
	public void testMapBasicEntityToDto() {
		Trade trade = Trade.builder()
				.id( 3 )
				.tradeSeqno( 1 )
				.build();
		TradeDto dto = tradeMapper.map( trade );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 3, dto.getId().intValue() );
		assertEquals( 1, dto.getTradeSeqno().intValue() );
		assertNull( dto.getVillager() );
	}

	@Test
	public void testMapEntityToDtoNull() {
		Trade trade = null;
		TradeDto dto = tradeMapper.map( trade );
		assertNull( dto );
	}

	@Test
	public void testMapDtoToEntity() {
		Trade entity = tradeMapper.map( TRADE_O );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 4, entity.getId().intValue() );
		assertEquals( 701, entity.getVillagerId().intValue() );
		assertEquals( 2, entity.getTradeSeqno().intValue() );
	}

	@Test
	public void testMapBasicDtoToEntity() {
		TradeDto dto = TradeDto.builder()
				.id( 4 )
				.tradeSeqno( 2 )
				.build();
		Trade entity = tradeMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 4, entity.getId().intValue() );
		assertEquals( 2, entity.getTradeSeqno().intValue() );
		assertNull( entity.getVillagerId() );
	}

	@Test
	public void testMapDtoToEntityNull() {
		TradeDto dto = null;
		Trade entity = tradeMapper.map( dto );
		assertNull( entity );
	}


	private static final VillagerDto VILLAGER = VillagerDto.builder()
			.id( 701 )
			.name("Gary")
			.build();

	private static final Trade TRADE_E = Trade.builder()
			.id( 3 )
			.villagerId( VILLAGER.getId() )
			.tradeSeqno( 1 )
			.build();

	private static final TradeDto TRADE_O = TradeDto.builder()
			.id( 4 )
			.villager( VILLAGER )
			.tradeSeqno( 2 )
			.build();


}
