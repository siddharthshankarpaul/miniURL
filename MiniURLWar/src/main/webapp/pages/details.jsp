<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="<c:url value="/css/base.css" />">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="http://www.geoplugin.net/javascript.gp" type="text/javascript"></script>
<title>Make tiny url</title>
</head>
<body>
	<div class="container">
		<h2>Your short URL is</h2>
		<a href="http://localhost:8080/mini/${shortURL}" target="_blank" onclick="document.getElementById('form1').submit();"> http://localhost:8080/mini/${shortURL} </a>
		<div> 
			<jsp:include page="chart.jsp"/>
		</div>
		<form method="post" id="form1">
		<input type="hidden" name="countryCode" id="countryCode"/>
		</form>
	</div>
	<script type="text/javascript">
	jQuery(document).ready(function($) {
	    jQuery.getScript('http://www.geoplugin.net/javascript.gp', function(){
	    var country = geoplugin_countryCode();
	    document.getElementById("countryCode").value = country;
	});
	});
	</script>
</body>
</html>