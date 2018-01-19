<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
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
		
		<c:choose>
		<c:when test="${loginMB.role eq 'A'}">
		<h:form styleClass="forms" style="position:relative;">
<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
			<h:outputText value="Logged in as"/>
			<h:outputText value="#{loginMB.userId}" />
		<h:outputLink value="Roles.jsp">Home</h:outputLink>
			<h:commandLink action="#{loginMB.logout}">Logout</h:commandLink>
		</h:panelGrid>
		</h:form><br><br>
		</c:when>
		

		<c:when test="${loginMB.role eq null}">
		<h:form styleClass="forms" style="position:relative;">
		<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
			<h:outputLink value="home.jsp">Home</h:outputLink>
		<h:outputLink value="ViewCourses.jsp">Courses</h:outputLink>
		<h:outputLink value="CustomerRegistration.jsp">SignUp</h:outputLink>
		
			<h:outputLink value="ViewRegistrationStatus.jsp" >ViewRegistrationStatus</h:outputLink>
		</h:panelGrid><br><br>
		</h:form>
		</c:when>
		
		
		</c:choose>
		
		
		<h:form styleClass="forms">
	<br><br>
	<h1>Courses</h1>
	<c:if test="${not empty courseDetailsMB.courseList}">
	<h:dataTable border="1" value="#{courseDetailsMB.courseList}" var="course" styleClass="tableStyle">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{course.courseName}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Course Level" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{course.courseLevel}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Duration" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{course.duration}"></h:outputText>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Certification" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{course.certification}">
				<f:converter converterId="certificationConverter"/></h:outputText>
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Fees" styleClass="tableHeaderStyle"></h:outputText>
				</f:facet>
				<h:outputText value="#{course.fee}"></h:outputText>
			</h:column>
		</h:dataTable>
	
	
	</c:if>




</h:form>

<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>