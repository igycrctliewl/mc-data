package mb.minecraft.service;

import java.util.List;
import mb.minecraft.dto.ItemDto;

/**
 * Classes interacting with this service will be expected to use the DTO class
 * exclusively.  The entity class will only be used within this service and the
 * corresponding DAO.
 * @author mikebro
 */
public interface ItemService {

	public ItemDto retrieveItem( Long id );
	public ItemDto retrieveItem( String name );
	public ItemDto findOrCreateItem( String name );
	public ItemDto createNewItem( ItemDto item );
	public ItemDto saveItem( ItemDto item );
	public List<ItemDto> retrieveAllItems();
	public ItemDto updateItem( ItemDto item );
	public Boolean removeItem( ItemDto item );
}
