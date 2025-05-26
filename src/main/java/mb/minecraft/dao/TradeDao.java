package mb.minecraft.dao;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;

import mb.minecraft.model.Trade;
import mb.minecraft.model.Villager;

/**
 * Data access services for the Trade entity.  Note that TradeItem has a dependency
 * on Trade, so if a Trade is going to be deleted, the corresponding TradeItem entries
 * should also be deleted.
 * 
 * @author mikebro
 */
public interface TradeDao extends DisposableBean {

	public Trade selectOneById( Long id );
	public List<Trade> selectAll();

	/**
	 * Select all the Trade entries offered by a single Villager.
	 * @param villager
	 * @return List of trades offered by the villager
	 */
	public List<Trade> selectAll( Villager villager );

	public Trade insertOne( Trade trade );

	/** 
	 * Inserting many entities at once:
	 * This would take a list, possibly generate new ID numbers for each,
	 * insert each, and reply with the entities updated with the generated ID
	 * 
	 * @param trades List of trades to insert
	 * @return The same List, with generated keys updated
	 */
	public List<Trade> insert( List<Trade> trades );

	public Trade update( Trade trade );
	public boolean deleteOne( Trade trade );
}
