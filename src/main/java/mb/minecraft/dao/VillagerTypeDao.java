package mb.minecraft.dao;

import java.util.List;
import mb.minecraft.model.VillagerType;
import org.springframework.beans.factory.DisposableBean;

/**
 * The contract for VillagerTypeDao ensures that a VillagerType object, once created,
 * is immutable.  A VillagerType can be assigned to a Villager with certainty that
 * that VillagerType object will not change.  For this reason, VillagerTypeDao will
 * have no "update" method.<br>
 * <br>
 * No two VillagerType objects can have the same description (name).<br>
 * <br>
 * A VillagerType can be deleted, but only after ensuring there are no villagers
 * using the type that will be deleted.
 * @author mikebro
 */
public interface VillagerTypeDao extends DisposableBean {
	public VillagerType selectOneById( Long id );
	public VillagerType selectOneByName( String name );
	public List<VillagerType> selectAll();
	public VillagerType insertOne( VillagerType villagerType);
	public Long getNextIdSeq();
	public Boolean deleteOne( VillagerType villagerType );
}
