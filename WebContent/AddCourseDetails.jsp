<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
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
		</h:form>
		<br>
<h:form styleClass="forms">
 
<br>
		<h1>Add Course Details</h1>
		<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="Course Name"></h:outputText>
			<h:inputText id="cour" required="true" requiredMessage="Course Name is Mandatory" value="#{addCourseDetailsMB.courseName}">
			<f:validateLength minimum="3" maximum="30"/>
			<f:validator validatorId="nameValidator"/>
			</h:inputText>
			<h:message for="cour" styleClass="error"></h:message>
			<h:outputText value="Course Level"></h:outputText>
			<h:inputText id="lev" required="true" requiredMessage="Course Level is Mandatory" value="#{addCourseDetailsMB.courseLevel}">
			<f:validateLength minimum="3" maximum="30"/>
			<f:validator validatorId="nameValidator"/>
			</h:inputText>
			<h:message for="lev" styleClass="error"></h:message>
			<h:outputText value="Course Type"></h:outputText>
			<h:selectOneMenu value="#{addCourseDetailsMB.courseType}" required="true" requiredMessage="Select atleast one option" id="typ">
			<f:selectItem itemLabel="--select--" />
			<f:selectItem itemLabel="Music" itemValue="Music"/>
			<f:selectItem itemLabel="Dance" itemValue="Dance"/>
			</h:selectOneMenu>
			<h:message for="typ" styleClass="error"></h:message>
			<h:outputText value="Certification"></h:outputText>
			<h:selectOneMenu id="certi" value="#{addCourseDetailsMB.certification}" required="true" requiredMessage="Select atleast one option">
			<f:selectItem itemLabel="--select--" />
			<f:selectItem itemLabel="Yes" itemValue="Y"/>
			<f:selectItem itemLabel="No" itemValue="N"/>
			</h:selectOneMenu>
			<h:message for="certi" styleClass="error"></h:message>
			<h:outputText value="Course Duration"></h:outputText>
			<h:inputText id="dur" value="#{addCourseDetailsMB.duration}" required="true" requiredMessage="Course Duration is Mandatory">
			</h:inputText>
			<h:message for="dur" styleClass="error"></h:message>
			<h:outputText value="Course Fee"></h:outputText>
			<h:inputText id="fee" value="#{addCourseDetailsMB.fee}" required="true" requiredMessage="Course Fee is Mandatory" >
			<f:validateDoubleRange minimum="1" maximum="99999"></f:validateDoubleRange>
			</h:inputText>
			<h:message for="fee" styleClass="error"></h:message>
			<h:commandButton value="Add Course" action="#{addCourseDetailsMB.addCourse}"></h:commandButton>
		<h:commandButton value="Reset" type="reset"></h:commandButton>
		</h:panelGrid>
		<h:outputText value="#{addCourseDetailsMB.message}" styleClass="error"></h:outputText>
		

</h:form>


</c:when>
	<c:otherwise>
	<br><br><br><br><br><br><br><br><br><br><br><br>
    <center><h2><font color="red">YOU ARE NOT LOGGED IN YET!!!!</font></h2>
	<h:outputLink value="home.jsp">HOME</h:outputLink></center>
	</c:otherwise>
</c:choose>
<center><jsp:include page="footer.jsp"></jsp:include></center>
</f:view>
</body>
</html>