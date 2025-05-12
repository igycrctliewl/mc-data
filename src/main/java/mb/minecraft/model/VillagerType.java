package mb.minecraft.model;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.io.Serializable;

/**
 * VillagerType is the persistent entity representing the profession of a Villager
 * in the Minecraft world.
 * VillagerType should be immutable.
 * <p>
 * Villager types should be unique, but that constraint is not enforced by this
 * model object.  That should be enforced by the DAO implementation for VillagerType.
 * <p>
 * Part of entity redesign.
 * @author mikebro
 */
public class VillagerType implements Serializable {

	private static final long serialVersionUID = 3987029880867258483L;

	private Long id;
	private String profession;

	VillagerType() {
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
	private void setId( Long id ) {
		this.id = id;
	}
	public String getProfession() {
		return profession;
	}
	private void setProfession( String profession ) {
		this.profession = profession;
	}



	public static VillagerType.Builder builder() {
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

		public VillagerType build() {
			VillagerType t = new VillagerType();
			t.setId( this.id );
			t.setProfession( this.profession );
			return t;
		}
	}

}
