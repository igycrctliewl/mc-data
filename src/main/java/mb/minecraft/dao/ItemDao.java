package mb.minecraft.dao;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;

import mb.minecraft.model.Item;

/**
 *
 * @author mikebro
 */
public interface ItemDao extends DisposableBean {
	public Item selectOneById( Long id );
	public Item selectOneByName( String name );
	public List<Item> selectAll();
	public Item insertOne( Item item );
	public Item update( Item item );

	/* why would anything outside the dao class need this service? */
	public Long getNextIdSeq();

	public boolean deleteOne( Item item );
}
