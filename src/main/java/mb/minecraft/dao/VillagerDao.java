package mb.minecraft.dao;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;

import mb.minecraft.model.Villager;

/**
 *
 * @author mikebro
 */
public interface VillagerDao extends DisposableBean {
	public Villager selectOneById( Long id );
	public Villager selectOneByName( String name );
	public List<Villager> selectAll();
	public Villager insertOne( Villager villager );
	public Villager update( Villager villager );
	public boolean deleteOne( Villager villager );
}
