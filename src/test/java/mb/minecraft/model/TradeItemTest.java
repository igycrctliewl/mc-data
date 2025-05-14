package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TradeItemTest {

	@Test
	public void testEntityBuilder() {
		TradeItem tradeItem = TradeItem.builder()
				.id( 91001L )
				.quantity( 7 )
				.itemId( 101L )
				.build();
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertEquals( 91001L, tradeItem.getId().longValue() );
		assertEquals( 7, tradeItem.getQuantity().intValue() );
		assertEquals( 101L, tradeItem.getItemId().longValue() );
	}

	@Test
	public void testEntitySetters() {
		TradeItem tradeItem = new TradeItem();
		tradeItem.setId( 91001L );
		tradeItem.setQuantity( 7 );
		tradeItem.setItemId( 101L );
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertEquals( 91001L, tradeItem.getId().longValue() );
		assertEquals( 7, tradeItem.getQuantity().intValue() );
		assertEquals( 101L, tradeItem.getItemId().longValue() );
	}

	@Test
	public void testEntityNullProperties() {
		TradeItem tradeItem = new TradeItem();
		assertNotNull( tradeItem );
		assertNotNull( tradeItem.toString() );
		assertNull( tradeItem.getId() );
		assertNull( tradeItem.getQuantity() );
		assertNull( tradeItem.getItemId() );
	}

}
