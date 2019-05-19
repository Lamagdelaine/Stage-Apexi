package dao;

import java.util.ArrayList;

/**
 * <b>Une interface mère générique</b>
 * 
 * @author 
 *
 * @param <T>
 */
public interface DAO<T> {
    void create( T t ) throws DAOException;
    T find( String search ) throws DAOException;
    ArrayList<T> findAll() throws DAOException;
}
