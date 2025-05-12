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
 * No two VillagerType entities can have the same description (profession).<br>
 * <br>
 * A VillagerType can be deleted, but only after ensuring there are no villagers
 * using the that VillagerType.
 * @author mikebro
 */
public interface VillagerTypeDao extends DisposableBean {
	public VillagerType selectOneById( Long id );
	public VillagerType selectOneByName( String profession );
	public List<VillagerType> selectAll();
	public VillagerType insertOne( VillagerType villagerType);
	public Long getNextIdSeq();
	public boolean deleteOne( VillagerType villagerType );
}
