package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * VillagerTypeDto is the application-internal representation of the VillagerType entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
public class VillagerTypeDto {

	private Long id;
	private String profession;

	VillagerTypeDto() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId().toString() ).append( COMMA_SEPARATOR );
		sb.append( "profession=" ).append( this.getProfession() );
		sb.append( " )");
		return sb.toString();
	}

	public Long getId() {
		return id;
	}
	private void setId( Long id ) {
		this.id = id;
	}
	public String getProfession() {
		return profession;
	}
	private void setProfession( String profession ) {
		this.profession = profession;
	}



	public static VillagerTypeDto.Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String profession;

		public Builder id( Long id ) {
			this.id = id;
			return this;
		}

		public Builder profession( String profession ) {
			this.profession = profession;
			return this;
		}

		public VillagerTypeDto build() {
			VillagerTypeDto t = new VillagerTypeDto();
			t.setId( this.id );
			t.setProfession( this.profession );
			return t;
		}
	}



	public static void main( String[] args ) {
		VillagerTypeDto type = VillagerTypeDto.builder()
				.id( 123L )
				.profession( "Farmer" )
				.build();
		System.out.println( type.toString() );

		type = VillagerTypeDto.builder()
				.id( 321L )
				.profession( "Fisherman" )
				.build();
		System.out.println( type.toString() );
	}
}
