package mb.minecraft.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mb.minecraft.dao.ItemDao;
import mb.minecraft.dto.ItemDto;
import mb.minecraft.mapper.ItemMapper;
import mb.minecraft.model.Item;
import mb.minecraft.service.ItemService;


/**
 *
 * @author mikebro
 */
@Service
public class ItemServiceImpl implements ItemService {


	@Autowired
	private ItemDao itemDao;

	private ItemServiceImpl() {}


	@Override
	public ItemDto retrieveItem( Long id ) {
		ItemDto dto = ItemMapper.map( itemDao.selectOneById( id ) );
		return dto;
	}

	@Override
	public ItemDto retrieveItem( String name ) {
		ItemDto dto = ItemMapper.map( itemDao.selectOneByName( name ) );
		return dto;
	}

	@Override
	public ItemDto findOrCreateItem( String name ) {
		ItemDto lookupItem = this.retrieveItem( name );
		if( lookupItem == null ) {
			lookupItem = ItemDto.builder()
					.name( name )
					.build();
			lookupItem = this.createNewItem( lookupItem );
		}
		return lookupItem;
	}

	@Override
	public ItemDto createNewItem( ItemDto dto ) {
		Item newItem = ItemMapper.map( dto );
		Item item = itemDao.insertOne( newItem );
		return ItemMapper.map( item );
	}

	@Override
	public ItemDto saveItem( ItemDto dto ) {
		Item item = ItemMapper.map( dto );
		return ItemMapper.map( itemDao.update( item ) );
	}

	@Override
	public List<ItemDto> retrieveAllItems() {
		List<ItemDto> dtoList = itemDao.selectAll().stream() 
				.map( i -> ItemMapper.map( i ) )
				.collect( Collectors.toList() );
		return dtoList;
	}

	@Override
	public boolean removeItem( ItemDto dto ) {
		Item item = ItemMapper.map( dto );
		return itemDao.deleteOne( item );
	}

}
