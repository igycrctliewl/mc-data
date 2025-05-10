package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * TradeItem is the persistent entity representing a thing that a villager asks
 * or offers in trade.  The TradeItem includes a reference to an item, and a quantity.
 * New version of TradeItem class for entity redesign.
 * @author mikebro
 */
public class TradeItem {

	private Long id;
   private Long itemId;
   private Integer quantity;

   private TradeItem() {}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId().toString() ).append( COMMA_SEPARATOR );
		sb.append( "itemId=" ).append( this.getItemId().toString() ).append( COMMA_SEPARATOR );
		sb.append( "quantity=" ).append( this.getQuantity() ).append( " )" );
		return sb.toString();
	}


   public void setId( Long newId ) {
      this.id = newId;
   }
   public Long getId() {
      return this.id;
   }

   public void setItemId( Long newItemId ) {
      this.itemId = newItemId;
   }
   public Long getItemId() {
      return this.itemId;
   }

   public void setQuantity( int newQuantity ) {
      this.quantity = newQuantity;
   }
   public Integer getQuantity() {
      return this.quantity;
   }


	public static TradeItem.Builder builder() {
		return new TradeItem.Builder();
	}

	public static class Builder {
		private Long id;
		private Long itemId;
		private Integer quantity;

		public Builder id( long id ) {
			this.id = id;
			return this;
		}
		public Builder itemId( long itemId ) {
			this.itemId = itemId;
			return this;
		}
		public Builder quantity( int quantity ) {
			this.quantity = quantity;
			return this;
		}
		public TradeItem build() {
			TradeItem i = new TradeItem();
			i.setId( id );
			i.setItemId( itemId );
			i.setQuantity( quantity );
			return i;
		}
	}
}
