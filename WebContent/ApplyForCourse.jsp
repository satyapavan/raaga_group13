<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apply For Course</title>
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
	<h1>Apply For A Course</h1>
		
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
		<h:outputText value="Courses Available"></h:outputText>
			<h:selectOneMenu value="#{applyForCourseMB.courseId}" valueChangeListener="#{applyForCourseMB.getCourseDetail}" onchange="submit();">
				<f:selectItem itemLabel="-Select-"/>
				<f:selectItems value="#{applyForCourseMB.courseAvailable}"/>
			</h:selectOneMenu>
			
		</h:panelGrid>	
		<br>
		
		
		<c:if test="${not empty applyForCourseMB.instructorAvailable}">
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
		<h:outputText value="Course Name"/>
		<h:outputText value="#{applyForCourseMB.courseName}"></h:outputText>
		<h:message for=""></h:message>
		<h:outputText value="Duration"/>
		<h:outputText value="#{applyForCourseMB.duration}"></h:outputText>
		<h:message for=""></h:message>
		<h:outputText value="Fee"/>
		<h:outputText value="#{applyForCourseMB.fee}"></h:outputText>
		<h:message for=""></h:message>
		<h:outputText value="Certification"/>
		<h:outputText value="#{applyForCourseMB.certification}"></h:outputText>
		<h:message for=""></h:message>
		<h:outputText value="Instructors Available(Select To View Detail)"/>
		<h:selectOneMenu value="#{applyForCourseMB.instructorId}" required="true" requiredMessage="Please select an instructor" id="iid">
		<f:selectItem itemLabel="--Select"/>
		<f:selectItems value="#{applyForCourseMB.instructorAvailable}"/>
		
		</h:selectOneMenu>
		<h:message styleClass="error" for="iid"></h:message>
	</h:panelGrid>
	<br>
	
	<h:panelGrid border="1" columns="3" styleClass="tableStyle">
		<h:outputText value="Fees Paid"/>
	    <h:inputText value="#{applyForCourseMB.feePaid}" required="true" requiredMessage="Please enter fee" id="fee"></h:inputText>
	    <h:message styleClass="error" for="fee"></h:message>
	    <h:commandButton action="#{applyForCourseMB.applyForCourse}" value="Apply for Course"/>
	</h:panelGrid>
	<br>
		</c:if>
		
		<c:if test="${not empty applyForCourseMB.instructorId}">
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Instructor Name"/>
			<h:outputText value="#{applyForCourseMB.instructorName}"/>
			<h:outputText value="Date Of Joining"/>
			<h:outputText value="#{applyForCourseMB.dateOfJoining}"><f:convertDateTime pattern="dd-MMM-yyyy" type="date"/></h:outputText>
			
			<h:outputText value="Courses Taken"/>
				<h:panelGrid border="1" columns="2" styleClass="tableStyle">
				
				<h:outputText value="Course Id"  styleClass="tableHeaderStyle"/>
				<h:outputText value="Course Name"  styleClass="tableHeaderStyle"/>
			<h:outputText value="#{applyForCourseMB.courseId}" />
			
			<h:outputText value="#{applyForCourseMB.courseName}"/>
				
				</h:panelGrid>
					
			</h:panelGrid> </c:if> 
		
		<h:outputText styleClass="error" value="#{applyForCourseMB.message}"></h:outputText>
</h:form>
	
</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>


</f:view></body>
</html>