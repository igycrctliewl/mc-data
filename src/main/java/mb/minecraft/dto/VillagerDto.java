package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VillagerDto is the application-internal representation of the Villager entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VillagerDto implements Comparable<VillagerDto> {

	private Integer id;
	private String name;
	private VillagerTypeDto type;
	private VillageDto village;
	private boolean tagged;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() )
			.append( "( " )
			.append( "id=" ).append( this.getId() )
			.append( COMMA_SEPARATOR )
			.append( "name=" ).append( this.getName() )
			.append( COMMA_SEPARATOR )
			.append( "isTagged=" ).append( this.isTagged() )
			.append( COMMA_SEPARATOR )
			.append( "village=" )
			.append( this.getVillage() == null ? "null" : this.getVillage().toString() )
			.append( COMMA_SEPARATOR )
			.append( "villagerType=" )
			.append( this.getType() == null ? "null" : this.getType().toString() )
			.append( " )" );
		return sb.toString();
	}

	@Override
	public int compareTo( VillagerDto other ) {
		if( other != null ) {
			return this.getName().compareTo( other.getName() );
		} else {
			return 1;
		}
	}

}
