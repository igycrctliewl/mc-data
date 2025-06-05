package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Item is the persistent entity representing some item that a villager might
 * offer or ask for in a trade.
 * New version of Item class for entity redesign.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	private static final long serialVersionUID = -6539458668001366718L;

	private Integer id;
	private String name;
	private String imageSource;


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " )
			.append( "id=" ).append( this.getId() )
			.append( COMMA_SEPARATOR )
			.append( "name=" ).append( this.getName() )
			.append( " )" );
		return sb.toString();
	}

}
