package ar.com.ada.maven.model.DAO;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO <T> {

    Collection<T> findAll() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    T findBId(Integer id);

    Boolean save( T t);
    Boolean update( T t, Integer id);
    Boolean delete (Integer id);

}
