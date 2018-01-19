<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 
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
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
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
	<h1>View Batch Details</h1>
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Select the Batch Type"></h:outputText>
			<h:selectOneMenu valueChangeListener="#{batchDetailsMB.getBatchDetails}"  onchange="submit();" value="#{batchDetailsMB.batchType}">
				<f:selectItem itemLabel="-Select-"/>
				<f:selectItem itemValue="D" itemLabel="WeekDay"/>
				<f:selectItem itemValue="E" itemLabel="WeekEnd"/>
				
			</h:selectOneMenu>
		</h:panelGrid><br>
		<c:if test="${not empty batchDetailsMB.batchList}">
		
		<h:dataTable border="1" value="#{batchDetailsMB.batchList}" var="row"  styleClass="tableStyle">
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Batch Id"/>
			</f:facet>
			<h:outputText value="#{row.batchId}"></h:outputText>
		</h:column>
		<h:column id="column2">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Batch Strength"/>
			</f:facet>
			<h:outputText value="#{row.batchStrength}"></h:outputText>
		</h:column>
		<h:column id="column3">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Venue Id"/>
			</f:facet>
			<h:outputText value="#{row.venueId}"></h:outputText>
		</h:column>
		<h:column id="column4">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Instructor Id"/>
			</f:facet>
			<h:outputText value="#{row.instructorId}"></h:outputText>
		</h:column>
		<h:column id="column5">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Start Date"/>
			</f:facet>
			<h:outputText value="#{row.startDate}">
			<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/> 
			</h:outputText>
		</h:column>
		<h:column id="column6">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="End Date"/>
			</f:facet>
			<h:outputText value="#{row.batchEndDate}">
			<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/> 
			</h:outputText>
		</h:column>
		<h:column id="column7">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Closure Date"/>
			</f:facet>
		<h:outputText value="#{row.batchClosureDate}">
		<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/> 
		</h:outputText>
		</h:column>
		<h:column id="column8">
			<f:facet name="header">
				<h:outputText styleClass="tableHeaderStyle" value="Closure Status"/>
			</f:facet>
			<h:outputText value="#{row.batchClosureStatus}">
			<f:converter converterId="batchStatusConverter"/> 
			</h:outputText>
		</h:column>
		
	</h:dataTable>
		
		
		
		</c:if>
	</h:form>
	<h:outputText styleClass="error" value="#{batchDetailsMB.message}"></h:outputText>
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