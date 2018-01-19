<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" type="text/css" rel="stylesheet"></link>
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
<body 
onload="noBack();" onunload="">
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
		<br>
		<br>
	

		<br>
		<h1>Edit Details</h1>
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Edit Details of:"></h:outputText>
			<h:selectOneRadio id="ed" value="#{editDetailsMB.choice}"
				valueChangeListener="#{editDetailsMB.getList}" onclick="submit();">
				<f:selectItem itemLabel="Trainee" itemValue="1" />
				<f:selectItem itemLabel="Instructor" itemValue="2" />
			</h:selectOneRadio>
		</h:panelGrid>
		<br>
		<br>
		<c:if test="${editDetailsMB.choice eq 1}">

			<h:panelGrid border="1" columns="2" styleClass="tableStyle">
				<h:outputText value="Select Trainee Name"></h:outputText>
				<h:selectOneMenu id="one"
					valueChangeListener="#{traineeMB.getTraineeDetail}"
					onchange="submit();" value="#{traineeMB.traineeId}" required="true" requiredMessage="Enter value">
					<f:selectItem itemLabel="-Select-" itemValue="" />
					<f:selectItems value="#{editDetailsMB.traineeList}" />
				</h:selectOneMenu>
			</h:panelGrid>
			
			<c:if test="${not empty traineeMB.traineeName}">
				<h:panelGrid border="1" columns="3" styleClass="tableStyle">
					<h:outputText value="Name"></h:outputText>
					<h:outputText value="#{traineeMB.traineeName}"></h:outputText>
					<h:outputText></h:outputText>
					<h:outputText value="Date oF Birth"></h:outputText>
					<h:inputText value="#{traineeMB.dateOfBirth}" id="d"
						required="true" requiredMessage="Date Of Birth is mandatory" 
						converterMessage="Invalid date format">
						<f:convertDateTime dateStyle="short" pattern="dd-MMM-yyyy" />
						<f:validator validatorId="dateValidator1" />
					</h:inputText>
					<h:message for="d"></h:message>
					<h:outputText value="Company"></h:outputText>
					
					<h:inputText id="company" value="#{traineeMB.company}">
					<f:validator validatorId="companyNameValidator"/>
					</h:inputText>
					<h:message for="company"></h:message>
					
					
					<h:outputText value="Occupation"></h:outputText>
					<h:inputText value="#{traineeMB.occupation}" id="o" required="true"
						requiredMessage="Occupation is mandatory">
						<f:validator validatorId="occupationValidator"/>
						</h:inputText>
					<h:message for="o"></h:message>
					<h:outputText value="Address"></h:outputText>
					<h:inputText value="#{traineeMB.address}" id="a" required="true"
						requiredMessage="Address is mandatory" rendered="true"></h:inputText>
					<h:message for="a"></h:message>
					<h:outputText value="Contact No"></h:outputText>
					<h:inputText value="#{traineeMB.contactNumber}" id="cN" converterMessage="Cannot Contain special charecters"
						required="true" requiredMessage="Contact no is mandatory">
						<f:validator validatorId="mobileNumberValidator"/>
						
						</h:inputText>
					<h:message for="cN"></h:message>
					<h:commandButton value="Save Changes"
						action="#{traineeMB.editTrainee}" type="submit"></h:commandButton>
					<h:outputText></h:outputText>
					<h:outputText></h:outputText>
				</h:panelGrid>
			</c:if>
			<br>
			<br>
			<h:outputText    styleClass="error"  value="#{traineeMB.message}"></h:outputText>
		</c:if>
		<br>
		<br>
		<c:if test="${editDetailsMB.choice eq 2}">

			<h:panelGrid border="1" columns="2" styleClass="tableStyle">
				<h:outputText value="Select Instructor Name"></h:outputText>
				<h:selectOneMenu
					valueChangeListener="#{instructorMB.getInstructorDetail}"
					onchange="submit();" value="#{instructorMB.instructorId}">
					<f:selectItem itemLabel="-Select-" itemValue="" />
					<f:selectItems value="#{editDetailsMB.instructorList}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<br>
			<br>
			<c:if test="${not empty instructorMB.instructorName}">
				<h:panelGrid border="1" columns="3" styleClass="tableStyle">
					<h:outputText value="Name"></h:outputText>
					<h:outputText value="#{instructorMB.instructorName}"></h:outputText>
					<h:outputText></h:outputText>
					
					<h:outputText value="Date Of Joining"></h:outputText>
					<h:outputText value="#{instructorMB.dateOfJoining}">
					<f:convertDateTime pattern="dd-MMM-yyyy" type="date"/>
					</h:outputText>
					<h:outputText></h:outputText>
					<h:outputText value="Address"></h:outputText>
					<h:inputText value="#{instructorMB.address}" id="ad"
						required="true" requiredMessage="Enter Address"></h:inputText>
					<h:message for="ad"></h:message>
					<h:outputText value="Contact No"></h:outputText>
					<h:inputText value="#{instructorMB.contactNumber}" id="conN"
						required="true" requiredMessage="Enter Contact Number" converterMessage="Cannot have special Characters">
						<f:validator validatorId="mobileNumberValidator"/>
						</h:inputText>
					<h:message for="conN"></h:message>
					<h:commandButton value="Save Changes"
						action="#{instructorMB.editInstructor}" type="submit"></h:commandButton>
				</h:panelGrid>
			</c:if>
			<br>
			<br>
			<h:outputText styleClass="error" value="#{instructorMB.message}"></h:outputText>
		</c:if>
<jsp:include page="footer.jsp"></jsp:include>

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