package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOMember;
import dao.DAOFactory;

/**
 * <b>La servelt Profile gère l'affichage de la page de profil du membre</b>
 * 
 * @see Member;
 * @author Sébastien Calas
 * 
 */
public class Profile extends HttpServlet {
    private static final long serialVersionUID  = 1L;
	/**
	 * la session utilisateur
	 */
	public static final String ATT_SESSION_USER = "sessionMember";

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_MEMBER            = "member";
    public static final String VIEW             = "/WEB-INF/pages.jsp";
    
    private DAOMember daoMember;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        
        
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoMember = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoMember();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			/*
			 * Récupération des données saisies, envoyées en tant que paramètres de * la
			 * requête GET générée à la validation du formulaire
			 */
			request.setAttribute("title", "Information membre");
			request.setAttribute("content", "profile");
			/* Transmission à la page JSP en charge de l'affichage des données */
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	        
		}
	}

