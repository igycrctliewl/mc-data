package mb.minecraft.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mb.minecraft.dto.TradeItemDto;
import mb.minecraft.mapper.TradeItemMapper;
import mb.minecraft.model.TradeItem;
import mb.minecraft.service.ItemService;
import mb.minecraft.service.TradeService;

/**
 * VillagerMapper manages transformations between the Village entity and the VillageDto.
 * Part of entity redesign.
 * @author mikebro
 */
@Component
public class TradeItemMapperImpl implements TradeItemMapper {

	@Autowired
	private TradeService tradeService;

	@Autowired
	private ItemService itemService;

	@Override
	public TradeItem map( TradeItemDto dto ) {
		if( dto == null ) {
			return null;
		}
		TradeItem v = TradeItem.builder()
				.offerRequire( dto.getOfferRequire() )
				.seqno( dto.getSeqno() )
				.quantity( dto.getQuantity() )
				.memo( dto.getMemo() )
				.build();
		if( dto.getTrade() != null ) {
			v.setTradeId( dto.getTrade().getId() );
		}
		if( dto.getItem() != null ) {
			v.setItemId( dto.getItem().getId() );
		}
		return v;
	}

	@Override
	public TradeItemDto map( TradeItem tradeItem ) {
		if( tradeItem == null ) {
			return null;
		}
		TradeItemDto dto = TradeItemDto.builder()
				.offerRequire( tradeItem.getOfferRequire() )
				.seqno( tradeItem.getSeqno() )
				.quantity( tradeItem.getQuantity() )
				.memo( tradeItem.getMemo() )
				.build();
		if( tradeItem.getTradeId() != null ) {
			dto.setTrade( tradeService.retrieveTrade( tradeItem.getTradeId() ) );
		}
		if( tradeItem.getItemId() != null ) {
			dto.setItem( itemService.retrieveItem( tradeItem.getItemId() ) );
		}
		return dto;
	}

}
