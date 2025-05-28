package mb.minecraft.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mb.minecraft.dao.TradeDao;
import mb.minecraft.dto.TradeDto;
import mb.minecraft.dto.VillagerDto;
import mb.minecraft.mapper.TradeMapper;
import mb.minecraft.mapper.VillagerMapper;
import mb.minecraft.model.Trade;
import mb.minecraft.service.TradeService;

/**
 *
 * @author mikebro
 */
@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private TradeMapper tradeMapper;

	@Autowired
	private VillagerMapper villagerMapper;

	private TradeServiceImpl() {}


	@Override
	public TradeDto retrieveTrade( Long id ) {
		return tradeMapper.map( tradeDao.selectOneById( id ) );
	}

	@Override
	public TradeDto createNewTrade( TradeDto dto ) {
		Trade trade = tradeDao.insertOne( tradeMapper.map( dto ) );
		return tradeMapper.map( trade );
	}

	@Override
	public List<TradeDto> createNewTrades( List<TradeDto> dtoList ) {
		List<Trade> trades = dtoList.stream()
				.map( t -> tradeMapper.map( t ) )
				.collect( Collectors.toList() );
		List<Trade> result = tradeDao.insert( trades );
		return result.stream()
				.map( t -> tradeMapper.map( t ) )
				.collect( Collectors.toList() );
	}

	@Override
	public List<TradeDto> retrieveAllTrades() {
		List<Trade> trades = tradeDao.selectAll();
		List<TradeDto> dtoList = trades.stream()
				.map( t -> tradeMapper.map( t ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public List<TradeDto> retrieveAllTrades( VillagerDto villager ) {
		List<Trade> trades = tradeDao.selectAll( villagerMapper.map( villager ) );
		List<TradeDto> dtoList = trades.stream()
				.map( t -> tradeMapper.map( t ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public TradeDto saveTrade( TradeDto dto ) {
		Trade trade = tradeMapper.map( dto );
		return tradeMapper.map( tradeDao.update( trade ) );
	}

	@Override
	public boolean removeTrade( TradeDto dto ) {
		Trade trade = tradeMapper.map( dto );
		return tradeDao.deleteOne( trade );
	}

}
