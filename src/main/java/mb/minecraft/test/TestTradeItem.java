package mb.minecraft.test;

import mb.minecraft.model.TradeItem;

/**
 *
 * @author mikebro
 */
public class TestTradeItem {

	public static void main( String[] args ) {
		TradeItem ti = TradeItem.builder()
				  .id( 1001L )
				  .itemId( 1L )
				  .quantity( 7 )
				  .build();
		System.out.println( ti );
/*
		ApplicationContext context = new AnnotationConfigApplicationContext( "mb.minecraft" );
		ItemService svc = (ItemService) context.getBean( "itemServiceImpl" );

		System.out.println( "---------- get starting list ----------" );
		List<ItemDto> list = svc.retrieveAllItems();
		for( ItemDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- get one id ----------" );
		ItemDto dto = svc.retrieveItem( 1008L );
		System.out.println( dto );

		System.out.println( "---------- get one name ----------" );
		dto = svc.retrieveItem( "Paper" );
		System.out.println( dto );

		System.out.println( "---------- find or create prev ----------" );
		dto = svc.findOrCreateItem( "Raw Beef" );
		System.out.println( dto );

		System.out.println( "---------- find or create new ----------" );
		ItemDto gold = svc.findOrCreateItem( "Gold Ingot" );
		//find or create isn't working'
		System.out.println( gold );

		System.out.println( "---------- update image source ----------" );
		gold.setImageSource( "https://static.wikia.nocookie.net/minecraft_gamepedia/images/8/8a/Gold_Ingot_JE4_BE2.png" );
		gold = svc.saveItem( gold );
		System.out.println( gold );

		System.out.println( "---------- add new ----------" );
		ItemDto newObj = ItemDto.builder()
				  .name( "Bottle o' Enchanting" )
				  .build();
		ItemDto response = svc.createNewItem( newObj );
		System.out.println( response );

		System.out.println( "---------- get list ----------" );
		list = svc.retrieveAllItems();
		for( ItemDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- delete fail (999) ----------" );
		Boolean result = svc.removeItem( ItemDto.builder()
				  .id( 999L )
				  .build() );
		System.out.println( "Delete returned " + result );

		System.out.println( "---------- delete one (1010) ----------" );
		result = svc.removeItem( svc.retrieveItem( 1010L ) );
		System.out.println( "Delete returned " + result );

		System.out.println( "---------- get list after delete ----------" );
		list = svc.retrieveAllItems();
		for( ItemDto t : list ) {
			System.out.println( t );
		}
*/

	}
}
