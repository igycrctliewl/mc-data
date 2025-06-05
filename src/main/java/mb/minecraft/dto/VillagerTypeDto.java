package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VillagerTypeDto is the application-internal representation of the VillagerType entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VillagerTypeDto implements Comparable<VillagerTypeDto> {

	private Integer id;
	private String profession;


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "profession=" ).append( this.getProfession() );
		sb.append( " )");
		return sb.toString();
	}

	// Setters are private because this object should be immutable.
	@SuppressWarnings("unused")
	private void setId( int id ) {
		this.id = id;
	}
	@SuppressWarnings("unused")
	private void setProfession( String profession ) {
		this.profession = profession;
	}

	@Override
	public int compareTo( VillagerTypeDto other ) {
		if( other != null ) {
			return this.getProfession().compareTo( other.getProfession() );
		} else {
			return 1;
		}
	}

}
