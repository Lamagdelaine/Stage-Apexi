<%@ page import="java.util.List"%>
<%@ page import="beans.Travel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<caption>Derniers trajets proposés</caption>
	<thead>
		<tr>
			<th>Date</th>
			<th>Heure de départ</th>
			<th>Départ</th>
			<th>Arrivée</th>
			<th>Détails</th>
		</tr>
	</thead>
	<tbody>
	<c:set var = "currentStep" scope = "page" value = "-1"/>
	<!-- 
      	   Ce n'est pas cohérent mais c'est surtout pour avoir la preuve
           que je communique avec la base de donnée et que j'ai assimilé 
           le concept
           -->
	<c:forEach items="${listeTrajets}" var="travel">
		<c:if test="${ currentStep != travel.id_travel }">
			<tr>
				<td> ${travel.departureDate }</td>
				<td>${travel.departureHour }</td>
				<td>${travel.firstCity }</td>
				<td>${travel.lastCity }</td>
				<td>
					<a href="details-voyage/${ travel.id_travel }">
						<button>Détail</button>
					</a>
				</td>
			</tr>
		<c:set var="currentStep" value="${travel.id_travel}" scope="page"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
