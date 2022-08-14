package agjs.dao;

import java.io.Serializable;
import java.util.List;

public interface CoreDao<T, I> {

	Serializable insert(T beanPo);

	int deleteById(T beanPo);

	int update(T beanPo);

	T select(I id);

	List<T> select(I[] idList);

	List<T> select();

}
