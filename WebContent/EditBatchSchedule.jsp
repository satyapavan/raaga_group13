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
</HEAD>
<BODY onload="noBack();" onunload="">
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
		<h2>Edit Batch Schedule</h2>
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="Select Batch"></h:outputText>
			<h:selectOneMenu id="batch" value="#{batchDetailsMB.batchId}"
				valueChangeListener="#{batchDetailsMB.getBatchIdDetails}" onchange="submit();"
				required="true" requiredMessage="Select Any One Batch" >
				
				<f:selectItem itemLabel="select" itemValue="" />
				<f:selectItems value="#{batchDetailsMB.batchIdList}" />
			</h:selectOneMenu>
			<h:message for="batch"></h:message>
		</h:panelGrid>
		<c:if test="${not empty batchDetailsMB.batchList}">
			<br>
			<h:dataTable border="1" value="#{batchDetailsMB.batchList}" var="b"
				styleClass="tableStyle">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputText value="Batch ID" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.batchId}"></h:outputText>
				</h:column>
				<h:column id="column2">
					<f:facet name="header">
						<h:outputText value="Batch Strength" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.batchStrength}"></h:outputText>
				</h:column>
				<h:column id="column3">
					<f:facet name="header">
						<h:outputText value="Venue Id" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.venueId}"></h:outputText>
				</h:column>
				<h:column id="column4">
					<f:facet name="header">
						<h:outputText value="Instructor Id" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.instructorId}"></h:outputText>
				</h:column>
				<h:column id="column5">
					<f:facet name="header">
						<h:outputText value="Start Date" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.startDate}">
						<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
					</h:outputText>
				</h:column>
				<h:column id="column6">
					<f:facet name="header">
						<h:outputText value="End Date" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.batchEndDate}">
						<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
					</h:outputText>
				</h:column>
				<h:column id="column7">
					<f:facet name="header">
						<h:outputText value="Closure Date" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.batchClosureDate}">
						<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
					</h:outputText>
				</h:column>
				<h:column id="column8">
					<f:facet name="header">
						<h:outputText value="Closure Status" styleClass="tableHeaderStyle"></h:outputText>
					</f:facet>
					<h:outputText value="#{b.batchClosureStatus}">
					<f:converter converterId="batchStatusConverter"/>
					</h:outputText>
				</h:column>
				
			
			</h:dataTable>
			<br>
		




<c:if test="${batchDetailsMB.batchType eq 'D'}">


				<h:panelGrid border="1" columns="3" styleClass="tableStyle">
					<h:outputText value="Enter the new start date for the batch"></h:outputText>
					<h:inputText id="startdate"
						value="#{batchDetailsMB.batchStartDate}" required="true"
						requiredMessage="please enter valid date" converterMessage="Date must be of dd-MMM-yyyy format">
						<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
						<f:validator validatorId="vaidateDate" />
						<f:validator validatorId="startDateValidator" />

					</h:inputText>
					<h:message for="startdate" styleClass="error"></h:message>

					<h:outputText value="Change the venue for the batch"></h:outputText>
					<h:selectOneMenu value="#{batchDetailsMB.classRoomId}" id="cid"
						required="true" requiredMessage="Select any one venue">
						<f:selectItem itemLabel="-select-" itemValue="" />
						<f:selectItems value="#{batchDetailsMB.classRoomList}" />
					</h:selectOneMenu>
					
					
					
					
					
					
					
					
					<h:message for="cid" styleClass="error"></h:message>
					<h:commandButton value="Edit Batch"
						action="#{batchDetailsMB.editBatch}" type="submit"></h:commandButton>
					<h:commandButton type="reset" value="Reset"></h:commandButton>
				</h:panelGrid>

					
					
					
					


</c:if>
<c:if test="${batchDetailsMB.batchType eq 'E'}">

				<h:panelGrid border="1" columns="3" styleClass="tableStyle">
					<h:outputText value="Enter the new start date for the batch"></h:outputText>
					<h:inputText id="startdate"
						value="#{batchDetailsMB.batchStartDate}" required="true"
						requiredMessage="please enter valid date">
						<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
						<f:validator validatorId="vaidateDate" />
						<f:validator validatorId="stratDatevalidator2" />

					</h:inputText>
					<h:message for="startdate" styleClass="error"></h:message>

					<h:outputText value="Change the venue for the batch"></h:outputText>
					<h:selectOneMenu value="#{batchDetailsMB.classRoomId}" id="cid"
						required="true" requiredMessage="Select any one venue">
						<f:selectItem itemLabel="-select-" itemValue="" />
						<f:selectItems value="#{batchDetailsMB.classRoomList}" />
					</h:selectOneMenu>
					<h:message for="cid" styleClass="error"></h:message>
					<h:commandButton value="Edit Batch"
						action="#{batchDetailsMB.editBatch}" type="submit"></h:commandButton>
					<h:commandButton type="reset" value="Reset"></h:commandButton>
				</h:panelGrid>
				
				
				
				
 </c:if>
			
			
		</c:if>
		<br><center><h:outputText value="#{batchDetailsMB.message}" styleClass="error"></h:outputText></center>
		<br>
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