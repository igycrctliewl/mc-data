package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.io.Serializable;

/**
 * Village is the persistent entity representing a named village in the Minecraft
 * world.
 * New version of Village class for entity redesign.
 * @author mikebro
 */
public class Village implements Serializable {

   private static final long serialVersionUID = -1151545047029849390L;

	private Long id;
	private String name;

	public Village() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId().toString() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() );
		sb.append( " )");
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



	public static Village.Builder builder() {
		return new Village.Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		public Builder id( Long id ) {
			this.id = id;
			return this;
		}
		public Builder name( String name ) {
			this.name = name;
			return this;
		}

		public Village build() {
			Village v = new Village();
			v.setId( this.id );
			v.setName( this.name );
			return v;
		}
	}

}
