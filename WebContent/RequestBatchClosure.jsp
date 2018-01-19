<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css"/>
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

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>

</head>
<body onload="noBack();" onunload="">
<f:view>
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
<center>
<h2 style="heading"> Request Batch Closure</h2>
</center>
<c:choose>
<c:when test="${not empty requestBatchClosureMB.batchIdList}">
				<h:panelGrid border="1" columns="3" styleClass="tableStyle">
					<h:outputText value="Select the batch"></h:outputText>
					<h:selectOneMenu id="select" value="#{requestBatchClosureMB.batchId}" required="true" requiredMessage="Please select a batch">
					<f:selectItem itemLabel="-select-"/>
					<f:selectItems value="#{requestBatchClosureMB.batchIdList}" /></h:selectOneMenu>
					   <h:message for="select" styleClass="error"></h:message> 
					<h:outputText value="Batch Closure Date"></h:outputText>
					<h:inputText id="batchclose" value="#{requestBatchClosureMB.batchClosureDate}" required="true" requiredMessage="Batch Closure Date is mandatory" converterMessage="Date must be of dd-MMM-yyyy format">
					<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
					</h:inputText>
					<h:message for="batchclose" styleClass="error"></h:message>
					<h:commandButton value="Request for Batch Closure" action="#{requestBatchClosureMB.requestClosure}"></h:commandButton>
				</h:panelGrid><br>
				<h:outputText styleClass="error" value="#{requestBatchClosureMB.message}"></h:outputText>
			</c:when>
			<c:otherwise>
			<h3>
			<font color="red"> No Batches allocated to you <br> Sorry!! No Batch is allocated to you</font>
			</h3>
			</c:otherwise>
</c:choose>


</h:form>
</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>

</f:view>
</body>
</html>