package mb.minecraft.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mb.minecraft.dao.ItemDao;
import mb.minecraft.dto.ItemDto;
import mb.minecraft.model.Item;
import mb.minecraft.service.impl.ItemServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

	@InjectMocks
	ItemServiceImpl itemService;

	@Mock
	ItemDao itemDao;


	@Test
	public void testRetrieveItemById() {
		when( itemDao.selectOneById( anyInt() )).thenReturn( prepareOneItem() );
		ItemDto item = itemService.retrieveItem( 1 );
		assertNotNull( item );
		assertEquals( 901, item.getId().intValue() );
		assertEquals( "Redstone Dust", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Redstone_Dust_JE2_BE2.png", item.getImageSource() );
	}

	@Test
	public void testRetrieveItemByName() {
		when( itemDao.selectOneByName( any() )).thenReturn( prepareOneItem() );
		ItemDto item = itemService.retrieveItem( "name" );
		assertNotNull( item );
		assertEquals( 901, item.getId().intValue() );
		assertEquals( "Redstone Dust", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Redstone_Dust_JE2_BE2.png", item.getImageSource() );
	}

	@Test
	public void testRetrieveAllItems() {
		when( itemDao.selectAll() ).thenReturn( prepareItemList() );
		List<ItemDto> items = itemService.retrieveAllItems();
		assertNotNull( items );
		assertEquals( 3, items.size() );

		ItemDto v1 = items.stream()
				.filter( v -> v.getName().equals( "Raw Beef" ) )
				.findFirst()
				.get();
		assertEquals( 991, v1.getId().intValue() );
		assertEquals( "Raw Beef", v1.getName() );

		ItemDto v2 = items.stream()
				.filter( v -> v.getName().equals( "Bottle o' Enchanting" ) )
				.findFirst()
				.get();
		assertEquals( 993, v2.getId().intValue() );
		assertEquals( "Bottle o' Enchanting", v2.getName() );
	}

	@Test
	public void testFindOrCreateExisting() {
		when( itemDao.selectOneByName( any() )).thenReturn( prepareOneItem() );
		ItemDto item = itemService.findOrCreateItem( "name" );
		assertNotNull( item );
		assertEquals( 901, item.getId().intValue() );
		assertEquals( "Redstone Dust", item.getName() );
	}

	@Test
	public void testFindOrCreateNew() {
		when( itemDao.selectOneByName( any() )).thenReturn( null );
		when( itemDao.insertOne( any( Item.class ) )).thenAnswer( i -> {
			Item item = i.getArgument(0);
			item.setId( 905 );
			return item;
		});
		ItemDto newItem = itemService.findOrCreateItem( "Emerald" );
		assertNotNull( newItem );
		assertEquals( 905, newItem.getId().intValue() );
		assertEquals( "Emerald", newItem.getName() );
	}

	@Test
	public void testCreateNewItem() {
		when( itemDao.insertOne( any( Item.class ) )).thenAnswer( i -> {
			Item item = i.getArgument(0);
			item.setId( 987 );
			return item;
		});
		ItemDto newItem = ItemDto.builder()
				.name( "Block of Emerald" )
				.imageSource( "https://minecraft.wiki/images/Block_of_Emerald_JE4_BE3.png" )
				.build();
		ItemDto item = itemService.createNewItem( newItem );
		assertNotNull( item );
		assertEquals( 987, item.getId().intValue() );
		assertEquals( "Block of Emerald", item.getName() );
		assertEquals( "https://minecraft.wiki/images/Block_of_Emerald_JE4_BE3.png", item.getImageSource() );
		assertNotNull( item.getImage() );
	}

	@Test
	public void testDeleteItemFail() {
		when( itemDao.deleteOne( any( Item.class ) )).thenReturn( false );
		ItemDto deleteItem = ItemDto.builder()
				.name( "Dirt" )
				.build();
		boolean itemDeleted = itemService.removeItem( deleteItem );
		assertFalse( itemDeleted );
	}

	@Test
	public void testDeleteItemSuccess() {
		when( itemDao.deleteOne( any( Item.class ) )).thenReturn( true );
		ItemDto deleteItem= ItemDto.builder()
				.name( "Diamond" )
				.build();
		boolean itemDeleted = itemService.removeItem( deleteItem );
		assertTrue( itemDeleted );
	}

	@Test
	public void testSaveUpdateItem() {
		when( itemDao.insertOne( any( Item.class ) )).thenAnswer( i -> {
			Item item = i.getArgument(0);
			item.setId( 1001 );
			return item;
		});
		when( itemDao.update( any( Item.class ) ) ).thenAnswer( i -> i.getArgument(0) );

		//create item without imagesource
		ItemDto item = itemService.createNewItem( ItemDto.builder()
				.name( "Diamond" )
				.build() );
		assertNull( item.getImageSource() );

		//add image source to item and save
		item.setImageSource( "https://minecraft.wiki/images/Diamond_JE3_BE3.png" );

		ItemDto finalItem = itemService.saveItem( item );
		assertNotNull( finalItem.getImageSource() );
		assertNotNull( finalItem.getImage() );
	}



	private static Item prepareOneItem() {
		Item i = buildItem( 901, "Redstone Dust", "https://minecraft.wiki/images/Redstone_Dust_JE2_BE2.png" );
		return i;
	}

	private static List<Item> prepareItemList() {
		List<Item> list = Arrays.asList(
				buildItem( 991, "Raw Beef", null ),
				buildItem( 992, "Gold Ingot", null ),
				buildItem( 993, "Bottle o' Enchanting", null ) );
		return list;
	}

	private static Item buildItem( int id, String name, String imageSource ) {
		return Item.builder()
				.id( id )
				.name( name )
				.imageSource( imageSource )
				.build();
	}

}
