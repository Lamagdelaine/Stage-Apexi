<form method="post" action="detailsTravel">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="beans.Travel" %>
    <fieldset>
        <legend>Details trajet</legend>
        <p>Informations sur le trajet</p>
		<ul>
			<li>
		<!-- Ville de départ -->
				<p>Ville de départ: ${ sessionScope.sessionTravel.firstCity }</p>
			</li>
			<li>
		<!-- Ville d'arrivée -->
				<p>Ville d'arrivée: ${ sessionScope.sessionTravel.lastCity }</p>
			</li>
			<li>
 		<!-- Date et heure de départ --> 
		 		<p>Date de départ: ${ sessionScope.sessionTravel.departureDate }</p>		        
			</li>
			<li>
		 		<p>Heure de départ: ${ sessionScope.sessionTravel.departureHour }</p>		        
			</li>
			<li>
		<!-- Date et heure d'arrivée -->
		 		<p>Date d'arrivée: ${ sessionScope.sessionTravel.departureDate } + ${ sessionScope.sessionTravel.length / 60 }h</p>
			</li>
			<li>
		 		<p>Heure d'arrivée: ${ sessionScope.sessionTravel.departureHour } + ${ sessionScope.sessionTravel.length / 60 }h</p>		        
			</li>
			<li>
		<!-- Durée estimée -->
		        <p>Durée du trajet: ${ sessionScope.sessionTravel.length / 60 }h</p>
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