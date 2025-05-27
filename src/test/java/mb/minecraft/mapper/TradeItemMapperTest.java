package mb.minecraft.mapper;

import static mb.minecraft.model.OfferRequire.OFFER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dto.ItemDto;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.TradeItemDto;
import mb.minecraft.mapper.impl.TradeItemMapperImpl;
import mb.minecraft.model.TradeItem;
import mb.minecraft.service.ItemService;
import mb.minecraft.service.TradeService;


@RunWith(MockitoJUnitRunner.class)
public class TradeItemMapperTest {

	@InjectMocks
	TradeItemMapperImpl tradeItemMapper;

	@Mock
	TradeService tradeService;

	@Mock
	ItemService itemService;


	@Test
	public void testMapEntityToDto() {
		when( tradeService.retrieveTrade( 4L ) ).thenReturn( TRADE_O );
		when( itemService.retrieveItem( 1012L ) ).thenReturn( ITEM_O );
		TradeItemDto dto = tradeItemMapper.map( TRADE_ITEM_E );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( 4L, dto.getTrade().getId().longValue() );
		assertEquals( OFFER, dto.getOfferRequire() );
		assertEquals( 1, dto.getSeqno().intValue() );
		assertEquals( 1, dto.getQuantity().intValue() );
		assertEquals( 1012L, dto.getItem().getId().longValue() );
		assertEquals( "brand new", dto.getMemo() );
	}

	@Test
	public void testMapBasicEntityToDto() {
		TradeItem e = TradeItem.builder()
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.memo( "missing" )
				.build();
		TradeItemDto dto = tradeItemMapper.map( e );
		assertNotNull( dto );
		assertNotNull( dto.toString() );
		assertEquals( OFFER, dto.getOfferRequire() );
		assertEquals( 1, dto.getSeqno().intValue() );
		assertEquals( 1, dto.getQuantity().intValue() );
		assertEquals( "missing", dto.getMemo() );
		assertNull( dto.getTrade() );
		assertNull( dto.getItem() );
	}

	@Test
	public void testMapEntityToDtoNull() {
		TradeItem e = null;
		TradeItemDto dto = tradeItemMapper.map( e );
		assertNull( dto );
	}

	@Test
	public void testMapDtoToEntity() {
		TradeItemDto dto = TRADE_ITEM_O;
		TradeItem entity = tradeItemMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 4L, entity.getTradeId().longValue() );
		assertEquals( OFFER, entity.getOfferRequire() );
		assertEquals( 1, entity.getSeqno().intValue() );
		assertEquals( 1, entity.getQuantity().intValue() );
		assertEquals( 1012L, entity.getItemId().longValue() );
		assertEquals( "used", entity.getMemo() );
	}

	@Test
	public void testMapBasicDtoToEntity() {
		TradeItemDto dto = TradeItemDto.builder()
				.seqno( 1 )
				.quantity( 1 )
				.memo( "missing" )
				.build();
		TradeItem entity = tradeItemMapper.map( dto );
		assertNotNull( entity );
		assertNotNull( entity.toString() );
		assertEquals( 1, entity.getSeqno().intValue() );
		assertEquals( 1, entity.getQuantity().intValue() );
		assertEquals( "missing", entity.getMemo() );
		assertNull( entity.getTradeId() );
		assertNull( entity.getItemId() );
	}

	@Test
	public void testMapDtoToEntityNull() {
		TradeItemDto dto = null;
		TradeItem entity = tradeItemMapper.map( dto );
		assertNull( entity );
	}



	private static final ItemDto ITEM_O = ItemDto.builder()
			.id( 1012L )
			.name("Compass")
			.build();

	private static final TradeDto TRADE_O = TradeDto.builder()
			.id( 4L )
			.tradeSeqno( 2 )
			.build();

	private static final TradeItem TRADE_ITEM_E = TradeItem.builder()
			.tradeId( TRADE_O.getId() )
			.offerRequire( OFFER )
			.seqno( 1 )
			.quantity( 1 )
			.itemId( ITEM_O.getId() )
			.memo( "brand new" )
			.build();

	private static final TradeItemDto TRADE_ITEM_O = TradeItemDto.builder()
			.trade( TRADE_O )
			.offerRequire( OFFER )
			.seqno( 1 )
			.quantity( 1 )
			.item( ITEM_O )
			.memo( "used" )
			.build();


}
