package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TradeItem is the persistent entity representing a thing that a villager requires
 * or offers in trade.  The TradeItem includes a reference to an item, and a quantity.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeItem implements Serializable {

	private static final long serialVersionUID = -421034078077683753L;

	private Long tradeId;
	private OfferRequire offerRequire;
	private Integer seqno;
	private Integer quantity;
	private Long itemId;
	private String memo;


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " )
			.append( "tradeId=" ).append( this.getTradeId() )
			.append( COMMA_SEPARATOR )
			.append( "offerRequire=" ).append( this.getOfferRequire().getCode() )
			.append( COMMA_SEPARATOR )
			.append( "seqno=" ).append( this.getSeqno() )
			.append( COMMA_SEPARATOR )
			.append( "quantity=" ).append( this.getQuantity() )
			.append( COMMA_SEPARATOR )
			.append( "itemId=" ).append( this.getItemId() )
			.append( " )" );
		return sb.toString();
	}

}
