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
public class VillageDtoTest {

	@Test
	public void testDtoByBuilder() {
		VillageDto village = VillageDto.builder()
				.id( 101L )
				.name( "Pacifica" )
				.build();
		assertNotNull( village );
		assertEquals( 101L, village.getId().longValue() );
		assertEquals( "Pacifica", village.getName() );

		String patternString = "mb.minecraft.dto.VillageDto@\\w*\\( id=101, name=Pacifica \\)";
		assertTrue( compareStringsByRegex( patternString, village.toString()) );
	}

	@Test
	public void testDtoByBuilderIdNull() {
		VillageDto village = VillageDto.builder()
				.name( "Pacifica" )
				.build();
		assertNotNull( village );
		assertNull( village.getId() );
		assertEquals( "Pacifica", village.getName() );

		String patternString = "mb.minecraft.dto.VillageDto@\\w*\\( id=null, name=Pacifica \\)";
		assertTrue( compareStringsByRegex( patternString, village.toString()) );
	}

	@Test
	public void testDtoBySetters() {
		VillageDto village = new VillageDto();
		village.setId( 101L );
		village.setName( "Pacifica" );

		assertNotNull( village );
		assertEquals( 101L, village.getId().longValue() );
		assertEquals( "Pacifica", village.getName() );

		String patternString = "mb.minecraft.dto.VillageDto@\\w*\\( id=101, name=Pacifica \\)";
		assertTrue( compareStringsByRegex( patternString, village.toString()) );
	}

	@Test
	public void testDtoWithPropertiesNull() {
		VillageDto village = new VillageDto();

		assertNotNull( village );
		assertNull( village.getId() );
		assertNull( village.getName() );

		String patternString = "mb.minecraft.dto.VillageDto@\\w*\\( id=null, name=null \\)";
		assertTrue( compareStringsByRegex( patternString, village.toString()) );
	}


	private static boolean compareStringsByRegex( String patternString, String objectString ) {
		Pattern pattern = Pattern.compile( patternString );
		Matcher matcher = pattern.matcher( objectString );
		return matcher.find();
	}
	
}
