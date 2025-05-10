package mb.minecraft.test;

import java.util.List;
import mb.minecraft.dto.VillageDto;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.service.VillageService;
import mb.minecraft.service.VillagerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 *
 * @author mikebro
 */
public class TestVillager {
   public static void main( String[] args ) {
		ApplicationContext context = new AnnotationConfigApplicationContext( "mb.minecraft" );
		VillagerService villagerService = (VillagerService) context.getBean( "villagerServiceImpl" );
		VillageService villageService = (VillageService) context.getBean( "villageServiceImpl" );

		System.out.println( "---------- get starting list ----------" );
		List<VillagerDto> list = villagerService.retrieveAllVillagers();
		for( VillagerDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- get one id ----------" );
		VillagerDto villager = villagerService.retrieveVillager( 73L );
		System.out.println( villager );

		System.out.println( "---------- get one name ----------" );
		villager = villagerService.retrieveVillager( "Amy" );
		System.out.println( villager );

		System.out.println( "---------- find or create prev ----------" );
		villager = villagerService.findOrCreateVillager( "Dana" );
		System.out.println( villager );

		System.out.println( "---------- find or create new ----------" );
		villager = villagerService.findOrCreateVillager( "Hank" );
		System.out.println( villager );

		System.out.println( "---------- add new ----------" );
		VillagerDto newObj = VillagerDto.builder()
				  .name( "Shelia" )
				  .build();

		// TODO: two things:
		//  2.  Each of the service classes should have an "update" method

		VillageDto v = villageService.findOrCreateVillage( "Brooklyn" );
		newObj.setVillage( v );
		VillagerDto response = villagerService.createNewVillager( newObj );
		System.out.println( response );

		System.out.println( "---------- add new ----------" );
		newObj = VillagerDto.builder()
				  .name( "Watch" )
				  .build();
		response = villagerService.createNewVillager( newObj );
		System.out.println( response );

		System.out.println( "---------- add new ----------" );
		newObj = VillagerDto.builder()
				  .name( "Ward" )
				  .build();
		response = villagerService.createNewVillager( newObj );
		System.out.println( response );

		System.out.println( "---------- get list ----------" );
		list = villagerService.retrieveAllVillagers();
		for( VillagerDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- delete fail (999) ----------" );
		Boolean result = villagerService.removeVillager( VillagerDto.builder()
				  .id( 999L )
				  .build() );
		System.out.println( "Delete returned " + result );

		System.out.printf( "---------- delete one (%s)----------%n", villager.getId() );
		result = villagerService.removeVillager( villager );
		System.out.println( "Delete returned " + result );

		System.out.println( "---------- get list after delete ----------" );
		list = villagerService.retrieveAllVillagers();
		for( VillagerDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- get list of villages ----------" );
		List<VillageDto> vList = villageService.retrieveAllVillages();
		for( VillageDto t : vList ) {
			System.out.println( t );
		}
	}
}
