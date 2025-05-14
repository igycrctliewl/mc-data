package mb.minecraft.dao;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;

import mb.minecraft.model.Village;

/**
 *
 * @author mikebro
 */
public interface VillageDao extends DisposableBean {
	public Village selectOneById( Long id );
	public Village selectOneByName( String name );
	public List<Village> selectAll();
	public Village insertOne( Village village );

	/* why would anything outside the dao class need this service? */
	public Long getNextIdSeq();

	public boolean deleteOne( Village village );
}
