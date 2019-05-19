package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <b>La classe DAOFactory est chargée de l'instanciation des différents DAO</b>
 * 
 * @see InitDAOFactory
 * @author Gaëtan Cheval
 *
 */
public class DAOFactory {

	private static final String PROPERTIES_FILE   = "/dao/dao.properties";
	private static final String PROPERTY_URL      = "url";
	private static final String PROPERTY_DRIVER   = "driver";
	private static final String PROPERTY_USER     = "user";
	private static final String PROPERTY_PASSWORD = "passwd";

	private String url;
	private String username;
	private String password;

	private static List<String> messages = new ArrayList<String>();

	DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/*
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String user;
		String password;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (propertiesFile == null) {
			throw new DAOConfigurationException("Le fichier properties " + PROPERTIES_FILE + " est introuvable.");
		}

		try {
			properties.load(propertiesFile);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			user = properties.getProperty(PROPERTY_USER);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible de charger le fichier properties " + PROPERTIES_FILE, e);
		}

		try {
			messages.add("Chargement du driver...");
			Class.forName(driver);
			messages.add("Driver chargé !");
		} catch (ClassNotFoundException e) {
			messages.add("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
					+ e.getMessage());
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
		}

		DAOFactory instance = new DAOFactory(url, user, password);
		return instance;
	}

	/* Méthode chargée de fournir une connexion à la base de données */
	/* package */ Connection getConnection() throws SQLException {
		Connection connexion = null;
		try {
			messages.add("Connexion à la base de données...");
			connexion = DriverManager.getConnection(url, username, password);
			messages.add("Connexion réussie !");
		} catch (SQLException e) {
			messages.add("Erreur lors de la connexion : <br/>" + e.getMessage());
		}
		return connexion;
	}

	public static List<String> getMessages() {
		return messages;
	}

	/*
	 * Méthodes de récupération de l'implémentation des différents DAO 
	 */
	public DAOMember getDaoMember() {
		return new DAOMemberImpl(this);
	}
	public DAOTravel getDaoTravel() {
		return new DAOTravelImpl(this);
	}
}