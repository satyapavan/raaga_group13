<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" href="style.css" type="text/css">
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
<body onload="noBack();" onunload="">
<f:view>
<jsp:include page="header.jsp"></jsp:include>
<h:form styleClass="forms" style="position:relative;">
<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
		<h:outputLink value="home.jsp">Home</h:outputLink>
		<h:outputLink value="ViewCourses.jsp">Courses</h:outputLink>
		<h:outputLink value="CustomerRegistration.jsp">SignUp</h:outputLink>
		<h:outputLink value="ViewRegistrationStatus.jsp">View Registration Status</h:outputLink>
			
		</h:panelGrid>
</h:form><br><br>
<br>
<h:form styleClass="forms">
		
	<br><br>
	
		<h1>View Registration Status</h1>
		<br>
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="Please Enter your Registration Id"></h:outputText>
			<h:inputText value="#{customerRegistrationMB.customerRegistrationId}"
				required="true" requiredMessage="Registration Id is Mandatory" id="custRegId"></h:inputText>
			<h:message for="custRegId" styleClass="error"></h:message>
			<h:commandButton value="Check"
				action="#{customerRegistrationMB.viewRegistrationStatus}"></h:commandButton>
			<h:commandButton type="reset" value="Reset"></h:commandButton>
		</h:panelGrid>
		<br>
		<c:choose>
			<c:when test="${not empty customerRegistrationMB.registrationStatus}">
				<h:panelGrid border="1" columns="1" styleClass="tableStyle">
					<h:outputText value="Status"></h:outputText>
					<h:outputText value="#{customerRegistrationMB.registrationStatus}">
						<f:converter converterId="statusConvertor" />
					</h:outputText>
					<c:if test="${customerRegistrationMB.registrationStatus eq 'A'}">
						<h:commandButton value="Get Login Details"
							action="#{customerRegistrationMB.getLoginDetails}" type="submit"></h:commandButton>
					</c:if>
				</h:panelGrid>
				<br>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when
				test="${not empty customerRegistrationMB.userId and customerRegistrationMB.registrationStatus eq 'A'}">
				<h:panelGrid border="1" columns="2" styleClass="tableStyle">
					<h:outputText value="User Id"></h:outputText>
					<h:outputText value="#{customerRegistrationMB.userId}"></h:outputText>
					<h:outputText value="Password"></h:outputText>
					<h:outputText value="#{customerRegistrationMB.password}"></h:outputText>
				</h:panelGrid>
			</c:when>
			<c:otherwise>
			<h:outputText value="#{customerRegistrationMB.message}" styleClass="error"></h:outputText>
			</c:otherwise>
		</c:choose>
	
	</h:form>
</f:view>
</body>
</html>