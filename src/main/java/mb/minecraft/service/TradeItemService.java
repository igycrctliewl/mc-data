package mb.minecraft.service;

import java.util.List;

import mb.minecraft.dto.ItemDto;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.TradeItemDto;

/**
 * @author mikebro
 */
public interface TradeItemService {

	public List<TradeItemDto> retrieveAllTradeItems();
	public List<TradeItemDto> retrieveAllTradeItems( TradeDto trade );
	public List<TradeItemDto> retrieveAllTradeItems( ItemDto item );
	public TradeItemDto createNewTradeItem( TradeItemDto tradeItem );
	public List<TradeItemDto> createNewTradeItems( List<TradeItemDto> tradeItems );
	public TradeItemDto saveTradeItem( TradeItemDto tradeItem );
	public boolean removeTradeItem( TradeItemDto tradeItem );
}
