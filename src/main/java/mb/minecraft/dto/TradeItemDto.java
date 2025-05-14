package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * TradeItemDto is the application-internal representation of the TradeItem entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
public class TradeItemDto {

	private Long id;
	private Integer quantity;
	private ItemDto item;


	public TradeItemDto() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "quantity=" ).append( this.getQuantity() ).append( COMMA_SEPARATOR );

		sb.append( "item=" );
		if( this.getItem() == null ) {
			sb.append( "null" );
		} else {
			sb.append( this.getItem().toString() );
		}

		sb.append( " )");
		return sb.toString();
	}


	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity( Integer quantity ) {
		this.quantity = quantity;
	}

	public ItemDto getItem() {
		return item;
	}
	public void setItem( ItemDto item ) {
		this.item = item;
	}


	public static TradeItemDto.Builder builder() {
		return new TradeItemDto.Builder();
	}

	public static class Builder {
		private Long id;
		private Integer quantity;
		private ItemDto item;
		public Builder id( Long id ) {
			this.id = id;
			return this;
		}
		public Builder quantity( Integer quantity ) {
			this.quantity = quantity;
			return this;
		}
		public Builder item( ItemDto item ) {
			this.item = item;
			return this;
		}

		public TradeItemDto build() {
			TradeItemDto dto = new TradeItemDto();
			dto.setId( this.id );
			dto.setQuantity( this.quantity );
			dto.setItem( this.item );
			return dto;
		}
	}

}
