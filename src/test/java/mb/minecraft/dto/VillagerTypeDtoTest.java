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
public class VillagerTypeDtoTest {

	@Test
	public void testDtoByBuilder() {
		VillagerTypeDto type = VillagerTypeDto.builder()
				.id( 301 )
				.profession( "Cleric" )
				.build();
		assertNotNull( type );
		assertNotNull( type.toString() );
		assertEquals( 301, type.getId().intValue() );
		assertEquals( "Cleric", type.getProfession() );

		String patternString = "mb.minecraft.dto.VillagerTypeDto@\\w*\\( id=301, profession=Cleric \\)";
		assertTrue( compareStringsByRegex( patternString, type.toString()) );
	}

	@Test
	public void testDtoByBuilderIdNull() {
		VillagerTypeDto type = VillagerTypeDto.builder()
				.profession( "Cleric" )
				.build();
		assertNotNull( type );
		assertNotNull( type.toString() );
		assertNull( type.getId() );
		assertEquals( "Cleric", type.getProfession() );

		String patternString = "mb.minecraft.dto.VillagerTypeDto@\\w*\\( id=null, profession=Cleric \\)";
		assertTrue( compareStringsByRegex( patternString, type.toString()) );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		VillagerTypeDto type = new VillagerTypeDto();
		assertNotNull( type );
		assertNotNull( type.toString() );
		assertNull( type.getId() );
		assertNull( type.getProfession() );

		String patternString = "mb.minecraft.dto.VillagerTypeDto@\\w*\\( id=null, profession=null \\)";
		assertTrue( compareStringsByRegex( patternString, type.toString()) );
	}


	private static boolean compareStringsByRegex( String patternString, String objectString ) {
		Pattern pattern = Pattern.compile( patternString );
		Matcher matcher = pattern.matcher( objectString );
		return matcher.find();
	}

}
