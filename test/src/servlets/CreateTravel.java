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

import beans.Member;
import beans.Travel;
import dao.DAOFactory;
import dao.DAOTravel;
import forms.FormValidationException;
import forms.ValidateForm;


/**
 * <b>CreateTravel est la serlet chargée de controler la création d'un nouveau trajet</b>
 * 
 * @see Travel
 * @see DAOFactory
 * @see DAOTravel
 * @see FormValidationException
 * @see ValidateForm
 * 
 * @author Pauline Preel
 * 
 */
@WebServlet("/CreateTravel")
public class CreateTravel extends HttpServlet {
	private static final long   serialVersionUID = 1L;
	 
	public static final String  CONF_DAO_FACTORY = "daofactory";
	public static final String 	VIEW 			 = "/WEB-INF/pages.jsp";
	
	public static final String 	ATT_FORM 		 = "form";
	public static final String 	ATT_TRAVEL 		 = "travel";
    public static final String 	ATT_RESULTAT	 = "result";
    public static final String 	ATT_ERREURS 	 = "errors";

    private String              result 		     = null;
    private Map<String, String> errors 		     = new HashMap<String, String>();
		
    private DAOTravel daoTravel;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTravel() {
        super();
    }
    
    /**
     * @see DAOFactory
     */
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoTravel = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoTravel();
    }

    public String getResult() {
        return result;
    }
        

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute( "title", "Nouveau trajet" );
        request.setAttribute( "content", "travelCreation" );
        /* Affichage de la page de création d'un nouveau trajet */
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ValidateForm form = new ValidateForm(daoTravel);
		HttpSession session = request.getSession();
		Travel travel = null;
		
		try {
			travel = form.checkForm(request);
		} catch (FormValidationException e) {
			e.printStackTrace();
		}

	    session.setAttribute(ATT_TRAVEL, travel);
		request.setAttribute(ATT_RESULTAT, result);
		
		if (form.getErrors().isEmpty()) {
			request.setAttribute( "title", "Détails du trajet" );
	        request.setAttribute( "content", "detailsTravel" );
	        /* Affichage de la page de détails d'un trajet */
			getServletContext().getRequestDispatcher( VIEW ).forward(request,response);
		}
		else {
			request.setAttribute(ATT_TRAVEL, null);
			request.setAttribute( "title", "Nouveau trajet" );
	        request.setAttribute( "content", "travelCreation" );
			request.setAttribute(ATT_ERREURS, errors);
			/* Affichage de la page de création d'un trajet */
			getServletContext().getRequestDispatcher( VIEW ).forward(request,response);			
		}		
	}
}
