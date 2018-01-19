<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<c:if test="${editCourseDetailsMB.message ne 'Course Edited Successfully'}">
			<h1>Edit Course Details</h1>
			<h:panelGrid border="1" columns="2" styleClass="tableStyle">
				<h:outputText value="Select a Batch Type"></h:outputText>
				<h:selectOneMenu value="#{editCourseDetailsMB.selectedCourse}"
					valueChangeListener="#{editCourseDetailsMB.getSelectedCourseName}"
					onchange="submit();">
					<f:selectItem itemLabel="-Select-" />
					<f:selectItems value="#{editCourseDetailsMB.courseNames}" />
				</h:selectOneMenu>

				<c:if test="${not empty editCourseDetailsMB.courseLevels}">
					<h:outputText value="Select Course Level"></h:outputText>
					<h:selectOneMenu value="#{editCourseDetailsMB.selectedLevel}"
						valueChangeListener="#{editCourseDetailsMB.getSelectedLevelName}"
						onchange="submit();">
						<f:selectItem itemLabel="-Select-" />
						<f:selectItems value="#{editCourseDetailsMB.courseLevels}" />
					</h:selectOneMenu>
				</c:if>
			</h:panelGrid>
			<h:outputText styleClass="error"
				value="#{editCourseDetailsMB.message}"></h:outputText>
			<br>
			<c:if test="${empty editCourseDetailsMB.message}">
			<c:if test="${not empty editCourseDetailsMB.detailsTO}">

				<h:dataTable border="1" value="#{editCourseDetailsMB.detailsTO}"
					var="row" styleClass="tableStyle">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Course Id" />
						</f:facet>
						<h:outputText value="#{row.courseId}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Course Level" />
						</f:facet>
						<h:outputText value="#{row.courseLevel}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Course Type" />
						</f:facet>
						<h:outputText value="#{row.courseType}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Certification" />
						</f:facet>
						<h:outputText value="#{row.certification}">
							<f:converter converterId="certificationConverter" />
						</h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Course Duration" />
						</f:facet>
						<h:outputText value="#{row.duration}"></h:outputText>
					</h:column>
					<h:column id="column6">
						<f:facet name="header">
							<h:outputText value="Course Fee" />
						</f:facet>
						<h:outputText value="#{row.fee}"></h:outputText>
					</h:column>
				</h:dataTable>
				<br>
				<h:panelGrid border="1" columns="3" styleClass="tableStyle">
					<h:outputText value="Select Course Certification"></h:outputText>
					<h:selectOneMenu value="#{editCourseDetailsMB.certification}"
						required="true" requiredMessage="Please select atleast one option"
						id="cert">
						<f:selectItem itemValue="Y" itemLabel="Yes"></f:selectItem>
						<f:selectItem itemValue="N" itemLabel="No"></f:selectItem>

					</h:selectOneMenu>
					<h:message for="cert" styleClass="error"></h:message>
					<h:outputText value="Select Course Duration"></h:outputText>
					<h:inputText value="#{editCourseDetailsMB.duration}"
						required="true" requiredMessage="enter the duration" id="duration"></h:inputText>
					<h:message for="duration" styleClass="error"></h:message>
					<h:outputText value="Select Course Fee"></h:outputText>
					<h:inputText value="#{editCourseDetailsMB.fee}" required="true"
						requiredMessage="enter the fee" id="coursefee"></h:inputText>
					<h:message for="coursefee" styleClass="error"></h:message>
					<h:commandButton value="EditCourse"
						action="#{editCourseDetailsMB.editCourse}"></h:commandButton>
					<h:commandButton value="Reset" type="reset"></h:commandButton>
				</h:panelGrid>

				<br>

			</c:if>
			</c:if>
		</c:if>
		<c:if
			test="${editCourseDetailsMB.message eq 'Course Edited Successfully'}">
			<h1>Edit Course Details Success</h1>
			<h:outputText styleClass="error"
				value="#{editCourseDetailsMB.message}"></h:outputText>
		</c:if>
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