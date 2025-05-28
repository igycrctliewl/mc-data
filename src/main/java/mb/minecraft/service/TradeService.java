package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.VillagerDto;

/**
 * @author mikebro
 */
public interface TradeService {

	public TradeDto retrieveTrade( Long id );
	public TradeDto createNewTrade( TradeDto trade );
	public List<TradeDto> createNewTrades( List<TradeDto> trades );
	public List<TradeDto> retrieveAllTrades();
	public List<TradeDto> retrieveAllTrades( VillagerDto villager );
	public TradeDto saveTrade( TradeDto trade );
	public boolean removeTrade( TradeDto trade );
}
