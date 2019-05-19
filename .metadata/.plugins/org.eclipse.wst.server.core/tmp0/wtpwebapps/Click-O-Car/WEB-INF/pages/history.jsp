<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.Travel" %>
<table>
  <caption>Historique des trajets</caption>
    <tr>
      <th>Date</th>
      <th>Heure de départ</th>
      <th>Départ</th>
      <th>Arrivée</th>
      <th>Détails</th>
    </tr>
	  <c:forEach items="${listeTrajets}" var="travel">
   		<td> ${travel.id_travel } </td>
   		<td> ${travel.length } </td>
   		<td> ${travel.seat_max } </td>
   		<td> ${travel.luggage_max } </td>
   		<td> <a href=""><input type="button" name="Répondre "value="Détail"/></a>
	  </c:forEach>
</table>

