package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Member;
import dao.DAOFactory;
import dao.DAOMember;
import dao.DAOMemberImpl;
import forms.FormValidationException;
import forms.ValidateForm;

/**
 * <b>Connexion est la servlet identifiant l'utilisateur comme membre</b>
 * <p>
 * Une connexion est caractérisée par les informations suivantes :
 * <ul>
 * <li>la session.</li>
 * <li>l'utilisateur.</li>
 * </ul>
 * </p>
 * <p>
 * Ici, l'importance du bean Member est fondatrice.
 * </p>
 * 
 * @see ValidateForm
 * @see Member
 * @see HttpServlet
 * 
 * @author Gaëtan Cheval
 * @version 2.0
 */
public class Connexion extends HttpServlet {
	/**
	 * numéro de série par défaut
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * le connecteur
	 */
	public static final String CONF_DAO_FACTORY = "daofactory";
	/**
	 * 
	 */
	private DAOMember daoMember;
	/**
	 * la vue
	 */
	public static final String VIEW = "/WEB-INF/pages.jsp";
	/**
	 * le membre
	 */
	public static final String ATT_USER = "utilisateur";
	/**
	 * le formulaire de connexion
	 */
	public static final String ATT_FORM = "form";
	/**
	 * la session utilisateur
	 */
	public static final String ATT_SESSION_USER = "sessionMember";

	/**
	 * constructeur par défaut
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
	}
	/**
	 * initialisation de la connexion du membre
	 * 
	 * @throws ServletException
	 * @see DAOMemberImpl
	 */
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoMember = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoMember();
    }
	/**
	 * accède à la page de connexion
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Connexion");
		request.setAttribute("content", "connexion");
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
	/**
	 * traîte le formulaire de connexion
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*	Préparation de l'objet formulaire	*/
		ValidateForm form = new ValidateForm(daoMember);
		/*	Traitement de la requête et récupération du bean en résultat	*/
		Member member = null;
		try {
			member = form.connectMember(request);
		} catch (FormValidationException e) {
			e.printStackTrace();
		}
		/*	Récupération de la session depuis la requête	*/
		HttpSession session = request.getSession();
		/*	Stockage du formulaire et du bean dans l'objet request	*/
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, member);
		request.setAttribute("title", "Connexion");
		request.setAttribute("content", "connexion");
		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean Utilisateur à
		 * la session, sinon suppression du bean de la session.
		 */
		if (form.getErrors().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, member);
			/*	Affichage de la page de connexion	*/
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
			/*	Affichage de la page du formulaire	*/
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
