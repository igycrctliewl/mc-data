package mb.minecraft.test;

import java.util.List;
import mb.minecraft.dao.DaoConstraintException;
import mb.minecraft.dto.VillagerTypeDto;
import mb.minecraft.service.VillagerTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 *
 * @author mikebro
 */
public class TestVillagerType {
   public static void main( String[] args ) {
		ApplicationContext context = new AnnotationConfigApplicationContext( "mb.minecraft" );
		VillagerTypeService svc = (VillagerTypeService) context.getBean( "villagerTypeServiceImpl" );

		System.out.println( "---------- get starting list ----------" );
		List<VillagerTypeDto> list = svc.retrieveAllVillagerTypes();
		for( VillagerTypeDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- get one id ----------" );
		VillagerTypeDto type = svc.retrieveVillagerType( 103L );
		System.out.println( type );

		System.out.println( "---------- get one name ----------" );
		type = svc.retrieveVillagerType( "Cleric" );
		System.out.println( type );

		System.out.println( "---------- find or create prev ----------" );
		type = svc.findOrCreateVillagerType( "Shepherd" );
		System.out.println( type );

		System.out.println( "---------- find or create new ----------" );
		type = svc.findOrCreateVillagerType( "DJ" );
		System.out.println( type );

		System.out.println( "---------- add new ----------" );
		VillagerTypeDto newObj = VillagerTypeDto.builder()
				  .profession( "Cartographer" )
				  .build();
		VillagerTypeDto response = svc.createNewVillagerType( newObj );
		System.out.println( response );

		System.out.println( "---------- add new ----------" );
		newObj = VillagerTypeDto.builder()
				  .profession( "Farmer" )
				  .build();
		response = svc.createNewVillagerType( newObj );
		System.out.println( response );

		System.out.println( "---------- add new ----------" );
		newObj = VillagerTypeDto.builder()
				  .profession( "Armorer" )
				  .build();
		response = svc.createNewVillagerType( newObj );
		System.out.println( response );

		System.out.println( "---------- add new fail test ----------" );
		VillagerTypeDto failObj = VillagerTypeDto.builder()
				  .profession( "Armorer" )
				  .build();
		try {
			response = svc.createNewVillagerType( failObj );
		} catch( DaoConstraintException e ) {
			System.out.println( "Successful test threw DaoConstraintException" );
			System.out.println( e.getMessage() );
			System.out.printf( "Offending object: %s%n", e.getOffendingObject() );
		} catch( Exception e ) {
			System.out.printf( "Unsuccessful test threw %s", e.getClass() );
		}

		System.out.println( "---------- get list ----------" );
		list = svc.retrieveAllVillagerTypes();
		for( VillagerTypeDto t : list ) {
			System.out.println( t );
		}

		System.out.println( "---------- delete fail (999) ----------" );
		Boolean result = svc.removeVillagerType( VillagerTypeDto.builder()
				  .id( 999L )
				  .build() );
		System.out.println( "Delete returned " + result );

		System.out.printf( "---------- delete one (%s) ----------%n", type.getId().toString() );
		result = svc.removeVillagerType( type );
		System.out.println( "Delete returned " + result );

		System.out.println( "---------- get list after delete ----------" );
		list = svc.retrieveAllVillagerTypes();
		for( VillagerTypeDto t : list ) {
			System.out.println( t );
		}
   }
}
