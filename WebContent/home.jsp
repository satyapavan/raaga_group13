<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link href="style.css" type="text/css" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<jsp:include page="header.jsp"></jsp:include>
<body onload="noBack();" onunload="">
<f:view>
	
		<h:panelGrid border="0" columns="4" styleClass="toolbar" cellpadding="4">


			<h:outputLink value="ViewCourses.jsp">Courses</h:outputLink>
			<h:outputLink value="CustomerRegistration.jsp">SignUp</h:outputLink>
			<h:outputLink value="ViewRegistrationStatus.jsp">View Registration Status</h:outputLink>
		</h:panelGrid>



<h:form styleClass="forms">

		<h:outputText value="Login" styleClass="heading"></h:outputText><br><br>
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="UserName"></h:outputText>
			<h:inputText id="name" value="#{loginMB.userId}" required="true"
				requiredMessage="UserName is mandatory"></h:inputText>
				<h:message for="name" styleClass="error"></h:message>
			<h:outputText value="Password"></h:outputText>
			<h:inputSecret id="password" value="#{loginMB.password}" required="true"
				requiredMessage="Password is mandatory"></h:inputSecret>
				<h:message for="password"  styleClass="error"></h:message>
			<h:commandButton value="Login" action="#{loginMB.validateLogin}"></h:commandButton>
			<h:commandButton type="reset" value="Reset"></h:commandButton>
		</h:panelGrid>




		<h:outputText value="#{loginMB.message}" styleClass="error"></h:outputText>


		<jsp:include page="footer.jsp"></jsp:include>
		
	</h:form>
	
</f:view>
</body>
</html>