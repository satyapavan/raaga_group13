<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.history.forward(1);
function noRightClick() {
    if (event.button==2) {
         alert("Disabled Right Click.");
    }
}

document.onmousedown=noRightClick;


</script>
<title>Logout</title>
</head>
<body>
		
	<%session.invalidate();%>
            <jsp:forward   page="home.jsp" ></jsp:forward>
		
		
</body>
</html>