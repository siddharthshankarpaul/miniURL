<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="<c:url value="/css/base.css" />">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>Make tiny url</title>
</head>
<body>
	<div class="container jumbotron">
		<form id="form" method="post" class="form-submit">
		
			<h2><label for="url-text">Paste your long url here:</label></h2> 
			
			<div class="input-group">			
				<input name="url-text" id="url-text" placeholder="Long url goes here" type="text" class="url-input input-lg form-control"/> 
				<span class="input-group-btn"><input type="submit" value="Trim" class="btn btn-lg btn-primary" /></span>
				<c:if test="${error} ne empty">
				${error}
				</c:if>
			</div>
		</form>
	</div>

</body>
</html>