<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
		<br>
		<br>
<h1>View Details</h1>

		<c:choose>
			<c:when test="${loginMB.role=='T'}">
				<h:panelGrid border="1" columns="2" styleClass="tableStyle" cellpadding="6">
					<h:outputText value="Name"></h:outputText>
					<h:outputText value="#{traineeMB.traineeName}"></h:outputText>
					<h:outputText value="Gender"></h:outputText>
					<h:outputText value="#{traineeMB.gender}"></h:outputText>
					<h:outputText value="DateOfBirth"></h:outputText>
					<h:outputText value="#{traineeMB.dateOfBirth}">
						<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
					</h:outputText>
					<h:outputText value="DateOfJoining"></h:outputText>
					<h:outputText value="#{traineeMB.dateOfJoining}">
						<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
					</h:outputText>
					<h:outputText value="email Id"></h:outputText>
					<h:outputText value="#{traineeMB.emailId}"></h:outputText>
					<h:outputText value="Address"></h:outputText>
					<h:outputText value="#{traineeMB.address}"></h:outputText>
					<h:outputText value="Contact No"></h:outputText>
					<h:outputText value="#{traineeMB.contactNumber}"></h:outputText>

				</h:panelGrid>
			</c:when>

			<c:when test="${loginMB.role=='I'}">
				<h:panelGrid border="1" columns="2" styleClass="tableStyle">
					<h:outputText value="Name"></h:outputText>
					<h:outputText value="#{instructorMB.instructorName}"></h:outputText>
					<h:outputText value="Date Of Joining"></h:outputText>
					<h:outputText value="#{instructorMB.dateOfJoining}">
						<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
					</h:outputText>
					<h:outputText value="Address"></h:outputText>
					<h:outputText value="#{instructorMB.address}"></h:outputText>
					<h:outputText value="Contact No"></h:outputText>
					<h:outputText value="#{instructorMB.contactNumber}"></h:outputText>
					<h:outputText value="Courses Taken"></h:outputText>
					<h:dataTable value="#{instructorMB.courseDetails}" var="c"
						styleClass="tableStyle">
						<h:column id="column1">
							<f:facet name="header">
								<h:outputText value="Course ID" styleClass="tableHeaderStyle"></h:outputText>
							</f:facet>
							     <h:outputText value="#{c.courseId}"></h:outputText>
						</h:column>
						<h:column id="column2">
							<f:facet name="header">
								<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
							</f:facet>
							    <h:outputText value="#{c.courseName}"></h:outputText>
						</h:column>
					</h:dataTable>
				</h:panelGrid>

			</c:when>
			<c:when test="${loginMB.role=='A'}">
				
				<h:panelGrid border="1" columns="2" styleClass="tableStyle">
					<h:outputText value="View Details Of"></h:outputText>
					<h:selectOneRadio value="#{viewDetailsMB.choice}"
						valueChangeListener="#{viewDetailsMB.getList}" onclick="submit();">
						<f:selectItem itemLabel="Trainee" itemValue="1" />
						<f:selectItem itemLabel="Instructor" itemValue="2" />
					</h:selectOneRadio>
				</h:panelGrid>
               <br>
				<c:if test="${viewDetailsMB.choice==1}">
					<h:panelGrid border="1" columns="2" styleClass="tableStyle">
						<h:outputText value="Select Trainee Name"></h:outputText>
						<h:selectOneMenu value="#{traineeMB.traineeId}"
							valueChangeListener="#{traineeMB.getTraineeDetail}"
							onchange="submit();">
							<f:selectItem itemLabel="select" itemValue="" />
							<f:selectItems value="#{viewDetailsMB.traineeList}" />
						</h:selectOneMenu>
					</h:panelGrid>
					<br>
					<c:if test="${traineeMB.traineeId!=null}">
					<h:panelGrid border="1" columns="2" styleClass="tableStyle">
						<h:outputText value="Name"></h:outputText>
						<h:outputText value="#{traineeMB.traineeName}"></h:outputText>
						<h:outputText value="Gender"></h:outputText>
						<h:outputText value="#{traineeMB.gender}"></h:outputText>
						<h:outputText value="DateOfBirth"></h:outputText>
						<h:outputText value="#{traineeMB.dateOfBirth}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						</h:outputText>
						<h:outputText value="DateOfJoining"></h:outputText>
						<h:outputText value="#{traineeMB.dateOfJoining}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						</h:outputText>
						<h:outputText value="email Id"></h:outputText>
						<h:outputText value="#{traineeMB.emailId}"></h:outputText>
						<h:outputText value="Address"></h:outputText>
						<h:outputText value="#{traineeMB.address}"></h:outputText>
						<h:outputText value="Contact No"></h:outputText>
						<h:outputText value="#{traineeMB.contactNumber}"></h:outputText>
					</h:panelGrid>
					</c:if>
				</c:if>
				
				<c:if test="${viewDetailsMB.choice==2}">
					<h:panelGrid border="1" columns="2" styleClass="tableStyle">
						<h:outputText value="Select Instructor Name"></h:outputText>
						<h:selectOneMenu value="#{instructorMB.instructorId}"
							valueChangeListener="#{instructorMB.getInstructorDetail}"
							onchange="submit();">
							<f:selectItem itemLabel="select" itemValue="" />
							<f:selectItems value="#{viewDetailsMB.instructorList}" />
						</h:selectOneMenu>
					</h:panelGrid>
					<br>
                   	<c:if test="${instructorMB.instructorId!=null}">
					<h:panelGrid border="1" columns="2" styleClass="tableStyle">
						<h:outputText value="Name"></h:outputText>
						<h:outputText value="#{instructorMB.instructorName}"></h:outputText>

						<h:outputText value="Date Of Joining"></h:outputText>
						<h:outputText value="#{instructorMB.dateOfJoining}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						</h:outputText>
						<h:outputText value="Address"></h:outputText>
						<h:outputText value="#{instructorMB.address}"></h:outputText>
						<h:outputText value="Contact No"></h:outputText>
						<h:outputText value="#{instructorMB.contactNumber}"></h:outputText>
						<h:outputText value="Courses Taken"></h:outputText>
						<h:dataTable value="#{instructorMB.courseDetails}" var="c"
							styleClass="tableStyle">
							<h:column id="column1">
								<f:facet name="header">
									<h:outputText value="Course ID" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{c.courseId}"></h:outputText>
							</h:column>

							<h:column id="column2">
								<f:facet name="header">
									<h:outputText value="Course Name" styleClass="tableHeaderStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{c.courseName}"></h:outputText>
							</h:column>
						</h:dataTable>
					</h:panelGrid>
					</c:if>
				</c:if>
			</c:when>
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