package mb.minecraft.test;

import java.util.List;
import mb.minecraft.dto.VillageDto;
import mb.minecraft.service.VillageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 *
 * @author mikebro
 */
public class TestVillage {
   public static void main( String[] args ) {
		ApplicationContext context = new AnnotationConfigApplicationContext( "mb.minecraft" );
		VillageService svc = (VillageService) context.getBean( "villageServiceImpl" );

		System.out.println( "---------- get starting list ----------" );
		List<VillageDto> list = svc.retrieveAllVillages();
		for( VillageDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- get one id ----------" );
		VillageDto village = svc.retrieveVillage( 203L );
		System.out.println( village );

		System.out.println( "---------- get one name ----------" );
		village = svc.retrieveVillage( "Heart" );
		System.out.println( village );

		System.out.println( "---------- find or create prev ----------" );
		village = svc.findOrCreateVillage( "Deep Water Cove" );
		System.out.println( village );

		System.out.println( "---------- find or create new ----------" );
		village = svc.findOrCreateVillage( "Northern Outpost" );
		System.out.println( village );

		System.out.println( "---------- add new ----------" );
		VillageDto newObj = VillageDto.builder()
				  .name( "Pacifica" )
				  .build();
		VillageDto response = svc.createNewVillage( newObj );
		System.out.println( response );

		System.out.println( "---------- add new ----------" );
		newObj = VillageDto.builder()
				  .name( "Half Moon Bay" )
				  .build();
		response = svc.createNewVillage( newObj );
		System.out.println( response );

		System.out.println( "---------- add new ----------" );
		newObj = VillageDto.builder()
				  .name( "Daly" )
				  .build();
		response = svc.createNewVillage( newObj );
		System.out.println( response );

		System.out.println( "---------- get list ----------" );
		list = svc.retrieveAllVillages();
		for( VillageDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- delete fail (999) ----------" );
		Boolean result = svc.removeVillage( VillageDto.builder()
				  .id( 999L )
				  .build() );
		System.out.println( "Delete returned " + result );

		System.out.printf( "---------- delete one (%s)----------%n", village.getId() );
		result = svc.removeVillage( village );
		System.out.println( "Delete returned " + result );

		System.out.println( "---------- get list after delete ----------" );
		list = svc.retrieveAllVillages();
		for( VillageDto t : list ) {
			System.out.println( t );
		}
   }
}
