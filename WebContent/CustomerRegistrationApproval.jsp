<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css"/>
<jsp:include page="header.jsp"></jsp:include>

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
<h2 style="heading"> CustomerRegistrationApproval</h2>
<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Pending Registrations"></h:outputText>
			<h:selectOneMenu value="#{customerRegistrationApprovalMB.customerRegistrationId}" valueChangeListener="#{customerRegistrationApprovalMB.showPendingCustomerDetail}" onchange="submit()">
			<f:selectItem itemLabel="-select-"/>
			<f:selectItems value="#{customerRegistrationApprovalMB.pendingCustomers}"/></h:selectOneMenu>
			
		</h:panelGrid>
		<br>
		<br>
	<c:choose>
	<c:when test="${not empty customerRegistrationApprovalMB.customerRegistrationId}">
		<h:panelGrid border="1" columns="2" styleClass="tableStyle">
			<h:outputText value="Registration Id"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.customerRegistrationId}"></h:outputText>
			<h:outputText value="First Name"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.firstName}"></h:outputText>
			<h:outputText value="Last Name"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.lastName}"></h:outputText>
			<h:outputText value="email Id"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.emailID}"></h:outputText>
			<h:outputText value="Gender"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.gender}"></h:outputText>
			<h:outputText value="Date of Birth"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.dateOfBirth}">
			<f:convertDateTime pattern="dd-MMM-yyyy"/>
			</h:outputText>
			<h:outputText value="Address"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.address}"></h:outputText>
			<h:outputText value="Contact No"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.contactNo}"></h:outputText>
			<h:outputText value="Occupation"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.occupation}"></h:outputText>
			<h:outputText value="Company"></h:outputText>
			<h:outputText value="#{customerRegistrationApprovalMB.company}"></h:outputText>
			<h:commandButton value="Approve" action="#{customerRegistrationApprovalMB.approveRegistration}"></h:commandButton>
			<h:panelGrid border="0" columns="3">
				<h:outputText value="Reason For The Rejection"></h:outputText>
				<h:inputText id="reject" value="#{customerRegistrationApprovalMB.reasonForRejection}" ></h:inputText>
				<h:commandButton value="Reject" action="#{customerRegistrationApprovalMB.rejectCustomerApplication}"></h:commandButton>
	
			</h:panelGrid>
			
		</h:panelGrid>
		
		</c:when>
		<c:otherwise></c:otherwise>
		</c:choose>
		<h:outputText styleClass="error" value="#{customerRegistrationApprovalMB.message}"></h:outputText>
		

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