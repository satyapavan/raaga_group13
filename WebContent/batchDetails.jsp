<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
		
		<h2>Periodical Batch Report</h2>
		<br>
		<h:panelGrid cellpadding="3" cellspacing="5" styleClass="tableStyle"
			border="1" columns="3">
			<h:outputText value="Report Type"></h:outputText>
			<h:selectOneMenu id="i" value="#{batchReportMB.reportType}"
				required="true" requiredMessage="select atleast one option"
				valueChangeListener="#{batchReportMB.getSelectedType}"
				onchange="submit();">
				<f:selectItem itemLabel="Select" />
				<f:selectItem itemValue="M" itemLabel="Monthly" />
				<f:selectItem itemValue="Y" itemLabel="Yearly" />
			</h:selectOneMenu>



			<br>
			<c:if test="${batchReportMB.reportType=='M'}">

				<h:outputText value="Month"></h:outputText>
				<h:selectOneMenu id="j" value="#{batchReportMB.month}"
					required="true" requiredMessage="select atleast one option">
					<f:selectItem itemLabel="Select" />
					<f:selectItems value="#{batchReportMB.months}" />
				</h:selectOneMenu>
				<h:message for="j" styleClass="error"></h:message>

				<h:outputText value="Year"></h:outputText>
				<h:selectOneMenu id="k" value="#{batchReportMB.year}"
					required="true" requiredMessage="select atleast one option">
					<f:selectItem itemLabel="Select" />
					<f:selectItems value="#{batchReportMB.years}" />
				</h:selectOneMenu>
				<h:message for="k" styleClass="error"></h:message>

				<h:commandButton type="submit" value="Get Report"
					action="#{batchReportMB.getBatchDetails}"></h:commandButton>
				<h:commandButton type="reset" value="Reset"></h:commandButton>


			</c:if>

			<c:if test="${batchReportMB.reportType=='Y'}">

				<h:outputText value="Year"></h:outputText>
				<h:selectOneMenu id="l" value="#{batchReportMB.year}"
					required="true" requiredMessage="select atleast one option">
					<f:selectItem itemLabel="Select" />
					<f:selectItems value="#{batchReportMB.years}" />
				</h:selectOneMenu>
				<br>


				<h:commandButton type="submit" value="Get Report"
					action="#{batchReportMB.getBatchDetails}"></h:commandButton>
				<h:commandButton type="reset" value="Reset"></h:commandButton>
			</c:if>
		</h:panelGrid>
		<br>
		
		
		 <c:choose>
			<c:when test="${not empty batchReportMB.batchList}">
				<h:panelGrid border="1" columns="2" styleClass="tableStyle">
					<h:outputText value="Total No Of Batches"></h:outputText>
					<h:outputText value="#{batchReportMB.totalBatchNo}"></h:outputText>
					<h:outputText value="Total Batch Strength"></h:outputText>
					<h:outputText value="#{batchReportMB.totalBatchStrength}"></h:outputText>
				</h:panelGrid>

				<br>
				<div align="right"><br>


				<h:dataTable border="5" styleClass="tableStyle"
					value="#{batchReportMB.batchList}" var="batch">

					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="BatchId" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.batchId}"></h:outputText>
					</h:column>

					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Batch Strength" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.batchStrength}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Start Date" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.startDate}">
							<f:convertDateTime pattern="dd-MMM-yyyy" dateStyle="short" />
						</h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="End Date" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.batchEndDate}">
							<f:convertDateTime pattern="dd-MMM-yyyy" dateStyle="short" />
						</h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Closure Date" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.batchClosureDate}">
							<f:convertDateTime pattern="dd-MMM-yyyy" dateStyle="short" />
						</h:outputText>
					</h:column>
					<h:column id="column6">
						<f:facet name="header">
							<h:outputText value="Closure Status" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.batchClosureStatus}">
						<f:converter converterId="batchStatusConverter"/>   </h:outputText>
					</h:column>
					<h:column id="column7">
						<f:facet name="header">
							<h:outputText value="Batch Type" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.batchType}">
						<f:converter converterId="batchTypeConverter"/></h:outputText>
					</h:column>
					<h:column id="column8">
						<f:facet name="header">
							<h:outputText value="Class Room Name" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.classRoomName}"></h:outputText>
					</h:column>

					<h:column id="column9">
						<f:facet name="header">
							<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.courseName}"></h:outputText>
					</h:column>
					<h:column id="column10">
						<f:facet name="header">
							<h:outputText value="Course Level" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.courseLevel}"></h:outputText>
					</h:column>
					<h:column id="column11">
						<f:facet name="header">
							<h:outputText value="Instructor Id" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.instructorId}"></h:outputText>
					</h:column>
					<h:column id="column12">
						<f:facet name="header">
							<h:outputText value="Instructor Name" styleClass="tableHeaderStyle"></h:outputText>
						</f:facet>
						<h:outputText value="#{batch.instructorName}"></h:outputText>
					</h:column>


				</h:dataTable></div>

			</c:when>
			<c:otherwise>
				<h:outputText value="#{batchReportMB.message}" styleClass="error">
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
	<div align="center"><jsp:include page="footer.jsp"></jsp:include></div>
</f:view>
</body>
</html>