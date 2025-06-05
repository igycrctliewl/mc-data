package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VillageDto is the application-internal representation of the Village entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VillageDto implements Comparable<VillageDto> {

	private Integer id;
	private String name;


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() ).append( " )");
		return sb.toString();
	}

	@Override
	public int compareTo( VillageDto other ) {
		if( other != null ) {
			return this.getName().compareTo( other.getName() );
		} else {
			return 1;
		}
	}

}
