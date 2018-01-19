<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>

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
		

		<h2>View Application Status</h2>
		<br>
		<c:choose>
			<c:when test="${not empty viewApplicationStatusMB.listOfCourses}">

				<h:dataTable border="1"
					value="#{viewApplicationStatusMB.listOfCourses}" var="l"
					styleClass="tableStyle">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Application Id"
								styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{l.applicationId}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Trainee Id" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{l.traineeId}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Course Id" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{l.courseId}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Fee Paid" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{l.feePaid}"></h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Application Status"
								styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{l.applicationStatus}">
						<f:converter converterId="statusConvertor"/>
						</h:outputText>
					</h:column>
					<h:column id="column6">
						<f:facet name="header">
							<h:outputText value="DateOfApplication"
								styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{l.dateOfApplication}">
							<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
						</h:outputText>
					</h:column>
				</h:dataTable>
			</c:when>
			<c:otherwise>
				<h:outputText value="#{viewApplicationStatusMB.message}"
					styleClass="error"></h:outputText>
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