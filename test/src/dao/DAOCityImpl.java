package dao;

import static dao.DAOUtilities.initPrepQuery;
import static dao.DAOUtilities.silentCloses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.City;

/**
 * <b>La classe DAOCityImpl se connecte à la base de données</b>
 * <p>
 * Cette classe est chargée de récupérer les données voulues
 * </p>
 * 
 * @see DAO
 * @see DAOCity
 * @author 
 * 
 */
public class DAOCityImpl implements DAOCity {
	private static final String SQL_DEFAULT	= "SELECT id_city, name, cp, lat, lon FROM city";
	/**
	 * instance de la DAOFactory
	 */
	private DAOFactory daoFactory;

	public DAOCityImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(City city) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City find(String cityName) throws DAOException {
		return find(SQL_DEFAULT+" WHERE name = ?", cityName);
	}
	
	private City find (String sql, Object... objects) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        City city = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initPrepQuery( connexion, sql, true, objects );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                city = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentCloses( resultSet, preparedStatement, connexion );
        }

		return city;
	}
	
    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static City map( ResultSet resultSet ) throws SQLException {
        City city = new City();
        city.setId_city(resultSet.getLong("id_city"));
        city.setName(resultSet.getString("name"));
        city.setPostcode(resultSet.getString("cp"));
        city.setLat(resultSet.getString("lat"));
        city.setLon(resultSet.getString("lon"));
        return city;
    }

	@Override
	public ArrayList<City> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
