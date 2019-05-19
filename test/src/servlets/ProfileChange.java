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
import dao.DAOFactory;
import dao.DAOMember;
import forms.FormValidationException;
import forms.ValidateForm;


/**
 * <b>La servlet ProfileChange, modifie les informations relative à un membre</b>
 * 
 * @see Member
 * @author Sébastien Calas
 * 
 * Servlet implementation class CreateTravel
 */
@WebServlet("/ProfileChange")
public class ProfileChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String 	VIEW 		= "/WEB-INF/pages.jsp";
	
	public static final String 	ATT_MEMBER 		= "member";
    public static final String 	ATT_RESULTAT	= "result";
    public static final String 	ATT_ERREURS 	= "errors";
	/**
	 * la session utilisateur
	 */
	public static final String ATT_SESSION_USER = "sessionMember";

    private String              result 		= null;
    private Map<String, String> errors 		= new HashMap<String, String>();
		
    private DAOMember daoMember;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileChange() {
        super();
    }
    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoMember = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoMember();
    }

    public String getResult() {
        return result;
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			request.setAttribute( "title", "Modification" );
	        request.setAttribute( "content", "profileChange" );
	        /* Affichage de la page de création d'un nouveau trajet */
	        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
		} else {
	        this.getServletContext().getRequestDispatcher( "/home" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ValidateForm form = new ValidateForm(daoMember);
		
		Member member = null;
		try {
			member = form.checkMemberForm(request);
		} catch (FormValidationException e) {
			e.printStackTrace();
		}

	    request.setAttribute(ATT_MEMBER, member);
		request.setAttribute(ATT_RESULTAT, result);
		
		if (errors.isEmpty()) {
			request.setAttribute( "title", "Information" );
	        request.setAttribute( "content", "profile" );
	        /* Affichage de la page de détails d'un trajet */
			getServletContext().getRequestDispatcher(VIEW).forward(request,response);
		}
		else {
			request.setAttribute( "title", "Modification" );
	        request.setAttribute( "content", "profileChange" );
			request.setAttribute(ATT_ERREURS, errors);
			/* Affichage de la page de création d'un trajet */
			getServletContext().getRequestDispatcher( VIEW ).forward(request,response);			
		}
	}

}
