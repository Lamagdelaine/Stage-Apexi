package dao;

import java.util.ArrayList;

import beans.Travel;

/**
 * <b>L'interface DAOTravel défini les méthodes obligatoires de DAOTravelImpl</b>
 * 
 * @see DAO
 * @see DAOTravelImpl
 * 
 * @author Vivien
 * 
 * @param <Travel>
 */

public interface DAOTravel extends DAO<Travel> {
	void create( Travel travel) throws DAOException;
	Travel find( String name ) throws DAOException;
	ArrayList<Travel> findAll() throws DAOException;
}
