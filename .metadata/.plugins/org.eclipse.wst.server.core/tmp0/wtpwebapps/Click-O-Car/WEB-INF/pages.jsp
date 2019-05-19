<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Click'o'Car ${ title }</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	<div id="main">
		<jsp:include page="/WEB-INF/pages/${ content }.jsp" />
	</div>
	<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>