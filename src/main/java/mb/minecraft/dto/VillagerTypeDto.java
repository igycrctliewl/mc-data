package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * VillagerTypeDto is the application-internal representation of the VillagerType entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
public class VillagerTypeDto implements Comparable<VillagerTypeDto> {

	private Long id;
	private String profession;

	VillagerTypeDto() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "profession=" ).append( this.getProfession() );
		sb.append( " )");
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public String getProfession() {
		return profession;
	}


	// Setters are private because this object should be immutable.
	private void setId( Long id ) {
		this.id = id;
	}

	private void setProfession( String profession ) {
		this.profession = profession;
	}

	@Override
	public int compareTo( VillagerTypeDto other ) {
		if( other != null ) {
			return this.getProfession().compareTo( other.getProfession() );
		} else {
			return 1;
		}
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

}
