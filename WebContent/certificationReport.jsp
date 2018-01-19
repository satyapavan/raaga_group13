<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
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
	<h2 style="heading" > Certification Report</h2>
	
		<h:panelGrid border="1" columns="3" styleClass="tableStyle" >
			<h:outputText value="Start Date"></h:outputText>
			<h:inputText id="startDate"
				value="#{certificationReportMB.startDate}" required="true"
				requiredMessage="Start Date is Mandatory" converterMessage="Date must be of dd-MMM-yyyy format">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:inputText>
			<h:message for="startDate" styleClass="error"></h:message>
			<h:outputText value="End Date"></h:outputText>
			<h:inputText id="endDate" value="#{certificationReportMB.endDate}"
				required="true" requiredMessage="End Date is Mandatory" converterMessage="Date must be of dd-MMM-yyyy format">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:inputText>
			<h:message for="endDate" styleClass="error"></h:message>
			<h:commandButton value="Get Report" type="submit"
				action="#{certificationReportMB.getCertificationReport}"></h:commandButton>

			<h:commandButton value="Reset" type="reset"></h:commandButton>
		</h:panelGrid>
		<br>
		
		<c:choose>
			<c:when test="${not empty certificationReportMB.certificationList}">
				<h:dataTable border="1"
					value="#{certificationReportMB.certificationList}" var="row" styleClass="tableStyle">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Certification Id" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.certificationId}"></h:outputText>

					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Test Date" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.testDate}">
							<f:convertDateTime pattern="dd-MMM-yyyy" />
						</h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Trainee Id" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.traineeId}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Trainee Name" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.traineeName}"></h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Date of Joining" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.dateOfJoining}">
							<f:convertDateTime pattern="dd-MMM-yyyy" />
						</h:outputText>
					</h:column>
					<h:column id="column6">
						<f:facet name="header">
							<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.courseName}"></h:outputText>
					</h:column>
					<h:column id="column7">
						<f:facet name="header">
							<h:outputText value="Course Level" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.courseLevel}"></h:outputText>
					</h:column>
					<h:column id="column8">
						<f:facet name="header">
							<h:outputText value="Course Type" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{row.courseType}"></h:outputText>
					</h:column>
				</h:dataTable>
			</c:when>
			<c:otherwise>
				<h:outputText value="#{certificationReportMB.message}" styleClass="error"></h:outputText>
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
</f:view>

</body>
</html>