<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div id="logo">
		<img alt="Click-o-Car - logo" src="${pageContext.request.contextPath}/logo_click-o-Car.png" />
	</div>
	<div id="headerMain">
		<h1>${ title }</h1>
	</div>
		<nav id="connectMenu">
			<ul>
	<c:if test="${empty sessionScope.sessionMember}">
				<li>
					<a href="registration">
						<button>S'enregistrer</button>
					</a>
				</li>
				<li>
					<a href="connexion">
						<button>Se connecter</button>
					</a>
				</li>
	</c:if>
	<c:if test="${!empty sessionScope.sessionMember}">
				<li>
					<a href="profile">
						<button>Paramètres</button>
					</a>
				</li>
				<li>
					<a href="deconnexion">
						<button>Se déconnecter</button>
					</a>
				</li>
	</c:if>
			</ul>
		</nav>
</header>
<nav id="mainMenu">
	<ul id="myTopNav" class="topnav">
		<li>
			<button id="btnHome" onclick="window.location.href = '${pageContext.request.contextPath}';">Accueil</button>
		</li>
		<li>
			<button id="btnTravel" onclick="window.location.href = 'nouveau-trajet';">Trajets</button>
		</li>
	</ul>
	<a href="javascript:void(0);" class="icon" onclick="myFunction()">&#9776;</a>
</nav>
