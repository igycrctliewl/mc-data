package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trade is the persistent entity representing an individual trade advertised by a villager.
 * This row both declares the trade entity and relates the trade to a villager.
 * @author mikebro
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trade implements Serializable {

	private static final long serialVersionUID = -1936991223553152479L;

	private Long id;
	private Long villagerId;
	private Integer tradeSeqno;


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " )
			.append( "id=" ).append( this.getId() )
			.append( COMMA_SEPARATOR )
			.append( "villagerId=" ).append( this.getVillagerId() )
			.append( COMMA_SEPARATOR )
			.append( "tradeSeqno=" ).append( this.getTradeSeqno() )
			.append( " )" );
		return sb.toString();
	}

}
