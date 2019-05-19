<form method="post" action="detailsTravel">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.Travel" %>
    <fieldset>
        <legend>Details trajet</legend>
        <p>Informations sur le trajet</p>
		<ul>
			<li>
		<!-- Ville de départ -->
				<p>Ville de départ: ${ city.name }</p>
			</li>
			<li>
		<!-- Ville d'arrivée -->
				<p>Ville d'arrivée: ${ city.name }</p>
			</li>
			<li>
 		<!-- Date et heure de départ --> 
		 		<p>Date de départ: </p>		        
			</li>
			<li>
		 		<p>Heure de départ: </p>		        
			</li>
			<li>
		<!-- Date et heure d'arrivée -->
		 		<p>Date d'arrivée: </p> 		        
			</li>
			<li>
		 		<p>Heure d'arrivée: </p>		        
			</li>
			<li>
		<!-- Durée estimée -->
		        <p>Durée du trajet: <sub>(en minute)</sub>${ sessionScope.sessionTravel.length }</p>
			</li>
			<li>	
		<!-- Nombre de passagers maximum -->
		        <p>Nombre de places disponibles: ${ sessionScope.sessionTravel.seat_max }</p>
			</li>
			<li>
		<!-- Nombre de bagages au total -->
		        <p>Nombre de bagages au total: ${ sessionScope.sessionTravel.luggage_max }</p>
			</li>
		</ul>
	</fieldset>
</form>