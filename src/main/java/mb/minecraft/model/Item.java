package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.io.Serializable;

/**
 * Item is the persistent entity representing some item that a villager might
 * offer or ask for in a trade.
 * New version of Item class for entity redesign.
 * @author mikebro
 */
public class Item implements Serializable {

	private static final long serialVersionUID = -6539458668001366718L;

	private Long id;
	private String name;
	private String imageSource;


	public Item() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() ).append( " )" );
		return sb.toString();
	}


	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource( String imageSource ) {
		this.imageSource = imageSource;
	}



	public static Item.Builder builder() {
		return new Item.Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private String imageSource;
		public Builder id( Long id ) {
			this.id = id;
			return this;
		}
		public Builder name( String name ) {
			this.name = name;
			return this;
		}
		public Builder imageSource( String imageSource ) {
			this.imageSource = imageSource;
			return this;
		}

		public Item build() {
			Item item = new Item();
			item.setId( this.id );
			item.setName( this.name );
			item.setImageSource( this.imageSource );
			return item;
		}
	}

}
