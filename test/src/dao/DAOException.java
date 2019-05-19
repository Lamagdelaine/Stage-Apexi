package dao;

/**
 * <b>La classe DAOException contrôle et renvoie les messages d'erreurs adéquats</b>
 * 
 * @author 
 *
 */
public class DAOException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
     * Constructeurs
     */
    public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}
