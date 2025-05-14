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
public class ItemDtoTest {

	@Test
	public void testDtoByBuilder() {
		ItemDto item = ItemDto.builder()
				.id( 1001L )
				.name( "Diamond" )
				.imageSource( "https://minecraft.wiki/images/Diamond_JE3_BE3.png" )
				.build();
		assertNotNull( item );
		assertEquals( 1001L, item.getId().longValue() );
		assertEquals( "Diamond", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Diamond_JE3_BE3.png", item.getImageSource() );
		assertNotNull( item.getImage() );
	}

	@Test
	public void testDtoByBuilderIdNull() {
		ItemDto item = ItemDto.builder()
				.name( "Diamond" )
				.build();
		assertNotNull( item );
		assertNotNull( item.toString() );
		assertNull( item.getId() );
		assertEquals( "Diamond", item.getName() );

		String patternString = "mb.minecraft.dto.ItemDto@\\w*\\( id=null, name=Diamond \\)";
		assertTrue( compareStringsByRegex( patternString, item.toString()) );
	}

	@Test
	public void testDtoBySetters() {
		ItemDto item = new ItemDto();
		item.setId( 1001L );
		item.setName( "Diamond" );
		item.setImageSource( "https://minecraft.wiki/images/Diamond_JE3_BE3.png" );
		assertNotNull( item );
		assertEquals( 1001L, item.getId().longValue() );
		assertEquals( "Diamond", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Diamond_JE3_BE3.png", item.getImageSource() );
		assertNotNull( item.getImage() );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		ItemDto item = new ItemDto();
		assertNotNull( item );
		assertNull( item.getId() );
		assertNull( item.getName() );
		assertNull( item.getImageSource() );
		assertNull( item.getImage() );

		String patternString = "mb.minecraft.dto.ItemDto@\\w*\\( id=null, name=null \\)";
		assertTrue( compareStringsByRegex( patternString, item.toString()) );
	}

	@Test
	public void testDtoImageSourceFail() {
		ItemDto item = new ItemDto();
		item.setId( 1001L );
		item.setName( "Diamond" );
		item.setImageSource( "https://minecraftwiki/images/Diamond_JE3_BE3.png" );
		assertNull( item.getImage() );
	}


	private static boolean compareStringsByRegex( String patternString, String objectString ) {
		Pattern pattern = Pattern.compile( patternString );
		Matcher matcher = pattern.matcher( objectString );
		return matcher.find();
	}
	
}
