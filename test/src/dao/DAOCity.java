package dao;

import java.util.ArrayList;

import beans.City;

/**
 * <b>L'interface DAOCity défini les méthodes obligatoires de DAOCityImpl</b>
 * 
 * @see DAO
 * @see DAOCityImpl
 * 
 * @author Vivien
 * 
 * @param <City>
 */

public interface DAOCity extends DAO<City> {
    void create( City city ) throws DAOException;
    City find( String email ) throws DAOException;
    ArrayList<City> findAll() throws DAOException;
}
