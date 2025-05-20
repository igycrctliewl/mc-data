package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * VillageDto is the application-internal representation of the Village entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
public class VillageDto implements Comparable<VillageDto> {

	private Long id;
	private String name;

	public VillageDto() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() ).append( " )");
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

	@Override
	public int compareTo( VillageDto other ) {
		if( other != null ) {
			return this.getName().compareTo( other.getName() );
		} else {
			return 1;
		}
	}



	public static VillageDto.Builder builder() {
		return new VillageDto.Builder();
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

		public VillageDto build() {
			VillageDto v = new VillageDto();
			v.setId( this.id );
			v.setName( this.name );
			return v;
		}
	}

}
