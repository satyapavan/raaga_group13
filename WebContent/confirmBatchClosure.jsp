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



</HEAD>
<BODY onload="noBack();" onunload="">
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
		
		<br>
		<H2>Confirm Batch Closure</H2>
		<h:panelGrid border="0" columns="3" styleClass="tableStyle">
	 <h:outputText value="Select Batch"></h:outputText>
	  <h:selectOneMenu
			valueChangeListener="#{confirmBatchClosureMB.getBatchDetails}"
			value="#{confirmBatchClosureMB.batchId}" onchange="submit()" required="true" requiredMessage="select atleast one option" id="batch">
			<f:selectItem itemLabel="-Select-" />
			<f:selectItems value="#{confirmBatchClosureMB.batchIdList}" />
		</h:selectOneMenu> 
		<h:message for="batch" styleClass="error"></h:message>
		</h:panelGrid><br>
		<br>
		<c:if test="${confirmBatchClosureMB.batchId!=0}">
			<h:panelGrid border="1" columns="2" styleClass="tableStyle">
				<h:outputText value="Course Id"></h:outputText>
				<h:outputText value="#{confirmBatchClosureMB.courseId}"></h:outputText>
				<h:outputText value="Start Date"></h:outputText>
				<h:outputText value="#{confirmBatchClosureMB.startDate}">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
				</h:outputText>
				<h:outputText value="End Date"></h:outputText>
				<h:outputText value="#{confirmBatchClosureMB.batchEndDate}">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
				</h:outputText>
				<h:outputText value="Closure Date"></h:outputText>
				<h:outputText value="#{confirmBatchClosureMB.batchClosureDate}">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
				</h:outputText>
				<h:commandButton type="submit" value="Close Batch"
			action="#{confirmBatchClosureMB.confirmBatchClosure}"></h:commandButton><br>
			</h:panelGrid>
		</c:if> <br>
		
		<c:if test="${ confirmBatchClosureMB.batchId!=0}">
			<h:outputText value="#{confirmBatchClosureMB.message}" styleClass="error"></h:outputText>
		</c:if>
		
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
