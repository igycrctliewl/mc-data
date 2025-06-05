package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TradeTest {

	@Test
	public void testEntityBuilder() {
		Trade trade = Trade.builder()
				.id( 1000 )
				.villagerId( 1 )
				.tradeSeqno( 1 )
				.build();
		assertNotNull( trade );
		assertEquals( 1000, trade.getId().intValue() );
		assertEquals( 1, trade.getVillagerId().intValue() );
		assertEquals( 1, trade.getTradeSeqno().intValue() );
	}

	@Test
	public void testEntitySetters() {
		Trade trade = new Trade();
		trade.setId( 1001 );
		trade.setVillagerId( 1 );
		trade.setTradeSeqno( 2 );
		assertNotNull( trade );
		assertEquals( 1001, trade.getId().intValue() );
		assertEquals( 1, trade.getVillagerId().intValue() );
		assertEquals( 2, trade.getTradeSeqno().intValue() );
	}

	@Test
	public void testEntityNullProperties() {
		Trade trade = new Trade();
		assertNotNull( trade );
		assertNotNull( trade.toString() );
		assertNull( trade.getId() );
		assertNull( trade.getVillagerId() );
		assertNull( trade.getTradeSeqno() );
	}

}
