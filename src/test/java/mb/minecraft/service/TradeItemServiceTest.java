package mb.minecraft.service;

import static mb.minecraft.model.OfferRequire.OFFER;
import static mb.minecraft.model.OfferRequire.REQUIRE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.TradeItemDao;
import mb.minecraft.dto.ItemDto;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.TradeItemDto;
import mb.minecraft.mapper.TradeItemMapper;
import mb.minecraft.mapper.TradeMapper;
import mb.minecraft.model.Item;
import mb.minecraft.model.OfferRequire;
import mb.minecraft.model.Trade;
import mb.minecraft.model.TradeItem;
import mb.minecraft.service.impl.TradeItemServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class TradeItemServiceTest {

	@InjectMocks
	TradeItemServiceImpl tradeItemService;

	@Mock
	private TradeItemDao tradeItemDao;

	@Mock
	private TradeMapper tradeMapper;

	@Mock
	private TradeItemMapper tradeItemMapper;


	@Before
	public void init() {
		when( tradeItemMapper.map( any( TradeItem.class ) ) ).thenAnswer( i -> mapTradeItem( i.getArgument(0) ) );
	}


	@Test
	public void testRetrieveAllTradeItems() {
		when( tradeItemDao.selectAll() ).thenReturn( prepareTradeItemList() );
		List<TradeItemDto> tradeItems = tradeItemService.retrieveAllTradeItems();
		assertNotNull( tradeItems );
		assertEquals( 7, tradeItems.size() );

		TradeItemDto ti1 = tradeItems.stream()
				.filter( i -> i.getTrade().getId().equals( 2 ) && i.getOfferRequire().equals( OFFER ) )
				.findFirst()
				.get();
		assertEquals( 1, ti1.getSeqno().intValue() );
		assertEquals( 1, ti1.getQuantity().intValue() );
		assertEquals( 1010, ti1.getItem().getId().intValue() );
		assertEquals( "Punch I", ti1.getMemo() );

		TradeItemDto ti2 = tradeItems.stream()
				.filter( i -> i.getTrade().getId().equals( 4 ) && i.getOfferRequire().equals( REQUIRE ) )
				.findFirst()
				.get();
		assertEquals( 1, ti2.getSeqno().intValue() );
		assertEquals( 11, ti2.getQuantity().intValue() );
		assertEquals( 1001, ti2.getItem().getId().intValue() );
		assertNull( ti2.getMemo() );
	}

	@Test
	public void testRetrieveAllTradeItemsForTrade() {
		when( tradeMapper.map( any( TradeDto.class ) ) ).thenAnswer( i -> mapTradeDto( i.getArgument(0) ) );
		when( tradeItemDao.selectAll( any( Trade.class ) ) ).thenReturn( prepareTradeItemListForTrade( 2 ) );
		List<TradeItemDto> tradeItems = tradeItemService.retrieveAllTradeItems( TradeDto.builder().id( 2 ).build() );
		assertNotNull( tradeItems );
		assertEquals( 3, tradeItems.size() );

		List<TradeItemDto> ti1 = tradeItems.stream()
				.filter( i -> i.getOfferRequire().equals( OFFER ) )
				.collect( Collectors.toList() );
		assertEquals( 1, ti1.size() );

		List<TradeItemDto> ti2 = tradeItems.stream()
				.filter( i -> i.getOfferRequire().equals( REQUIRE ) )
				.collect( Collectors.toList() );
		assertEquals( 2, ti2.size() );
	}

	@Test
	public void testRetrieveAllTradeItemsForItem() {
		when( tradeItemDao.selectAll( any( Item.class ) ) ).thenReturn( prepareTradeItemListForItem( 1001 ) );
		List<TradeItemDto> tradeItems = tradeItemService.retrieveAllTradeItems( ItemDto.builder().id( 1001 ).name( "Emerald" ).build() );
		assertNotNull( tradeItems );
		assertEquals( 3, tradeItems.size() );

		TradeItemDto ti1 = tradeItems.stream()
				.filter( i -> i.getTrade().getId().equals( 2 ) &&
						i.getOfferRequire().equals( REQUIRE ) &&
						i.getSeqno().equals( 2 ) )
				.findFirst()
				.get();
		assertEquals( 15, ti1.getQuantity().intValue() );

		TradeItemDto ti2 = tradeItems.stream()
				.filter( i -> i.getTrade().getId().equals( 4 ) &&
						i.getOfferRequire().equals( REQUIRE ) &&
						i.getSeqno().equals( 1 ) )
				.findFirst()
				.get();
		assertEquals( 11, ti2.getQuantity().intValue() );
	}

	@Test
	public void testCreateNewTradeItem() {
		when( tradeItemDao.insertOne( any( TradeItem.class ) )).thenAnswer( i -> i.getArgument(0) );
		when( tradeItemMapper.map( any( TradeItemDto.class ) ) ).thenAnswer( i -> mapTradeItemDto( i.getArgument(0) ) );
		TradeItemDto newTradeItem = TradeItemDto.builder()
				.trade( TradeDto.builder().id( 1 ).build() )
				.offerRequire( REQUIRE )
				.seqno( 2 )
				.quantity( 1 )
				.item( ItemDto.builder().id( 1011 ).name( "Book" ).build() )
				.build();
		TradeItemDto insertedTradeItem = tradeItemService.createNewTradeItem( newTradeItem );
		assertNotNull( insertedTradeItem );
		assertFalse( insertedTradeItem == newTradeItem );
		assertEquals( 1, insertedTradeItem.getTrade().getId().intValue() );
		assertEquals( 1011, insertedTradeItem.getItem().getId().intValue() );
	}

	@Test
	public void testCreateNewTradeItems() {
		when( tradeItemDao.insert( anyList() )).thenAnswer( i -> i.getArgument(0) );
		when( tradeItemMapper.map( any( TradeItemDto.class ) ) ).thenAnswer( i -> mapTradeItemDto( i.getArgument(0) ) );
		List<TradeItemDto> newTradeItems = Arrays.asList(
			TradeItemDto.builder()
				.trade( TradeDto.builder().id( 16 ).build() )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( ItemDto.builder().id( 1001 ).name( "Emerald" ).build() )
				.build()
			,
			TradeItemDto.builder()
				.trade( TradeDto.builder().id( 16 ).build() )
				.offerRequire( REQUIRE )
				.seqno( 1 )
				.quantity( 35 )
				.item( ItemDto.builder().id( 1002 ).name( "Rotten Flesh" ).build() )
				.build() );
		List<TradeItemDto> insertedTradeItems = tradeItemService.createNewTradeItems( newTradeItems );
		assertNotNull( insertedTradeItems );
		assertEquals( 2, insertedTradeItems.size() );
		assertEquals( REQUIRE, insertedTradeItems.get(1).getOfferRequire() );
		assertEquals( 1002, insertedTradeItems.get(1).getItem().getId().intValue() );
	}

	@Test
	public void testSaveTradeItem() {
		when( tradeItemDao.update( any( TradeItem.class ) )).thenAnswer( i -> i.getArgument(0) );
		when( tradeItemMapper.map( any( TradeItemDto.class ) ) ).thenAnswer( i -> mapTradeItemDto( i.getArgument(0) ) );
		TradeItemDto saveTradeItem = TradeItemDto.builder()
				.trade( TradeDto.builder().id( 100 ).build() )
				.build();
		TradeItemDto savedTradeItem = tradeItemService.saveTradeItem( saveTradeItem );
		assertFalse( savedTradeItem == saveTradeItem );
		assertEquals( 100, savedTradeItem.getTrade().getId().intValue() );
	}

	@Test
	public void testDeleteTradeItem() {
		when( tradeItemDao.deleteOne( any( TradeItem.class ) )).thenReturn( true );
		when( tradeItemMapper.map( any( TradeItemDto.class ) ) ).thenAnswer( i -> mapTradeItemDto( i.getArgument(0) ) );
		TradeItemDto deleteTradeItem = TradeItemDto.builder()
				.trade( TradeDto.builder().id( 10 ).build() )
				.build();
		boolean tradeItemDeleted = tradeItemService.removeTradeItem( deleteTradeItem );
		assertTrue( tradeItemDeleted );
	}



	private static List<TradeItem> prepareTradeItemList() {
		List<TradeItem> list = new ArrayList<>();
		list.add( buildTradeItem( 2, "O", 1, 1, 1010, "Punch I" ) );
		list.add( buildTradeItem( 2, "R", 1, 1, 1011, null ) );
		list.add( buildTradeItem( 2, "R", 2, 15, 1001, null ) );
		list.add( buildTradeItem( 3, "O", 1, 1, 1001, null ) );
		list.add( buildTradeItem( 3, "R", 1, 10, 1011, null ) );
		list.add( buildTradeItem( 4, "O", 1, 1, 1012, null ) );
		list.add( buildTradeItem( 4, "R", 1, 11, 1001, null ) );
		return list;
	}

	private static List<TradeItem> prepareTradeItemListForTrade( int tradeId ) {
		List<TradeItem> list = prepareTradeItemList().stream()
				.filter( ti -> ti.getTradeId().equals( tradeId ) )
				.collect( Collectors.toList() );
		return list;
	}

	private static List<TradeItem> prepareTradeItemListForItem( int itemId ) {
		List<TradeItem> list = prepareTradeItemList().stream()
				.filter( ti -> ti.getItemId().equals( itemId ) )
				.collect( Collectors.toList() );
		return list;
	}

	private static TradeItem buildTradeItem( int tradeId, String offReq, int seqno, int quantity, int itemId, String memo ) {
		return TradeItem.builder()
				.tradeId( tradeId )
				.offerRequire( OfferRequire.getFromCode( offReq ) )
				.seqno( seqno )
				.quantity( quantity )
				.itemId( itemId )
				.memo( memo )
				.build();
	}

	private static TradeItemDto mapTradeItem( TradeItem v ) {
		return TradeItemDto.builder()
				.trade( TradeDto.builder().id( v.getTradeId() ).build() )
				.offerRequire( v.getOfferRequire() )
				.seqno( v.getSeqno() )
				.quantity( v.getQuantity() )
				.item( ItemDto.builder().id( v.getItemId() ).build() )
				.memo( v.getMemo() )
				.build();
	}

	private static TradeItem mapTradeItemDto( TradeItemDto v ) {
		return TradeItem.builder()
				.tradeId( v.getTrade().getId() )
				.offerRequire( v.getOfferRequire() )
				.seqno( v.getSeqno() )
				.quantity( v.getQuantity() )
				.itemId( v.getItem() != null ? v.getItem().getId() : null )
				.memo( v.getMemo() )
				.build();
	}

	private static Trade mapTradeDto( TradeDto dto ) {
		return Trade.builder()
				.id( dto.getId() )
				.villagerId( dto.getVillager() != null ? dto.getVillager().getId() : null )
				.tradeSeqno( dto.getTradeSeqno() )
				.build();
	}

}
