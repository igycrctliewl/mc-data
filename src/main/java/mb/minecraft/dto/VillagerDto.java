package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * VillagerDto is the application-internal representation of the Villager entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
public class VillagerDto {

	private Long id;
	private String name;
	private VillagerTypeDto type;
	private VillageDto village;
	private boolean tagged;

	VillagerDto() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() ).append( COMMA_SEPARATOR );
		sb.append( "isTagged=" ).append( this.isTagged() ).append( COMMA_SEPARATOR );

		sb.append( "village=" );
		if( this.getVillage() == null ) {
			sb.append( "null" );
		} else {
			sb.append( this.getVillage().toString() );
		}
		sb.append( COMMA_SEPARATOR );

		sb.append( "villagerType=" );
		if( this.getType() == null ) {
			sb.append( "null" );
		} else {
			sb.append( this.getType().toString() );
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

	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}

	public void setTagged( boolean tagged ) {
		this.tagged = tagged;
	}
	public boolean isTagged() {
		return this.tagged;
	}

	public void setType( VillagerTypeDto newType ) {
		this.type = newType;
	}
	public VillagerTypeDto getType() {
		return this.type;
	}

	public void setVillage( VillageDto newVillage ) {
		this.village = newVillage;
	}
	public VillageDto getVillage() {
		return this.village;
	}



	public static VillagerDto.Builder builder() {
		return new VillagerDto.Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private VillagerTypeDto type;
		private VillageDto village;
		private boolean tagged;

		public VillagerDto.Builder id( Long id ) {
			this.id = id;
			return this;
		}
		public VillagerDto.Builder name( String name ) {
			this.name = name;
			return this;
		}
		public VillagerDto.Builder type( VillagerTypeDto type ) {
			this.type = type;
			return this;
		}
		public VillagerDto.Builder village( VillageDto village ) {
			this.village = village;
			return this;
		}
		public VillagerDto.Builder tagged( boolean tagged ) {
			this.tagged = tagged;
			return this;
		}

		public VillagerDto build() {
			VillagerDto villager = new VillagerDto();
			villager.setId( this.id );
			villager.setName( this.name );
			villager.setType( this.type );
			villager.setVillage( this.village );
			villager.setTagged( this.tagged );
			return villager;
		}
	}

}
