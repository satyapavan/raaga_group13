<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<br><br>
	
	
	<h2>Reject Allocation</h2>
	<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="Select The Batch"></h:outputText>
			<h:selectOneMenu id="batch" value="#{rejectAllocationMB.batchId}" required="True" requiredMessage="Select atleast one option">
			<f:selectItem itemLabel="-select-"/>
			<f:selectItems value="#{rejectAllocationMB.batchIdList}"/>
			</h:selectOneMenu>
			<h:message for="batch" ></h:message>

			<h:commandButton value="Reject" action="#{rejectAllocationMB.rejectAllocation}"></h:commandButton>

		</h:panelGrid>
		<h:outputText styleClass="error"    value="#{rejectAllocationMB.message}"></h:outputText>




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