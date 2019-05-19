package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Member;
import dao.DAOFactory;
import dao.DAOMember;
import forms.ValidateForm;

/**
 * <b>Registration est la servlet gérant l'inscription d'un membre</b>
 * 
 * 
 * @see Member
 * @see DAOMember
 * @see DAOFactory
 * @author 
 *
 */
public class Registration extends HttpServlet {
	

	private static final long serialVersionUID    = 1L;
	
	public static final String CONF_DAO_FACTORY   = "daofactory";
	public static final String ATT_USER           = "member";
	public static final String ATT_FORM           = "form";
	public static final String ATT_SESSION_USER   = "sessionMember";
	public static final String VIEW               = "/WEB-INF/pages.jsp";
	
	ResultSet generatedValues = null;
	
	private DAOMember daoMember;
	
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.daoMember = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDaoMember();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Inscription");
		request.setAttribute("content", "registration");
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ValidateForm form = new ValidateForm(daoMember);

		if (daoMember.find(request.getParameter("email")) != null) {
			System.out.println("Le membre est dans la base de donnée");
		}
		/* Traitement de la requête et récupération du bean en résultant */
		Member member = form.registerMember(request);
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, member);
		/*	enregistrement réussi	*/
		if (form.getErrors().isEmpty()) {
			request.setAttribute("title", "Connexion");
			request.setAttribute("content", "connexion");
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
		/*	enregistrement refusé	*/
		else {
			request.setAttribute("title", "Inscription");
			request.setAttribute("content", "registration");
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
