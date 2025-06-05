package mb.minecraft.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.TradeDao;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.TradeMapper;
import mb.minecraft.mapper.VillagerMapper;
import mb.minecraft.model.Trade;
import mb.minecraft.model.Villager;
import mb.minecraft.service.impl.TradeServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

	@InjectMocks
	TradeServiceImpl tradeService;

	@Mock
	private TradeDao tradeDao;

	@Mock
	private TradeMapper tradeMapper;

	@Mock
	private VillagerMapper villagerMapper;

	@Before
	public void init() {
		when( tradeMapper.map( any( Trade.class ) ) ).thenAnswer( i -> mapTrade( i.getArgument(0) ) );
	}


	@Test
	public void testRetrieveTrade() {
		when( tradeDao.selectOneById( 13 )).thenReturn( TRADE_E1 );
		TradeDto tradeDto = tradeService.retrieveTrade( 13 );
		assertNotNull( tradeDto );
		assertNotNull( tradeDto.toString() );
		assertEquals( 13, tradeDto.getId().intValue() );
		assertEquals( 705, tradeDto.getVillager().getId().intValue() );
		assertEquals( 1, tradeDto.getTradeSeqno().intValue() );
	}

	@Test
	public void testCreateNewTrade() {
		AtomicInteger idGen = new AtomicInteger( 16 );
		when( tradeDao.insert( anyList() )).thenAnswer( i -> {
			List<Trade> ts = i.getArgument(0);
			List<Trade> result = ts.stream()
					.map( t -> {
						t.setId( idGen.incrementAndGet() );
						return t;
					})
					.collect( Collectors.toList() );
			return result;
		});
		when( tradeMapper.map( any( TradeDto.class ) ) ).thenAnswer( i -> mapTradeDto( i.getArgument(0) ) );
		List<TradeDto> newTrades = tradeService.createNewTrades( prepareTrades() );
		assertNotNull( newTrades );
		assertEquals( 3, newTrades.size() );
		assertEquals( 17, newTrades.get(0).getId().intValue() );
		assertEquals( 18, newTrades.get(1).getId().intValue() );
		assertEquals( 19, newTrades.get(2).getId().intValue() );
	}

	@Test
	public void testRetrieveAllTrades() {
		when( tradeDao.selectAll() ).thenReturn( prepareAllTrades() );
		List<TradeDto> trades = tradeService.retrieveAllTrades();
		assertNotNull( trades );
		assertEquals( 3, trades.size() );

		TradeDto t1 = trades.stream()
				.filter( t -> t.getId().equals( 13 ) )
				.findFirst()
				.get();
		assertEquals( 13, t1.getId().intValue() );
		assertEquals( 705, t1.getVillager().getId().intValue() );
		assertEquals( 1, t1.getTradeSeqno().intValue() );

		TradeDto t2 = trades.stream()
				.filter( t -> t.getId().equals( 15 ) )
				.findFirst()
				.get();
		assertEquals( 15, t2.getId().intValue() );
		assertEquals( 705, t2.getVillager().getId().intValue() );
		assertEquals( 3, t2.getTradeSeqno().intValue() );
	}

	@Test
	public void testRetrieveAllVillagerTrades() {
		when( tradeDao.selectAll( any( Villager.class) ) ).thenReturn( prepareAllTrades() );
		when( villagerMapper.map( any( VillagerDto.class ) ) ).thenAnswer( i -> mapVillagerDto( i.getArgument(0) ) );
		VillagerDto villager = VillagerDto.builder().id( 705 ).build();
		List<TradeDto> trades = tradeService.retrieveAllTrades( villager );
		assertNotNull( trades );
		assertEquals( 3, trades.size() );

		TradeDto t1 = trades.stream()
				.filter( t -> t.getId().equals( 13 ) )
				.findFirst()
				.get();
		assertEquals( 13, t1.getId().intValue() );
		assertEquals( 705, t1.getVillager().getId().intValue() );
		assertEquals( 1, t1.getTradeSeqno().intValue() );

		TradeDto t2 = trades.stream()
				.filter( t -> t.getId().equals( 15 ) )
				.findFirst()
				.get();
		assertEquals( 15, t2.getId().intValue() );
		assertEquals( 705, t2.getVillager().getId().intValue() );
		assertEquals( 3, t2.getTradeSeqno().intValue() );
	}

	@Test
	public void testUpdateTrade() {
		when( tradeDao.update( any( Trade.class) ) ).thenAnswer( i -> i.getArgument(0) );
		when( tradeMapper.map( any( TradeDto.class ) ) ).thenAnswer( i -> mapTradeDto( i.getArgument(0) ) );
		TradeDto updateTrade = TradeDto.builder()
				.id( 15 )
				.villager( VillagerDto.builder().id( 705 ).build() )
				.tradeSeqno( 4 )
				.build();
		TradeDto updatedTrade = tradeService.saveTrade( updateTrade );
		assertFalse( updateTrade == updatedTrade );
		assertEquals( 4, updatedTrade.getTradeSeqno().intValue() );
	}

	@Test
	public void testDeleteTrade() {
		when( tradeDao.deleteOne( any( Trade.class ) )).thenReturn( true );
		when( tradeMapper.map( any( TradeDto.class ) ) ).thenAnswer( i -> mapTradeDto( i.getArgument(0) ) );
		TradeDto deleteTrade = TradeDto.builder()
				.id( 15 )
				.villager( VillagerDto.builder().id( 705 ).build() )
				.tradeSeqno( 3 )
				.build();
		boolean tradeDeleted = tradeService.removeTrade( deleteTrade );
		assertTrue( tradeDeleted );
	}



	private static final Trade TRADE_E1 = Trade.builder()
			.id( 13 )
			.villagerId( 705 )
			.tradeSeqno( 1 )
			.build();

	private static List<Trade> prepareAllTrades() {
		List<Trade> trades = new ArrayList<>();
		trades.add( TRADE_E1 );
		trades.add( Trade.builder()
				.id( 14 )
				.villagerId( 705 )
				.tradeSeqno( 2 )
				.build() );
		trades.add( Trade.builder()
				.id( 15 )
				.villagerId( 705 )
				.tradeSeqno( 3 )
				.build() );
		return trades;
	}

	private static List<TradeDto> prepareTrades() {
		List<TradeDto> trades = new ArrayList<>();
		trades.add( TradeDto.builder()
				.villager( VillagerDto.builder().id( 706 ).build() )
				.tradeSeqno( 1 )
				.build() );
		trades.add( TradeDto.builder()
				.villager( VillagerDto.builder().id( 706 ).build() )
				.tradeSeqno( 2 )
				.build() );
		trades.add( TradeDto.builder()
				.villager( VillagerDto.builder().id( 706 ).build() )
				.tradeSeqno( 3 )
				.build() );
		return trades;
	}

	private static Villager mapVillagerDto( VillagerDto v ) {
		return Villager.builder()
				.id( v.getId() )
				.name( v.getName() )
				.tagged( v.isTagged() )
				.build();
	}

	private static TradeDto mapTrade( Trade t ) {
		return TradeDto.builder()
				.id( t.getId() )
				.villager( VillagerDto.builder().id( t.getVillagerId() ).build() )
				.tradeSeqno( t.getTradeSeqno() )
				.build();
	}

	private static Trade mapTradeDto( TradeDto dto ) {
		return Trade.builder()
				.id( dto.getId() )
				.villagerId( dto.getVillager().getId() )
				.tradeSeqno( dto.getTradeSeqno() )
				.build();
	}

}
