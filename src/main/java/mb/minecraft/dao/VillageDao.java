package mb.minecraft.dao;

import java.util.List;
import mb.minecraft.model.Village;
import org.springframework.beans.factory.DisposableBean;

/**
 *
 * @author mikebro
 */
public interface VillageDao extends DisposableBean {
	public Village selectOneById( Long id );
	public Village selectOneByName( String name );
	public List<Village> selectAll();
	public Village insertOne( Village village );
	public Long getNextIdSeq();
	public Boolean deleteOne( Village village );
}
