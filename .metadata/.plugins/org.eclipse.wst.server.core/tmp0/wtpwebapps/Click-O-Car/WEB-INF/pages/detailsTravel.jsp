<form method="post" action="detailsTravel">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="beans.Travel" %>
    <fieldset>
        <legend>Details trajet</legend>
        <p>Informations sur le trajet</p>
		<ul>
			<li>
		<!-- Ville de d�part -->
				<p>Ville de d�part: ${ sessionScope.sessionTravel.firstCity }</p>
			</li>
			<li>
		<!-- Ville d'arriv�e -->
				<p>Ville d'arriv�e: ${ sessionScope.sessionTravel.lastCity }</p>
			</li>
			<li>
 		<!-- Date et heure de d�part --> 
		 		<p>Date de d�part: ${ sessionScope.sessionTravel.departureDate }</p>		        
			</li>
			<li>
		 		<p>Heure de d�part: ${ sessionScope.sessionTravel.departureHour }</p>		        
			</li>
			<li>
		<!-- Date et heure d'arriv�e -->
		 		<p>Date d'arriv�e: ${ sessionScope.sessionTravel.departureDate } + ${ sessionScope.sessionTravel.length / 60 }h</p>
			</li>
			<li>
		 		<p>Heure d'arriv�e: ${ sessionScope.sessionTravel.departureHour } + ${ sessionScope.sessionTravel.length / 60 }h</p>		        
			</li>
			<li>
		<!-- Dur�e estim�e -->
		        <p>Dur�e du trajet: ${ sessionScope.sessionTravel.length / 60 }h</p>
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