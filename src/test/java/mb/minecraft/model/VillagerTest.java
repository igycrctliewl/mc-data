package mb.minecraft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VillagerTest {

	@Test
	public void testVillager() {
		Villager villager = Villager.builder()
				.name("Gary")
				.build();
		assertNotNull( villager );
		assertNull( villager.getId() );
		assertEquals( "Gary", villager.getName() );
		assertFalse( villager.isTagged() );
	}

}
