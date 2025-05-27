package mb.minecraft.mapper;

import org.springframework.stereotype.Component;

import mb.minecraft.dto.TradeDto;
import mb.minecraft.model.Trade;

/**
 * TradeMapper manages transformations between the Trade entity and the TradeDto.
 * Part of trade upgrade.
 * @author mikebro
 */
@Component
public interface TradeMapper {

	public Trade map( TradeDto dto );
	public TradeDto map( Trade trade );

}
