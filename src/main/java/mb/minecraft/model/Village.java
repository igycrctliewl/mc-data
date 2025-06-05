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

	private Integer id;
	private String name;

	public Village() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " )
			.append( "id=" ).append( this.getId() )
			.append( COMMA_SEPARATOR )
			.append( "name=" ).append( this.getName() )
			.append( " )");
		return sb.toString();
	}


	public Integer getId() {
		return id;
	}
	public void setId( Integer id ) {
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
		private Integer id;
		private String name;
		public Builder id( Integer id ) {
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
