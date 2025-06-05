package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ItemTest {

	@Test
	public void testEntityBuilder() {
		Item item = Item.builder()
				.id( 101 )
				.name( "Emerald" )
				.imageSource( "https://minecraft.wiki/images/Emerald_JE3_BE3.png" )
				.build();
		assertNotNull( item );
		assertNotNull( item.toString() );
		assertEquals( 101, item.getId().intValue() );
		assertEquals( "Emerald", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Emerald_JE3_BE3.png", item.getImageSource() );
	}

	@Test
	public void testEntitySetters() {
		Item item = new Item();
		item.setId( 101 );
		item.setName( "Emerald" );
		item.setImageSource( "https://minecraft.wiki/images/Emerald_JE3_BE3.png" );
		assertNotNull( item );
		assertNotNull( item.toString() );
		assertEquals( 101, item.getId().intValue() );
		assertEquals( "Emerald", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Emerald_JE3_BE3.png", item.getImageSource() );
	}

	@Test
	public void testEntityNullProperties() {
		Item item = new Item();
		assertNotNull( item );
		assertNotNull( item.toString() );
		assertNull( item.getId() );
		assertNull( item.getName() );
		assertNull( item.getImageSource() );
	}

}
