package dao;

import static dao.DAOUtilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Member;

/**
 * <b>La classe DAOMemberImpl se connecte à la base de données</b>
 * <p>
 * Cette classe se charge d'accéder à la base de données pour récupérer les informations demandées
 * </p>
 * @see DAO
 * @see DAOMember
 * 
 * @author 
 *
 */
public class DAOMemberImpl implements DAOMember {
	private static final String SQL_SELECT_BY_EMAIL	    = "SELECT id_member, email, password, phone FROM member WHERE email = ?";
	private static final String SQL_SELECT_BY_CONNECT	= "SELECT id_member, email, password, phone FROM member WHERE email = ? AND password = ?";
    private static final String SQL_INSERT              = "INSERT INTO `member` (`email`, `password`, `phone`) VALUES (?, ?, ?);";
	/**
	 * instance de la DAOFactory
	 */
	private DAOFactory daoFactory;

	/**
	 * constructeur
	 * 
	 * @param daoFactory
	 */
	DAOMemberImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    @Override
    public Member find( String email ) throws DAOException {
        return find( SQL_SELECT_BY_EMAIL, email );
    }
    
    @Override
    public Member find2connect( String email, String password ) throws DAOException {
    	Connection connexion;
    	ResultSet resultSet = null;
    	Member member = null;
		try {
			connexion = this.daoFactory.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(SQL_SELECT_BY_CONNECT);
			preparedStatement.setObject(1, email);
			preparedStatement.setObject(2, password);
			resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                member = map( resultSet );
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return member;
    }
    /*
     * Méthode générique utilisée pour retourner un utilisateur depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramÃ¨tres
     * les objets passés en argument.
     */
	private Member find(String sql, Object... objects) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Member member = null;

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
                member = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentCloses( resultSet, preparedStatement, connexion );
        }

        return member;
	}

	/**
	 * créer un membre
	 */
	@Override
	public void create(Member member) throws IllegalArgumentException, DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedValues = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initPrepQuery( connexion, SQL_INSERT, true, member.getEmail(), member.getPassword(), member.getPhone() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Echec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            generatedValues = preparedStatement.getGeneratedKeys();
            if ( generatedValues.next() ) {
                member.setId_member( generatedValues.getLong( 1 ) );
            } else {
                throw new DAOException( "Echec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentCloses( generatedValues, preparedStatement, connexion );
        }
	}

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static Member map( ResultSet resultSet ) throws SQLException {
        Member member = new Member();
        member.setId_member( resultSet.getLong( "id_member" ) );
        member.setEmail( resultSet.getString( "email" ) );
        member.setPassword( resultSet.getString( "password" ) );
        member.setPhone( resultSet.getString( "phone" ) );
        return member;
    }

	@Override
	public ArrayList<Member> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
