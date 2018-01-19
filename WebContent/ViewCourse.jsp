<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h2>View Courses</h2>
	<c:if test="${not empty courseDetailsMB.courseList}">
	<h:dataTable border="1" value="#{courseDetailsMB.courseList}" var="row" styleClass="tableStyle">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.courseName}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Course Level" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.courseLevel}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Duration" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.duration}"></h:outputText>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Certification" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.certification}">
				<f:converter converterId="certificationConverter"/></h:outputText>
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Fees" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.fee}"></h:outputText>
			</h:column>
		</h:dataTable>
	
	
	</c:if>




</h:form>
</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>
<div align="center"><jsp:include page="footer.jsp"></jsp:include></div>
</f:view>
</body>
</html>