package mb.minecraft.model;

import java.io.Serializable;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

/**
 * Villager is the persistent entity representing one of the NPC villagers
 * in the Minecraft world.
 * A Villager lives in a Village and offers a selection of Trades.
 * New version of Villager class for entity redesign.
 * @author mikebro
 */
public class Villager implements Serializable {

	private static final long serialVersionUID = -3305610392007863400L;

	private Long id;
	private String name;
	private Long villageId;
	private Long typeId;
	/** Has this villager been assigned a NameTag in the game? */
	private boolean tagged;


	Villager() {
		super();
	}
	Villager( String name ) {
		this.name = name;
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

	/**
	 * This Villager can be given a name tag in the Minecraft world.  If the Villager
	 * has been tagged, this flag can be set to record the tag.
	 * @param tagged true if the Villager has been given a name tag,
	 * otherwise false.
	 */
	public void setTagged( boolean tagged ) {
		this.tagged = tagged;
	}

	/**
	 * This Villager can be given a name tag in the Minecraft world.  This indicates
	 * whether the Villager has been tagged.
	 * @return the current value of the tagged property
	 */
	public boolean isTagged() {
		return this.tagged;
	}

	/**
	 * This Villager has a profession.  This method provides a way to set the
	 * profession as a VillagerType.
	 * @param newType The VillagerType object describing this Villager's
	 * profession.
	 */
	public void setTypeId( Long newTypeId ) {
      this.typeId = newTypeId;
	}
	public Long getTypeId() {
		return this.typeId;
	}

	/**
	 * This Villager lives in a Village.  This method provides a way to set the
	 * Village.
	 * @param newVillage The ID of the Village object where this Villager lives
	 */
	public void setVillageId( Long newVillageId ) {
		this.villageId = newVillageId;
	}
	public Long getVillageId() {
		return this.villageId;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() ).append( COMMA_SEPARATOR );
		sb.append( "isTagged=" ).append( this.isTagged() ).append( COMMA_SEPARATOR );
		sb.append( "villageId=" ).append( this.getVillageId() ).append( COMMA_SEPARATOR );
		sb.append( "villagerTypeId=" ).append( this.getTypeId() );
		sb.append( " )");
		return sb.toString();
	}


	public static Villager.Builder builder() {
		return new Villager.Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private Long typeId;
		private Long villageId;
		private boolean tagged;


		public Villager.Builder id( Long id ) {
			this.id = id;
			return this;
		}
		public Villager.Builder name( String name ) {
			this.name = name;
			return this;
		}
		public Villager.Builder typeId( Long typeId ) {
			this.typeId = typeId;
			return this;
		}
		public Villager.Builder villageId( Long villageId ) {
			this.villageId = villageId;
			return this;
		}
		public Villager.Builder tagged( boolean tagged ) {
			this.tagged = tagged;
			return this;
		}

		public Villager build() {
			Villager villager = new Villager();
			villager.setId( this.id );
			villager.setName( this.name );
			villager.setTypeId( this.typeId );
			villager.setVillageId( this.villageId );
			villager.setTagged( this.tagged );
			return villager;
		}
	}
}
