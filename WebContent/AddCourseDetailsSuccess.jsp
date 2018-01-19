<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title>Raaga MDI LTD</title>
<script type="text/javascript">
window.history.forward(1); 
function noRightClick() {
if (event.button==2) {
alert("Right Click Is Disabled");
}
}
document.onmousedown=noRightClick;
</script>
</head>
<body onload="noBack();" onunload="">
<jsp:include page="header.jsp"></jsp:include>
<f:view>
<c:choose>
<c:when test="${not empty loginMB.userId}">	
<br>
<h:form styleClass="forms" style="position:relative;">
<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
			<h:outputText value="Logged in as"/>
			<h:outputText value="#{loginMB.userId}" />
		<h:outputLink value="Roles.jsp">Home</h:outputLink>
			<h:commandLink action="#{loginMB.logout}">Logout</h:commandLink>
		</h:panelGrid>
</h:form>
<br>
<h:form styleClass="forms">
<br><br><br>
<h1>Add Course Details Success</h1><br><br>
<h:outputText value="#{addCourseDetailsMB.message}" styleClass="error"></h:outputText>

</h:form>
<br>
<br>
<br>
<br>
<br>

</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>
<center><jsp:include page="footer.jsp"></jsp:include></center>
</f:view>
</body>
</html>