package mb.minecraft.mapper;

import org.springframework.stereotype.Component;

import mb.minecraft.dto.TradeItemDto;
import mb.minecraft.model.TradeItem;

/**
 * TradeItemMapper manages transformations between the TradeItem entity and the TradeItemDto.
 * Part of trade upgrade.
 * @author mikebro
 */
@Component
public interface TradeItemMapper {

	public TradeItem map( TradeItemDto dto );
	public TradeItemDto map( TradeItem tradeItem );

}
