package mb.minecraft.service.impl;

import java.util.ArrayList;
import java.util.List;
import mb.minecraft.dao.ItemDao;
import mb.minecraft.dto.ItemDto;
import mb.minecraft.mapper.ItemMapper;
import mb.minecraft.model.Item;
import mb.minecraft.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Item item = itemDao.insertOne( Item.builder()
				  .id( itemDao.getNextIdSeq() )
				  .name( dto.getName() )
				  .build() );
		return ItemMapper.map( item );
	}

	@Override
	public ItemDto saveItem( ItemDto dto ) {
		Item item = ItemMapper.map( dto );
		return ItemMapper.map( itemDao.update( item ) );
	}

	@Override
	public List<ItemDto> retrieveAllItems() {
		List<Item> items = itemDao.selectAll();
		List<ItemDto> dtoList = new ArrayList<>();
		for( Item i : items ) {
			dtoList.add( ItemMapper.map( i ) );
		}
		return dtoList;
	}

	@Override
	public Boolean removeItem( ItemDto dto ) {
		Item item = ItemMapper.map( dto );
		return itemDao.deleteOne( item );
	}

	@Override
	public ItemDto updateItem( ItemDto item ) {
		// TODO implement new service method
		throw new UnsupportedOperationException( "Not supported yet." );
	}

}
