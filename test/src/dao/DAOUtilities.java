package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b></b>
 * 
 * @see DAO
 * 
 * @author 
 * 
 */
public class DAOUtilities {

	/*
	 * Constructeur caché par défaut (car c'est une classe finale utilitaire,
	 * contenant uniquement des méthode appelées de manière statique)
	 */
	private DAOUtilities() {
	}

	/* Fermeture silencieuse du resultset */
	public static void silentClose(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Echec de la fermeture du ResultSet : " + e.getMessage());
			}
		}
	}

	/* Fermeture silencieuse du statement */
	public static void silentClose(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println("Echec de la fermeture du Statement : " + e.getMessage());
			}
		}
	}

	/* Fermeture silencieuse de la connexion */
	public static void silentClose(Connection connexion) {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("Echec de la fermeture de la connexion : " + e.getMessage());
			}
		}
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public static void silentCloses(Statement statement, Connection connexion) {
		silentClose(statement);
		silentClose(connexion);
	}

	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void silentCloses(ResultSet resultSet, Statement statement, Connection connexion) {
		silentClose(resultSet);
		silentClose(statement);
		silentClose(connexion);
	}

	/*
	 * Initialise la requête préparée basée sur la connexion passée en argument,
	 * avec la requête SQL et les objets donnés.
	 */
	public static PreparedStatement initPrepQuery(Connection connexion, String sql, boolean returnGeneratedKeys,
			Object... objets) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < objets.length; i++) {
			preparedStatement.setObject(i + 1, objets[i]);
		}
		return preparedStatement;
	}
}
