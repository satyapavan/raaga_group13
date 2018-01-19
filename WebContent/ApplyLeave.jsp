<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<f:view>
	<jsp:include page="header.jsp"></jsp:include><br>
	<c:choose>
<c:when test="${not empty loginMB.userId}">	
	
	<h:form styleClass="forms" style="position:relative;">
<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
			<h:outputText value="Logged in as"/>
			<h:outputText value="#{loginMB.userId}" />
		<h:outputLink value="Roles.jsp">Home</h:outputLink>
			<h:commandLink action="#{loginMB.logout}">Logout</h:commandLink>
		</h:panelGrid>
</h:form><br><br>
<br>
<h:form styleClass="forms">

	<br><br><br>
		
		<h1>Apply Leave</h1>
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="From Date"></h:outputText>
			<h:inputText id="from" value="#{applyLeaveMB.blockedFrom}"
				required="true" requiredMessage="FromDate is Mandatory" converterMessage="Date must be of dd-MMM-yyyy format">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:inputText>
			<h:message for="from" styleClass="error"></h:message>
			<h:outputText value="To Date"></h:outputText>
			<h:inputText id="to" value="#{applyLeaveMB.blockedTo}"
				required="true" requiredMessage="ToDate is Mandatory" converterMessage="Date must be of dd-MMM-yyyy format">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:inputText>
			<h:message for="to" styleClass="error"></h:message>
			<h:outputText value="Please specify the reason"></h:outputText>
			<h:inputText id="son" value="#{applyLeaveMB.reason}" required="true"
				requiredMessage="Please specify the reason" validatorMessage="Reason should be atleast more than 3 characters">
				<f:validateLength minimum="3" maximum="30"/>
				
				</h:inputText>
			<h:message for="son" styleClass="error"></h:message>
 
			<h:commandButton value="Apply Leave"
				action="#{applyLeaveMB.applyLeave}">
			</h:commandButton>
		</h:panelGrid> 
		
		
			<h:outputText value="#{applyLeaveMB.message}" styleClass="error"></h:outputText>
		
	</h:form>
	

</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>
<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>