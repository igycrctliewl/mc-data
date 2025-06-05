package mb.minecraft.dto;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TradeDtoTest {

	private static final VillageDto VILLAGE = VillageDto.builder()
			.id( 213 )
			.name( "Calgary" )
			.build();

	private static final VillagerDto VILLAGER = VillagerDto.builder()
			.id( 73 )
			.name( "Tyler" )
			.village( VILLAGE )
			.build();

	@Test
	public void testDtoByBuilder() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		assertNotNull( trade );
		assertEquals( 9, trade.getId().intValue() );
		assertEquals( VILLAGER, trade.getVillager() );
		assertEquals( 1, trade.getTradeSeqno().intValue() );
	}

	@Test
	public void testOrderOtherNull() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		TradeDto other = null;
		assertEquals( 1, trade.compareTo( other ) );
	}

	@Test
	public void testThisVillagerNull() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.tradeSeqno( 1 )
				.build();
		TradeDto other = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		assertEquals( 0, trade.compareTo( other ) );
	}

	@Test
	public void testOtherVillagerNull() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		TradeDto other = TradeDto.builder()
				.id( 9 )
				.tradeSeqno( 1 )
				.build();
		assertEquals( 0, trade.compareTo( other ) );
	}

	@Test
	public void testVillagerEqualThisSeqnoNull() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.build();
		TradeDto other = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		assertEquals( 0, trade.compareTo( other ) );
	}

	@Test
	public void testVillagerEqualOtherSeqnoNull() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		TradeDto other = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.build();
		assertEquals( 0, trade.compareTo( other ) );
	}

	@Test
	public void testVillagerEqualSeqnoNotEqual() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		TradeDto other = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 2 )
				.build();
		assertTrue( trade.compareTo( other ) < 0 );
	}

	@Test
	public void testVillagerNotEqual() {
		TradeDto trade = TradeDto.builder()
				.id( 9 )
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		TradeDto other = TradeDto.builder()
				.id( 9 )
				.villager( VillagerDto.builder()
						.id( 705 )
						.name( "Amy" )
						.build() )
				.tradeSeqno( 1 )
				.build();
		assertTrue( trade.compareTo( other ) > 0 );
	}

	@Test
	public void testDtoByBuilderIdNull() {
		TradeDto trade = TradeDto.builder()
				.villager( VILLAGER )
				.tradeSeqno( 1 )
				.build();
		assertNull( trade.getId() );
		assertNotNull( trade.toString() );
		assertEquals( VILLAGER, trade.getVillager() );
	}

	@Test
	public void testDtoBySetters() {
		TradeDto trade= new TradeDto();
		trade.setId( 9 );
		trade.setVillager( VILLAGER );
		trade.setTradeSeqno( 1 );
		assertNotNull( trade );
		assertEquals( 9, trade.getId().intValue() );
		assertEquals( VILLAGER, trade.getVillager() );
		assertEquals( 1, trade.getTradeSeqno().intValue() );
		assertNotNull( trade.toString() );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		TradeDto trade = new TradeDto();
		assertNotNull( trade );
		assertNull( trade.getId() );
		assertNull( trade.getVillager() );
		assertNull( trade.getTradeSeqno() );
		assertNotNull( trade.toString() );
	}

}
