    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.List" %>
    <%@ page import="beans.Member" %>
<form method="post" action="profileChange">
            <fieldset>
                <legend>Modification</legend>
                <p>Modification</p>
				<ul>
					<li>
						<!-- Modification adresse mail -->
		                <label for="email">Modification adresse email <span class="requis">*</span></label>
		                <input type="text" id="email" name="email" value="${ sessionScope.sessionMember.email }" size="20" maxlength="60" />
					</li>
					<li>
						<!-- Modification nom -->
		                <label for="LastName">Modification nom</label>
		                <input type="text" id="firstName" name="firstName" value="${ sessionScope.sessionMember.lastName }" size="20" maxlength="20" />
					</li>
					<li>
						<!-- Modification prénom -->
		                <label for="FirstName">Modification Prénom</label>
		                <input type="text" id="lastName" name="lastName" value="${ sessionScope.sessionMember.firstName }" size="20" maxlength="20" />
					</li>
					<li>
						<!-- Modification numéro de téléphone -->
		                <label for="phone">Modification numéro de téléphone</label>
		                <input type="text" id="phone" name="phone" value="${ sessionScope.sessionMember.phone }" size="20" maxlength="20" />
					</li>
					<li>
						<!-- Modification date de naissance -->
		                <label for="birthday"> Modification date de naissance au format jj/mm/aaaa</label>
		                <input type="text" id="birthday" name="birthday" value="${ sessionScope.sessionMember.birthday }" size="20" maxlength="20" />
					</li>
					<li>
						<!-- Modification numéro de permis -->
		                <label for="license">Modification numéro du permis de conduire</label>
		                <input type="text" id="license" name="license" value="${ sessionScope.sessionMember.license }" size="20" maxlength="20" />
					</li>
					<li>
		                <input type="submit" value="Enregistrer les modifications" class="sansLabel" />
		                <a href="profile"><input type="button" name="Répondre"value="Retour Information"/></a>
					</li>
				</ul>
            </fieldset>
        </form>
