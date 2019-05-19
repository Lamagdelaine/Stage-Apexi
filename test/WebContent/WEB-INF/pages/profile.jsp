

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.List" %>
    <%@ page import="beans.Member" %>

        <form method="post" action="profile">
            <fieldset>
                <legend>Information</legend>
                <h3>Information de votre compte</h3>
					<!-- Affichage adresse mail -->
	                <p> Adresse mail  : ${ sessionScope.sessionMember.email}</p> 
	                <!-- Affichage nom -->               
	                <p> Nom :  ${ sessionScope.sessionMember.lastName}</p>
	                <!-- Affichage prénom -->
	                <p> Prenom :  ${ sessionScope.sessionMember.firstName}</p>
	                <!-- Affichage numéro de téléphone -->
	                <p> Numéro de téléphone :  ${ sessionScope.sessionMember.phone}</p>
	                <!-- Affichage date de naissance -->
	                <p> Date de naissance :  ${ sessionScope.sessionMember.birthday}</p>
	                <!-- Affichage numéro de permis -->
	                <p> numéro de permis :  ${ sessionScope.sessionMember.license}</p>
					<a href="profileChange"><input type="button" name="Répondre"value="Modifier"/></a>
            </fieldset>
        </form> 