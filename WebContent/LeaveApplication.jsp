<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<body 
onload="noBack();" onunload="">
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
<h3>Leave Requests</h3>
<c:if test="${not empty leaveApplicationMB.instructorList}">
		<h:dataTable  border="3" value="#{leaveApplicationMB.instructorList}" var="leave" styleClass="tableStyle" >
			<h:column id="column0">
				<f:facet name="header">
					<h:outputText value="Calendar Id" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{leave.calendarId}"></h:outputText>
			</h:column>
			
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Instructor Id" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{leave.instructorId}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Blocked From" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
			<h:outputText value="#{leave.blockedFrom}">
			<f:convertDateTime pattern="dd-MMM-yyyy" dateStyle="short" /></h:outputText>
			
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Blocked To" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{leave.blockedTo}">
				<f:convertDateTime pattern="dd-MMM-yyyy" dateStyle="short" /></h:outputText>
			
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Reason" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{leave.reason}"></h:outputText>
			
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Sel" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
					
					<h:selectBooleanCheckbox value="#{leave.flag}" id="s" required="true" requiredMessage="Select atleast one option">
					<h:message for="s"></h:message>
					</h:selectBooleanCheckbox>
					
				</h:column>
			
		</h:dataTable>
		
		<h:commandButton value="Approve"  action="#{leaveApplicationMB.approveLeave}"></h:commandButton>		
	<h:commandButton value="Reject" action="#{leaveApplicationMB.rejectLeave}"></h:commandButton><br>	
	<h:messages></h:messages>	
	</c:if>
	<h:outputText value="#{leaveApplicationMB.message}" styleClass="error"></h:outputText>
	
	</h:form>
	</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>
	<div>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	
	
</f:view>
</body>
</html>