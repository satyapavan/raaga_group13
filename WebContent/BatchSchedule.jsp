<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Batch Schedule</title>
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
		<h1>Batch Schedule</h1>
		<br>
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="Select Course Name"></h:outputText>
			<h:selectOneMenu value="#{batchScheduleMB.selectedCourseName}"
				valueChangeListener="#{batchScheduleMB.getCourseName}"
				id="courseName" onchange="submit()">
				<f:selectItem itemLabel="-select-" />
				<f:selectItems value="#{batchScheduleMB.courseNames}" />
			</h:selectOneMenu>
			<h:message for="courseName" styleClass="error"></h:message>

			<c:choose>
				<c:when test="${not empty batchScheduleMB.courseLevels}">
					<h:outputText value="Select Course Level"></h:outputText>
					<h:selectOneMenu value="#{batchScheduleMB.selectedCourseLevel}"
						required="true" requiredMessage="Please select a course level"
						id="courseLevel" >
						<f:selectItem itemLabel="-select-" />
						<f:selectItems value="#{batchScheduleMB.courseLevels}" />
					</h:selectOneMenu>
					<h:message for="courseLevel" styleClass="error"></h:message>

					<h:outputText value="Start Date (dd-MMM-yyyy)"></h:outputText>
					<h:inputText value="#{batchScheduleMB.startDate}" required="true"
						requiredMessage="Start Date is Mandatory" id="startDate" converterMessage="Date must be of dd-MMM-yyyy format">
						<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						<f:validator validatorId="batchStartDateValidator" />
					</h:inputText>
					<h:message for="startDate" styleClass="error"></h:message>


					<h:outputText value="Select Batch Type"></h:outputText>
					<h:selectOneMenu value="#{batchScheduleMB.batchType}"
						required="true" requiredMessage="Please select a batch type"
						id="batchType">
						<f:selectItem itemLabel="-select-" />
						<f:selectItem itemLabel="WeekDay" itemValue="D" />
						<f:selectItem itemLabel="WeekEnd" itemValue="E" />

					</h:selectOneMenu>
					<h:message for="batchType" styleClass="error"></h:message>


					<h:outputText value="Select Instructor"></h:outputText>
					<h:selectOneMenu value="#{batchScheduleMB.instructorId}"
						required="true" requiredMessage="Please select an Instructor"
						id="instructorId">
						<f:selectItem itemLabel="-select-" />
						<f:selectItems value="#{batchScheduleMB.instructors}" />
					</h:selectOneMenu>
					<h:message for="instructorId" styleClass="error"></h:message>


					<h:outputText value="Select Venue"></h:outputText>
					<h:selectOneMenu value="#{batchScheduleMB.classRoomId}"
						required="true" requiredMessage="Please select a venue"
						id="classroomId">
						<f:selectItem itemLabel="-select-" />
						<f:selectItems value="#{batchScheduleMB.classRooms}" />
					</h:selectOneMenu>
					<h:message for="classroomId" styleClass="error"></h:message>


					<h:commandButton type="submit"
						action="#{batchScheduleMB.scheduleBatch}" value="Schedule Batch"></h:commandButton>
					<h:commandButton type="reset" value="Reset"></h:commandButton>

				</c:when>
			</c:choose>
		</h:panelGrid>
		<h:outputText value="#{batchScheduleMB.message}" styleClass="error"></h:outputText>

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