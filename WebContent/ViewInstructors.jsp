<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
<br><br>
<br>
		<h1>View Instructors</h1>
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Instructors Available"></h:outputText>
			<h:selectOneMenu value="#{instructorMB.instructorId}"  valueChangeListener="#{instructorMB.getInstructorDetail}" onchange="submit()">
			<f:selectItem itemLabel="--select--"/>
			<f:selectItems value="#{instructorMB.instructorList}"/>
			</h:selectOneMenu>
		</h:panelGrid><br><br>
		<c:choose>
		<c:when test="${not empty instructorMB.courseDetails}">
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Instructor Name"></h:outputText>
			<h:outputText value="#{instructorMB.instructorName}"></h:outputText>
			<h:outputText value="Date Of Joining"></h:outputText>
			<h:outputText value="#{instructorMB.dateOfJoining}" ><f:convertDateTime pattern="dd-MMM-yyyy" /></h:outputText>
			<h:outputText value="Course Taken"></h:outputText>
			<h:dataTable border="1" value="#{instructorMB.courseDetails}" var="row" style="tableStyle">
				<h:column id="column1">
					<f:facet name="header" >
						<h:outputText value="Course Id" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.courseId}"></h:outputText>
				</h:column>
				<h:column id="column2">
					<f:facet name="header">
						<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.courseName}"></h:outputText>
				</h:column>
			</h:dataTable>
			</h:panelGrid>
	</c:when>
	<c:otherwise>
<h:outputText value="#{instructorMB.message}">

</h:outputText>
	
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
<jsp:include page="footer.jsp"></jsp:include>
</f:view>


</body>
</html>
