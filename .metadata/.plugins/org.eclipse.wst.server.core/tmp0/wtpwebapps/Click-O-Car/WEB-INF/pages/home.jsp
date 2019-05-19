<%@ page import="java.util.List"%>
<%@ page import="beans.Travel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<caption>Derniers trajets propos�s</caption>
	<thead>
		<tr>
			<th>Date</th>
			<th>Heure de d�part</th>
			<th>D�part</th>
			<th>Arriv�e</th>
			<th>D�tails</th>
		</tr>
	</thead>
	<tbody>
	<c:set var = "currentStep" scope = "page" value = "-1"/>
	<!-- 
      	   Ce n'est pas coh�rent mais c'est surtout pour avoir la preuve
           que je communique avec la base de donn�e et que j'ai assimil� 
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
						<button>D�tail</button>
					</a>
				</td>
			</tr>
		<c:set var="currentStep" value="${travel.id_travel}" scope="page"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
