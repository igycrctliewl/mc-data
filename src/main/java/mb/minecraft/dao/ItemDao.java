package mb.minecraft.dao;

import java.util.List;
import mb.minecraft.model.Item;
import org.springframework.beans.factory.DisposableBean;

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
	public Long getNextIdSeq();
	public Boolean deleteOne( Item item );
}
