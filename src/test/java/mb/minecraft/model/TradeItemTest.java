package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static mb.minecraft.model.OfferRequire.OFFER;
import static mb.minecraft.model.OfferRequire.REQUIRE;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.InvalidValueException;


@RunWith(MockitoJUnitRunner.class)
public class TradeItemTest {

	@Test
	public void testEntityBuilder() {
		TradeItem tradeItem = TradeItem.builder()
				.tradeId( 1000L )
				.offerRequire( REQUIRE )
				.seqno( 1 )
				.quantity( 1 )
				.itemId( 1L )
				.memo( "memo" )
				.build();
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertEquals( 1000L, tradeItem.getTradeId().longValue() );
		assertEquals( "R", tradeItem.getOfferRequire().getCode() );
		assertEquals( 1, tradeItem.getSeqno().intValue() );
		assertEquals( 1, tradeItem.getQuantity().intValue() );
		assertEquals( 1L, tradeItem.getItemId().longValue() );
		assertEquals( "memo", tradeItem.getMemo() );
	}

	@Test
	public void testEntitySetters() {
		TradeItem tradeItem = new TradeItem();
		tradeItem.setTradeId( 1001L );
		tradeItem.setOfferRequire( OFFER );
		tradeItem.setSeqno( 1 );
		tradeItem.setQuantity( 1 );
		tradeItem.setItemId( 10L );
		tradeItem.setMemo( "Punch I" );
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertEquals( 1001L, tradeItem.getTradeId().longValue() );
		assertEquals( "O", tradeItem.getOfferRequire().getCode() );
		assertEquals( 1, tradeItem.getSeqno().intValue() );
		assertEquals( 1, tradeItem.getQuantity().intValue() );
		assertEquals( 10L, tradeItem.getItemId().longValue() );
		assertEquals( "Punch I", tradeItem.getMemo() );
	}

	@Test
	public void testEntityNullProperties() {
		TradeItem tradeItem = new TradeItem();
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertNull( tradeItem.getTradeId() );
		assertNull( tradeItem.getOfferRequire() );
		assertNull( tradeItem.getSeqno() );
		assertNull( tradeItem.getQuantity() );
		assertNull( tradeItem.getItemId() );
		assertNull( tradeItem.getMemo() );
	}

	@Test
	public void testOfferRequireO() {
		OfferRequire offer = OfferRequire.getFromCode( "O" );
		assertNotNull( offer );
		assertEquals( "O", offer.getCode() );
	}

	@Test
	public void testOfferRequireR() {
		OfferRequire require = OfferRequire.getFromCode( "R" );
		assertNotNull( require );
		assertEquals( "R", require.getCode() );
	}

	@Test
	public void testOfferRequireX() {
		Exception e = assertThrows( InvalidValueException.class, () -> OfferRequire.getFromCode( "X" ) );
		assertEquals( "\"X\" is an invalid value for type \"OfferRequire\"", e.getMessage() );
	}

	@Test
	public void testOfferRequireNull() {
		Exception e = assertThrows( InvalidValueException.class, () -> OfferRequire.getFromCode( null ) );
		assertEquals( "\"null\" is an invalid value for type \"OfferRequire\"", e.getMessage() );
	}

}
