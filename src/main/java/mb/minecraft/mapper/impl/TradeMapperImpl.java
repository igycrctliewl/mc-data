package mb.minecraft.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mb.minecraft.dto.TradeDto;
import mb.minecraft.mapper.TradeMapper;
import mb.minecraft.model.Trade;
import mb.minecraft.service.VillagerService;

/**
 * TradeMapper manages transformations between the Trade entity and the TradeDto.
 * Part of trade upgrade.
 * @author mikebro
 */
@Component
public class TradeMapperImpl implements TradeMapper {

	@Autowired
	private VillagerService villagerService;

	@Override
	public Trade map( TradeDto dto ) {
		if( dto == null ) {
			return null;
		}
		Trade e = Trade.builder()
				.id( dto.getId() )
				.tradeSeqno( dto.getTradeSeqno() )
				.build();
		if( dto.getVillager() != null ) {
			e.setVillagerId( dto.getVillager().getId() );
		}
		return e;
	}

	@Override
	public TradeDto map( Trade trade ) {
		if( trade == null ) {
			return null;
		}
		TradeDto dto = TradeDto.builder()
				.id( trade.getId() )
				.tradeSeqno( trade.getTradeSeqno() )
				.build();
		if( trade.getVillagerId() != null ) {
			dto.setVillager( villagerService.retrieveVillager( trade.getVillagerId() ) );
		}
		return dto;
	}

}
