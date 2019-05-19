package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Member;
import dao.DAOFactory;
import dao.DAOTravel;
import forms.ValidateForm;

/**
 * <b>Home est la servlet chargée d'acueillir et de redirigé les utilisateurs</b>
 * 
 * <p>
 * Son affichage dépends de l'état connecté ou non de l'utilisateur
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID  = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_TRAVEL       = "travel";
	public static final String ATT_LISTE_TRAVEL = null;
	public static final String VIEW             = "/WEB-INF/pages.jsp";
	public static final String ATT_FORM         = "form";
    public static final String ATT_MESSAGES     = "messages";
    
    public static final String ID_TRAVEL        = null;
    public static final String LENGTH           = null;
    public static final String SEAT_MAX         = null;
    public static final String LUGGAGE_MAX      = null;
    
    private DAOTravel daoTravel;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }
    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoTravel = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoTravel();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> messages = DAOFactory.getMessages();
        request.setAttribute( ATT_MESSAGES, messages );
        
        request.setAttribute("listeTrajets", daoTravel.findAll());
		request.setAttribute( "title", "Bienvenu-e-s sur Click-o-Car" );
		request.setAttribute("content", "home");
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
