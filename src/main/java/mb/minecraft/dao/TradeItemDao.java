package mb.minecraft.dao;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;

import mb.minecraft.model.TradeItem;
import mb.minecraft.model.Item;
import mb.minecraft.model.Trade;

/**
 * Data access services for the TradeItem entity.  Note that TradeItem has a dependency on Trade.
 * 
 * @author mikebro
 */
public interface TradeItemDao extends DisposableBean {

	public List<TradeItem> selectAll();

	/**
	 * Select all the TradeItem entries for a particular Trade.
	 * @param trade
	 * @return List of TradeItem entries for the given Trade.
	 */
	public List<TradeItem> selectAll( Trade trade );

	/**
	 * Select all the TradeItem entries regarding a particular Item.
	 * @param item
	 * @return List of TradeItem entries where that item is offered or required
	 */
	public List<TradeItem> selectAll( Item item );

	public TradeItem insertOne( TradeItem tradeItem );

	/** 
	 * Inserting many entities at once:
	 * This would take a list, possibly generate new ID numbers for each,
	 * insert each, and reply with the entities updated with the generated ID
	 * 
	 * @param tradeItems List of items to insert
	 * @return The same List, with generated keys updated
	 */
	public List<TradeItem> insert( List<TradeItem> tradeItems );

	public TradeItem update( TradeItem tradeItem );
	public boolean deleteOne( TradeItem tradeItem );
}
