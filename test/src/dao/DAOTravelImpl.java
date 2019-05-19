package dao;

import static dao.DAOUtilities.initPrepQuery;
import static dao.DAOUtilities.silentCloses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Travel;

/**
 * <b>La classe DAOTravelImpl se connecte à la base de données</b>
 * <p>
 * Cette classe se charge de récupérer, dans la base, les données voulues
 * </p>
 * @see DAO
 * @see DAOTravel
 * 
 * @author 
 *
 */
public class DAOTravelImpl implements DAOTravel {
	private static final String SQL_DEFAULT	= "SELECT T.id_travel, T.length, T.seat_max, T.luggage_max, C.name, "
								   + "DT.schedule, M.lastname, M.firstname "
								   + "FROM travel T "
								   + "INNER JOIN drives_to DT ON DT.id_travel=T.id_travel "
								   + "INNER JOIN city C ON DT.id_city=C.id_city"
								   + "INNER JOIN drives_with DW ON T.id_travel = DW.id_travel "
								   + "INNER JOIN member M ON DW.id_member = M.id_member ";

	/**
	 * 
	 * Requêtes SQL pour création et suppresion dans la BD
	 * 
	 */
	private static final String SQL_INSERT = "INSERT INTO travel ( length, seat_max, luggage_max) "
										   + "VALUES (?, ?, ?, NOW())";
	private static final String SQL_DELETE = "DELETE FROM travel WHERE id_travel = ?";
	private static final String SQL_SELECT = "SELECT  " + 
			"    T.id_travel, " + 
			"    T.length, " + 
			"    T.seat_max, " + 
			"    T.luggage_max, " + 
			"    C.name, " + 
			"    DT.schedule, " + 
			"    M.lastname, " + 
			"    M.firstname, " + 
			"    DW.seat " + 
			"FROM " + 
			"    travel T " + 
			"        INNER JOIN " + 
			"    drives_to DT ON DT.id_travel = T.id_travel " + 
			"        INNER JOIN " + 
			"    city C ON DT.id_city = C.id_city " + 
			"        INNER JOIN " + 
			"    drives_with DW ON T.id_travel = DW.id_travel " + 
			"        INNER JOIN " + 
			"    member M ON DW.id_member = M.id_member " + 
			"WHERE " + 
			"    DW.seat = 1 " + 
			"ORDER BY DT.schedule, DW.seat";
	/**
	 * instance de la DAOFactory
	 */
	private DAOFactory daoFactory;
	
	DAOTravelImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * création de la fonction de création d'un trajet
	 * @param t
	 * @throws SQLException
	 */
	@Override
	public void create(Travel t) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedValues = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initPrepQuery( connexion, SQL_INSERT, true, t.getLength(), t.getLuggage_max(), t.getSeat_max() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Echec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            generatedValues = preparedStatement.getGeneratedKeys();
            if ( generatedValues.next() ) {
                t.setId_travel( generatedValues.getLong( 1 ) );
            } else {
                throw new DAOException( "Echec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentCloses( generatedValues, preparedStatement, connexion );
        }
	}
	
	@Override
	public Travel find(String cityName) throws DAOException {
		return find(SQL_DEFAULT+" WHERE C.name = ?", cityName);
	}

	private Travel find (String sql, Object... objects) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Travel travel = null;
        
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
            	travel = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentCloses( resultSet, preparedStatement, connexion );
        }

		return travel;
	}

	/**
	 * création de la fonction de suppression d'un trajet
	 * @param t
	 * @throws SQLException
	 */
	public void delete(Travel t) throws SQLException {

		Connection connect = null;
		PreparedStatement pstmt = null;

		try {
			connect = daoFactory.getConnection();
			pstmt = initPrepQuery( connect, SQL_DELETE, false, t.getId_travel() );
		} catch ( SQLException e) {
			throw new DAOException( e );
		} finally {
            silentCloses( pstmt, connect );
        }
	}

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static Travel map( ResultSet resultSet ) throws SQLException {
    	Travel travel = new Travel();
        travel.setId_travel(resultSet.getLong("id_travel"));
        travel.setLength(resultSet.getLong("length"));
        travel.setLuggage_max(resultSet.getLong("luggage_max"));
        travel.setSeat_max(resultSet.getLong("seat_max"));
        
        return travel;
    }

	/**
	 * création d'une liste de trajet
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Travel> findAll() throws DAOException {

		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Travel> travel = new ArrayList<Travel>();

		try {
			connect = daoFactory.getConnection();
			pstmt = connect.prepareStatement(SQL_SELECT);
			rs = pstmt.executeQuery();
			int count = rs.getFetchSize();
			if (rs != null) {
				int i = 0;
				int j = 0;
				Long id = (long) -1;
				while (rs.next()) {
					Travel t = new Travel();
					System.out.println(travel.size()+" || "+count+"### "+id+" "+i+" "+j+" "+rs.getString("name"));
					t.setId_travel(rs.getLong("id_travel"));
					t.setDeparture(rs.getString("schedule"));
					t.setFirstCity(rs.getString("name"));
					t.setLastCity(rs.getString("name"));
					t.addSteps(rs.getString("name"), rs.getString("schedule"));
					t.setLength(rs.getLong("length"));
					t.setLuggage_max(rs.getLong("luggage_max"));
					t.setSeat_max(rs.getLong("seat_max"));
					if (id != rs.getLong("id_travel") || travel.size()+1 == rs.getFetchSize()) {
						if (id != (long) -1) {
							System.out.println(id+" "+i+" "+j+" "+rs.getString("name")+" || dernier : "+
										travel.get(travel.size()-j).getFirstCity()+" "+
										travel.get(travel.size()-j).getLastCity());
							String lastCityName = travel.get(travel.size()-j+1).getLastCity();
							travel.get(travel.size()-j).setLastCity(lastCityName);
							j=0;
						}
						id = rs.getLong("id_travel");
					} else {
//						travel.add(t);
					}
					if (id == rs.getLong("id_travel")) {
						//travel.get((int) (travel.size()-1)).setLastCity(lastCityName);
					}
					travel.add(t);
					j++;
					i++;
				}
			}
		}catch ( SQLException e) {
			throw new DAOException( e );
		} finally {
            silentCloses( rs, pstmt, connect );
        }
		return travel;
	}

}
