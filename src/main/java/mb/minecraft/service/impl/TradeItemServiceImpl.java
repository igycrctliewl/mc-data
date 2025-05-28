package mb.minecraft.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mb.minecraft.dao.TradeItemDao;
import mb.minecraft.dto.ItemDto;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.TradeItemDto;
import mb.minecraft.mapper.ItemMapper;
import mb.minecraft.mapper.TradeItemMapper;
import mb.minecraft.mapper.TradeMapper;
import mb.minecraft.model.TradeItem;
import mb.minecraft.service.TradeItemService;

/**
 *
 * @author mikebro
 */
@Service
public class TradeItemServiceImpl implements TradeItemService {

	@Autowired
	private TradeItemDao tradeItemDao;

	@Autowired
	private TradeMapper tradeMapper;

	@Autowired
	private TradeItemMapper tradeItemMapper;

	private TradeItemServiceImpl() {}


	@Override
	public List<TradeItemDto> retrieveAllTradeItems() {
		List<TradeItem> tradeItems = tradeItemDao.selectAll();
		List<TradeItemDto> dtoList = tradeItems.stream()
				.map( ti -> tradeItemMapper.map( ti ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public List<TradeItemDto> retrieveAllTradeItems( TradeDto trade ) {
		List<TradeItem> tradeItems = tradeItemDao.selectAll( tradeMapper.map( trade ) );
		List<TradeItemDto> dtoList = tradeItems.stream()
				.map( ti -> tradeItemMapper.map( ti ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public List<TradeItemDto> retrieveAllTradeItems( ItemDto item ) {
		List<TradeItem> tradeItems = tradeItemDao.selectAll( ItemMapper.map( item ) );
		List<TradeItemDto> dtoList = tradeItems.stream()
				.map( ti -> tradeItemMapper.map( ti ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public TradeItemDto createNewTradeItem( TradeItemDto dto ) {
		TradeItem tradeItem = tradeItemDao.insertOne( tradeItemMapper.map( dto ) );
		return tradeItemMapper.map( tradeItem );
	}

	@Override
	public List<TradeItemDto> createNewTradeItems( List<TradeItemDto> dtoList ) {
		List<TradeItem> tradeItems = dtoList.stream()
				.map( ti -> tradeItemMapper.map( ti ) )
				.collect( Collectors.toList() );
		List<TradeItem> result = tradeItemDao.insert( tradeItems );
		return result.stream()
				.map( ti -> tradeItemMapper.map( ti ) )
				.collect( Collectors.toList() );
	}

	@Override
	public TradeItemDto saveTradeItem( TradeItemDto dto ) {
		TradeItem tradeItem = tradeItemMapper.map( dto );
		return tradeItemMapper.map( tradeItemDao.update( tradeItem ) );
	}

	@Override
	public boolean removeTradeItem( TradeItemDto dto ) {
		TradeItem tradeItem = tradeItemMapper.map( dto );
		return tradeItemDao.deleteOne( tradeItem );
	}

}
