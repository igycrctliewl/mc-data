package mb.minecraft.dto;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TradeItemDtoTest {

	private static final ItemDto DIAMOND = ItemDto.builder()
			.id( 1001L )
			.name( "Diamond" )
			.imageSource( "https://minecraft.wiki/images/Diamond_JE3_BE3.png" )
			.build();

	private static final ItemDto EMERALD = ItemDto.builder()
			.id( 2001L )
			.name( "Emerald" )
			.imageSource( "https://minecraft.wiki/images/Emerald_JE3_BE3.png" )
			.build();

	@Test
	public void testDtoByBuilder() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.id( 91001L )
				.quantity( 2 )
				.item( DIAMOND )
				.build();
		assertNotNull( tradeItem );
		assertEquals( 91001L, tradeItem.getId().longValue() );
		assertEquals( DIAMOND, tradeItem.getItem() );
	}

	@Test
	public void testDtoByBuilderIdNull() {
		TradeItemDto tradeItem = TradeItemDto.builder()
				.quantity( 2 )
				.item( DIAMOND )
				.build();
		assertNull( tradeItem.getId() );
		assertNotNull( tradeItem.toString() );
		assertEquals( DIAMOND, tradeItem.getItem() );
	}

	@Test
	public void testDtoBySetters() {
		TradeItemDto tradeItem = new TradeItemDto();
		tradeItem.setId( 92001L );
		tradeItem.setQuantity( 7 );
		tradeItem.setItem( EMERALD );
		assertNotNull( tradeItem );
		assertEquals( 92001L, tradeItem.getId().longValue() );
		assertEquals( 7, tradeItem.getQuantity().intValue() );
		assertNotNull( tradeItem.toString() );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		TradeItemDto tradeItem = new TradeItemDto();
		assertNotNull( tradeItem );
		assertNull( tradeItem.getId() );
		assertNull( tradeItem.getQuantity() );
		assertNull( tradeItem.getItem() );

		String patternString = "mb.minecraft.dto.TradeItemDto@\\w*\\( id=null, quantity=null, item=null \\)";
		assertTrue( compareStringsByRegex( patternString, tradeItem.toString()) );
	}


	private static boolean compareStringsByRegex( String patternString, String objectString ) {
		Pattern pattern = Pattern.compile( patternString );
		Matcher matcher = pattern.matcher( objectString );
		return matcher.find();
	}
	
}
