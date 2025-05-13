package mb.minecraft.dao;

import java.util.List;
import mb.minecraft.model.Villager;
import org.springframework.beans.factory.DisposableBean;

/**
 *
 * @author mikebro
 */
public interface VillagerDao extends DisposableBean {
	public Villager selectOneById( Long id );
	public Villager selectOneByName( String name );
	public List<Villager> selectAll();
	public Villager insertOne( Villager villager );

	/* why would anything outside the dao class need this service? */
	public Long getNextIdSeq();

	public boolean deleteOne( Villager villager );
}
