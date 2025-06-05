package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TradeDto is the application-internal representation of the Trade entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeDto implements Comparable<TradeDto> {

	private Integer id;
	private VillagerDto villager;
	private Integer tradeSeqno;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() )
			.append( "( " )
			.append( "id=" ).append( this.getId() )
			.append( COMMA_SEPARATOR )
			.append( "villager=" ).append( this.getVillager() )
			.append( COMMA_SEPARATOR )
			.append( "tradeSeqno=" ).append( this.getTradeSeqno() )
			.append( " )" );
		return sb.toString();
	}

	@Override
	public int compareTo( TradeDto other ) {
		if( other == null )
			return 1;

		if( this.getVillager() == null || other.getVillager() == null )
			return 0;

		// Order by villager, then tradeSeqno
		if( this.getVillager().compareTo( other.getVillager() ) == 0 ) {
			if( this.getTradeSeqno() == null || other.getTradeSeqno() == null ) {
				return 0;
			} else {
				return this.getTradeSeqno().compareTo( other.getTradeSeqno() );
			}
		} else {
			return this.getVillager().compareTo( other.getVillager() );
		}
	}

}
