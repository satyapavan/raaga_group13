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
<title>Raaga MDI LTD</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
<body>
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
		<h1>Course Application</h1>

		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Select Course Id"></h:outputText>
			<h:selectOneMenu value="#{courseApplicationMB.courseId}"
				valueChangeListener="#{courseApplicationMB.getSelectedCourse}"
				onchange="submit()">
				<f:selectItem itemLabel="-select-" />
				<f:selectItems value="#{courseApplicationMB.courseIds}" />
			</h:selectOneMenu>
		</h:panelGrid>
		<br>

	
				<c:choose>
					<c:when test="${not empty courseApplicationMB.applicationTOs and not empty courseApplicationMB.courseId}">
						<h:dataTable border="1"
							value="#{courseApplicationMB.courseDetailsTO}" var="row1"
							styleClass="tableStyle" id="courseDataTable">
							<h:column id="column1">
								<f:facet name="header">
									<h:outputText value="Course Id" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.courseId}"></h:outputText>
							</h:column>
							<h:column id="column2">
								<f:facet name="header">
									<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.courseName}"></h:outputText>
							</h:column>
							<h:column id="column3">
								<f:facet name="header">
									<h:outputText value="Course Level"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.courseLevel}"></h:outputText>
							</h:column>
							<h:column id="column4">
								<f:facet name="header">
									<h:outputText value="Course Type" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.courseType}"></h:outputText>
							</h:column>
							<h:column id="column5">
								<f:facet name="header">
									<h:outputText value="Certification"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.certification}"></h:outputText>
							</h:column>
							<h:column id="column6">
								<f:facet name="header">
									<h:outputText value="Course Duration"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.duration}"></h:outputText>
							</h:column>
							<h:column id="column7">
								<f:facet name="header">
									<h:outputText value="Course Fee" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row1.fee}"></h:outputText>
							</h:column>
						</h:dataTable>
						<br>

						<c:choose>
							<c:when test="${not empty courseApplicationMB.batchTOs}">
								<h:dataTable border="1" styleClass="tableStyle"
									value="#{courseApplicationMB.batchTOs}" var="row"
									id="batchDataTable">
									<h:column id="column1">
										<f:facet name="header">
											<h:outputText value="Batch Id" styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.batchId}"></h:outputText>
									</h:column>
									<h:column id="column2">
										<f:facet name="header">
											<h:outputText value="Batch Strength"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.batchStrength}"></h:outputText>
									</h:column>
									<h:column id="column3">
										<f:facet name="header">
											<h:outputText value="Start Date" id="text3"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.startDate}">
											<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
										</h:outputText>
									</h:column>
									<h:column id="column4">
										<f:facet name="header">
											<h:outputText value="End Date" id="text4"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.batchEndDate}">
											<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
										</h:outputText>
									</h:column>
									<h:column id="column5">
										<f:facet name="header">
											<h:outputText value="Closure Date" id="text5"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.batchClosureDate}">
											<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
										</h:outputText>
									</h:column>
									<h:column id="column6">
										<f:facet name="header">
											<h:outputText value="Closure Status" id="text6"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.batchClosureStatus}"></h:outputText>
									</h:column>
									<h:column id="column7">
										<f:facet name="header">
											<h:outputText value="Batch Type" id="text7"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.batchType}"></h:outputText>
									</h:column>
									<h:column id="column8">
										<f:facet name="header">
											<h:outputText value="ClassRoom Name" id="text8"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.classRoomName}"></h:outputText>
									</h:column>
									<h:column id="column9">
										<f:facet name="header">
											<h:outputText value="Course Name" id="text9"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.courseName}"></h:outputText>
									</h:column>
									<h:column id="column10">
										<f:facet name="header">
											<h:outputText value="Course Level" id="text10"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.courseLevel}"></h:outputText>
									</h:column>
									<h:column id="column11">
										<f:facet name="header">
											<h:outputText value="Instructor Id" id="text11"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.instructorId}"></h:outputText>
									</h:column>
									<h:column id="column12">
										<f:facet name="header">
											<h:outputText value="Instructor Name" id="text12"
												styleClass="tableHeaderStyle"></h:outputText>
										</f:facet>
										<h:outputText value="#{row.instructorName}"></h:outputText>
									</h:column>
								</h:dataTable>
							</c:when>

						</c:choose>
						<br>


						<h:dataTable border="1"
							value="#{courseApplicationMB.applicationTOs}" var="row2" styleClass="tableStyle">
							<h:column id="column1">
								<f:facet name="header">
									<h:outputText value="Application Id"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row2.applicationId}"></h:outputText>
							</h:column>
							<h:column id="column2">
								<f:facet name="header">
									<h:outputText value="Application Status"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row2.applicationStatus}"></h:outputText>
							</h:column>
							<h:column id="column3">
								<f:facet name="header">
									<h:outputText value="Date Of Application"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row2.dateOfApplication}">
									<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
								</h:outputText>
							</h:column>
							<h:column id="column4">
								<f:facet name="header">
									<h:outputText value="Trainee Id" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row2.traineeId}"></h:outputText>
							</h:column>
							<h:column id="column5">
								<f:facet name="header">
									<h:outputText value="Trainee Name"
										styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row2.traineeName}"></h:outputText>
							</h:column>
							<h:column id="column6">
								<f:facet name="header">
									<h:outputText value="Fee Paid" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{row2.feePaid}"></h:outputText>
							</h:column>
							<h:column id="column7">
								<f:facet name="header">
									<h:outputText value="Select" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:selectBooleanCheckbox value="#{row2.checked}"></h:selectBooleanCheckbox>
							</h:column>

						</h:dataTable>
						<br>
						<c:choose>
							<c:when test="${not empty courseApplicationMB.batchTOs}">
								<h:panelGrid columns="3" border="1" styleClass="tableStyle">
									<h:outputText value="Select Status"></h:outputText>
									<h:selectOneMenu value="#{courseApplicationMB.status}"
										id="status" required="true"
										requiredMessage="Please select a status"
										valueChangeListener="#{courseApplicationMB.getSelectedStatus}"
										onchange="submit()">
										<f:selectItem itemLabel="-select-" />
										<f:selectItem itemLabel="Approved" itemValue="A" />
										<f:selectItem itemLabel="Rejected" itemValue="R" />
									</h:selectOneMenu>
									<h:message for="status" styleClass="error"></h:message>
									
									<c:if test="${courseApplicationMB.status eq 'A'}">
									<h:outputText value="Please select a Batch Id"></h:outputText>
									<h:selectOneMenu value="#{courseApplicationMB.batchId}"
										id="batchId" required="true"
										requiredMessage="Batch Id is Mandatory">
										<f:selectItems value="#{courseApplicationMB.availableBatches}" />
									</h:selectOneMenu>
									<h:message for="batchId" styleClass="error"></h:message>
									</c:if>
									<h:commandButton type="submit" value="Submit"
										action="#{courseApplicationMB.approveOrRejectApplication}"></h:commandButton>
									<h:commandButton type="reset" value="Reset"></h:commandButton>
								</h:panelGrid>
							</c:when>
						</c:choose>
					</c:when>

				</c:choose>
			
		<h:outputText value="#{courseApplicationMB.message}"
			styleClass="error"></h:outputText>
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