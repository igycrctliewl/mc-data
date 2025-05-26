package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mb.minecraft.model.OfferRequire;

/**
 * TradeItemDto is the application-internal representation of the TradeItem entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeItemDto implements Comparable<TradeItemDto> {

	private TradeDto trade;
	private OfferRequire offerRequire;
	private Integer seqno;
	private Integer quantity;
	private ItemDto item;
	private String memo;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() )
			.append( "( " )
			.append( "trade=" )
			.append( this.getTrade() == null ? "null" : this.getTrade().toString() )
			.append( COMMA_SEPARATOR )
			.append( "offerRequire=" ).append( this.getOfferRequire() )
			.append( COMMA_SEPARATOR )
			.append( "seqno=" ).append( this.getSeqno() )
			.append( COMMA_SEPARATOR )
			.append( "quantity=" ).append( this.getQuantity() )
			.append( COMMA_SEPARATOR )
			.append( "item=" )
			.append( this.getItem() == null ? "null" : this.getItem().toString() )
			.append( COMMA_SEPARATOR )
			.append( "memo=" ).append( this.getMemo() )
			.append( " )" );
		return sb.toString();
	}

	@Override
	public int compareTo( TradeItemDto other ) {

		if( other == null )
			return 1;

		if( this.getTrade() == null || other.getTrade() == null )
			return 0;

		// order by trade, then offerRequire, then seqno
		if( this.getTrade().compareTo( other.getTrade() ) == 0 ) {
			if( this.getOfferRequire() == null || other.getOfferRequire() == null ) {
				return 0;
			} else {
				if( this.getOfferRequire().compareTo( other.getOfferRequire() ) == 0 ) {
					if( this.getSeqno() == null || other.getSeqno() == null ) {
						return 0;
					} else {
						return this.getSeqno().compareTo( other.getSeqno() );
					}
				} else {
					return this.getOfferRequire().compareTo( other.getOfferRequire() );
				}
			}
		} else {
			return this.getTrade().compareTo( other.getTrade() );
		}
	}

}
