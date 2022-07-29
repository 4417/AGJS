package agjs.dao;

import java.util.List;

public interface CoreDao<T, I> {

	int insert(T beanPo);

	int deleteById(T beanPo);

	int update(T beanPo);

	T select(I id);

	List<T> select();

}
