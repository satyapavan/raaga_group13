<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
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
		
	<h2>View Course Instructor Details</h2>
	<h:panelGrid border="0" columns="3" styleClass="tableStyle">
			<h:outputText value="Select Course"></h:outputText>
			<h:selectOneMenu id="course" value="#{viewCourseDetailsMB.selectedCourseName}" valueChangeListener="#{viewCourseDetailsMB.getSelectedCourse}"  onchange="submit()"    required="true" requiredMessage="Select atleast one option">
			<f:selectItem itemLabel="-select-"/>
			<f:selectItems value="#{viewCourseDetailsMB.courses}"/>
			</h:selectOneMenu>
			<h:message for="course"></h:message>

			

		</h:panelGrid>
		
		<br><br>
		
		
		<c:if test="${not empty viewCourseDetailsMB.detailsTOs}">
		
			<h:dataTable border="1" id="health" value="#{viewCourseDetailsMB.detailsTOs}" var="course" styleClass="tableStyle">
				<h:column id="column1">
				<h:outputText value="#{course.courseId}"></h:outputText>
					<f:facet name="header">
						<h:outputText value="Course Id" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				<h:column id="column2">
				<h:outputText value="#{course.courseLevel}"></h:outputText>
					<f:facet name="header">
						<h:outputText value="Course Level" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				<h:column id="column3">
				<h:outputText value="#{course.courseType}"></h:outputText>
					<f:facet name="header">
						<h:outputText value="Course Type" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				
				<h:column id="column4">
				<h:outputText value="#{course.certification}">
				<f:converter converterId="certificationConverter"/>
				</h:outputText>
				
					<f:facet name="header">
						<h:outputText value="Certification" styleClass="tableHeaderStyle">
						
						</h:outputText>
					</f:facet>
				</h:column>
				
				<h:column id="column5">
				<h:outputText value="#{course.duration}">
				
				</h:outputText>
				
					<f:facet name="header">
						<h:outputText value="Course Duration" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				
				<h:column id="column6">
				<h:outputText value="#{course.fee}">
				
				</h:outputText>
				
					<f:facet name="header">
						<h:outputText value="Course Fee" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
			</h:dataTable>
		</c:if>
		
		
		<br><br>
		
		<c:if test="${not empty viewCourseDetailsMB.instructorTOs}">
		
			<h:dataTable border="1" id="health1" value="#{viewCourseDetailsMB.instructorTOs}" var="instructor" styleClass="tableStyle" >
				<h:column id="column1">
				<h:outputText value="#{instructor.instructorId}"></h:outputText>
					<f:facet name="header">
						<h:outputText value="Instructor Id" styleClass="tableHeaderStyle"> </h:outputText>
					</f:facet>
				</h:column>
				<h:column id="column2">
				<h:outputText value="#{instructor.instructorName}"></h:outputText>
					<f:facet name="header">
						<h:outputText value="Instructor Name" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				<h:column id="column3">
				<h:outputText value="#{instructor.contactNumber}"></h:outputText>
					<f:facet name="header">
						<h:outputText value="Contact No" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				
				<h:column id="column4">
				<h:outputText value="#{instructor.dateOfJoining}">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
				</h:outputText>
				
					<f:facet name="header">
						<h:outputText value="Date Of Joining" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				
				<h:column id="column5">
				<h:outputText value="#{instructor.address}">
				
				</h:outputText>
				
					<f:facet name="header">
						<h:outputText value="Address" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
				</h:column>
				
				
			</h:dataTable>
		</c:if>
		<h:outputText value="#{viewCourseDetailsMB.message}"></h:outputText><br>

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