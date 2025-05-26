package mb.minecraft.dto;

import static mb.minecraft.model.OfferRequire.OFFER;
import static mb.minecraft.model.OfferRequire.REQUIRE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TradeItemDtoTest {

	private static final VillagerDto VILLAGER = VillagerDto.builder()
			.id( 699L )
			.name( "Liam Z" )
			.build();

	private static final TradeDto TRADE_1 = TradeDto.builder()
			.id( 1L )
			.villager( VILLAGER )
			.tradeSeqno( 1 )
			.build();

	private static final ItemDto EMERALD = ItemDto.builder()
			.id( 1001L )
			.name( "Emerald" )
			.build();

	private static final ItemDto PAPER = ItemDto.builder()
			.id( 1009L )
			.name( "Paper" )
			.build();

	@Test
	public void testDtoByBuilder() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertEquals( 1L, tradeItem.getTrade().getId().longValue() );
		assertEquals( "O", tradeItem.getOfferRequire().getCode() );
	}

	@Test
	public void testOtherNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = null;
		assertEquals( 1L, tradeItem.compareTo( other ) );
	}

	@Test
	public void testThisTradeNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertEquals( 0, tradeItem.compareTo( other ) );
	}

	@Test
	public void testOtherTradeNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertEquals( 0, tradeItem.compareTo( other ) );
	}

	@Test
	public void testThisOfferRequireNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertEquals( 0, tradeItem.compareTo( other ) );
	}

	@Test
	public void testOtherOfferRequireNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertEquals( 0, tradeItem.compareTo( other ) );
	}

	@Test
	public void testThisSeqnoNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertEquals( 0, tradeItem.compareTo( other ) );
	}

	@Test
	public void testOtherSeqnoNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertEquals( 0, tradeItem.compareTo( other ) );
	}

	@Test
	public void testSeqnoNotEqual() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 2 )
				.quantity( 24 )
				.item( PAPER )
				.build();
		assertTrue( tradeItem.compareTo( other ) < 0 );
	}

	@Test
	public void testOfferRequireNotEqual() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( REQUIRE )
				.seqno( 1 )
				.quantity( 24 )
				.item( PAPER )
				.build();
		assertTrue( tradeItem.compareTo( other ) < 0 );
	}

	@Test
	public void testTradeNotEqual() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.trade( TRADE_1 )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		TradeItemDto other = TradeItemDto.builder()
				.trade( TradeDto.builder()
						.id( 1L )
						.villager( VILLAGER )
						.tradeSeqno( 2 )
						.build() )
				.offerRequire( OFFER )
				.seqno( 1 )
				.quantity( 1 )
				.item( EMERALD )
				.build();
		assertTrue( tradeItem.compareTo( other ) < 0 );
	}

	@Test
	public void testDtoBySetters() {
		TradeItemDto tradeItem = new TradeItemDto();
		tradeItem.setTrade( TRADE_1 );
		tradeItem.setOfferRequire( OFFER );
		tradeItem.setSeqno( 1 );
		tradeItem.setQuantity( 1 );
		tradeItem.setItem( EMERALD );
		tradeItem.setMemo( "shiny" );
		assertNotNull( tradeItem );
		assertEquals( 1L, tradeItem.getTrade().getId().longValue() );
		assertEquals( 1, tradeItem.getQuantity().intValue() );
		assertEquals( "shiny", tradeItem.getMemo() );
		assertNotNull( tradeItem.toString() );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		TradeItemDto tradeItem = new TradeItemDto();
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertNull( tradeItem.getTrade() );
		assertNull( tradeItem.getOfferRequire() );
		assertNull( tradeItem.getSeqno() );
		assertNull( tradeItem.getQuantity() );
		assertNull( tradeItem.getItem() );
		assertNull( tradeItem.getMemo() );
	}

}
