<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<body onload="noBack();" onunload="">
<f:view>
	<jsp:include page="header.jsp"></jsp:include>
	
	<c:choose>
<c:when test="${not empty loginMB.userId}">	

		<%!
char role;
 %>
		<%role=(Character)session.getAttribute("role");%>

		

			<h:form styleClass="forms" style="position:relative;">
<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
			<h:outputText value="Logged in as"/>
			<h:outputText value="#{loginMB.userId}" />
		<h:outputLink value="Roles.jsp">Home</h:outputLink>
			<h:commandLink action="#{loginMB.logout}">Logout</h:commandLink>
		</h:panelGrid>
</h:form>
<br><br><br>
<h:form styleClass="forms">
<br><br>
			<c:if test="${role=='T'}">
			
			<h2>Home Page<br><br>
			
			<h:panelGrid border="1" columns="1" styleClass="tableStyle">

				<h:outputLink value="ViewCourse.jsp">View Courses</h:outputLink>
				<h:outputLink value="ApplyForCourse.jsp">Apply For Course</h:outputLink>
				<h:outputLink value="ViewInstructors.jsp">View Instructors</h:outputLink>
				<h:outputLink value="ViewApplicationStatus.jsp">View Application Status</h:outputLink>
				<h:outputLink value="ViewDetails.jsp">View Your Detail</h:outputLink>
			</h:panelGrid></h2>
	
		</c:if><br><br><br>
		<c:if test="${role=='A'}">
			
			<h2>Home Page<br><br>
			
			<h:panelGrid border="1" columns="1" styleClass="tableStyle">

				<h:outputLink value="BatchSchedule.jsp">Batch Schedule</h:outputLink>
				<h:outputLink value="EditBatchSchedule.jsp">Edit Batch Schedule</h:outputLink>
				<h:outputLink value="batchDetails.jsp">Periodical Batch Report</h:outputLink>
				<h:outputLink value="ViewCourses.jsp">View Courses</h:outputLink>
				<h:outputLink value="ViewCoursedetails.jsp">View Courses-Instructor Details</h:outputLink>
				<h:outputLink value="EditCourseDetails.jsp">Edit Course Details</h:outputLink>
				<h:outputLink value="AddCourseDetails.jsp">Add Course Details</h:outputLink>
				<h:outputLink value="certificationReport.jsp">Certification Report</h:outputLink>
				<h:outputLink value="confirmBatchClosure.jsp">Confirm Batch Closure</h:outputLink>
				<h:outputLink value="courseApplicationApproval.jsp">Course Application Approval</h:outputLink>
				<h:outputLink value="LeaveApplication.jsp">Leave Application</h:outputLink>
				<h:outputLink value="CustomerRegistrationApproval.jsp">Customer Registration Approval</h:outputLink>
				<h:outputLink value="ViewDetails.jsp">View Details</h:outputLink>
				<h:outputLink value="editDetails.jsp">Edit Details</h:outputLink>
				<h:outputLink value="courseApplication.jsp">Course Application</h:outputLink>

			</h:panelGrid></h2>

		</c:if><br><br>
		<c:if test="${role=='I'}">
			
			<h2>Home Page<br><br>
			

			<h:panelGrid border="1" columns="1" styleClass="tableStyle">
				<h:outputLink value="ViewBatchDetails.jsp">View Batch Details</h:outputLink>
				<h:outputLink value="RequestBatchClosure.jsp">Request For Batch Closure</h:outputLink>
				<h:outputLink value="RejectAllocation.jsp">Reject Allocation</h:outputLink>
				<h:outputLink value="ApplyLeave.jsp">Apply Leave</h:outputLink>
				<h:outputLink value="ViewDetails.jsp">View Your Detail</h:outputLink>
			</h:panelGrid></h2>
		</c:if>
		<div align="center"><jsp:include page="footer.jsp"></jsp:include>
		</div>
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