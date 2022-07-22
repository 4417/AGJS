package agjs.dao;

import java.sql.SQLException;
import java.util.List;

public interface RoomStyleIn<T> {
	
	List<T> getAll()  throws SQLException;

}
