package servlets;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Travel;
import dao.*;
import forms.FormValidationException;
import forms.ValidateForm;

/**
 * <b>La servlet DetailTravel gère la page de description d'un trajet</b>
 * 
 * @author Pauline Preel
 * 
 */
@WebServlet("/DetailTravel")
public class DetailTravel extends HttpServlet {
	private static final long serialVersionUID  = 1L;
	
	public static final String ATT_SESSION_TRAVEL = "sessionTravel";
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VIEW			    = "/WEB-INF/pages.jsp";
	
	public static final String ATT_TRAVEL 	    = "travel";
	public static final String 	ATT_RESULTAT	 = "result";
    public static final String 	ATT_ERREURS 	 = "errors";

	public static final String length 		    = null;
	public static final String seat_max 	    = null;
	public static final String luggage_max 	    = null;
	
	private String              result 		     = null;
    private Map<String, String> errors 		     = new HashMap<String, String>();

    private DAOTravel daoTravel;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailTravel() {
        super();
    }
    /**
     * @see DAOFactory
     */
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoTravel = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoTravel();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute( "title", "Détails du trajet" );
		request.setAttribute( "content", "detailsTravel" );
		/* Affichage de la page de détails des trajets */
		this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
						
		HttpSession session = request.getSession();
		if (session.getAttribute(ATT_SESSION_TRAVEL) != null) {
			request.setAttribute( "title", "Détail du trajet" );
	        request.setAttribute( "content", "detailsTravel" );
			request.setAttribute(ATT_ERREURS, errors);
			/* Affichage de la page de détails d'un trajet */
			getServletContext().getRequestDispatcher( VIEW ).forward(request,response);
		}else {
			request.setAttribute( "title", "Acceuil" );
	        request.setAttribute( "content", "home" );
	        /* Affichage de la page acceuil */
			getServletContext().getRequestDispatcher(VIEW).forward(request,response);
		}
	}
}
