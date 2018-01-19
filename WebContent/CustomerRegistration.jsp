<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<h:form styleClass="forms" style="position:relative;">
<h:panelGrid border="0" columns="4" styleClass="tableStyle" cellpadding="4">
			
		<h:outputLink value="home.jsp">Home</h:outputLink>
		<h:outputLink value="ViewCourses.jsp">Courses</h:outputLink>
		<h:outputLink value="CustomerRegistration.jsp">SignUp</h:outputLink>
		<h:outputLink value="ViewRegistrationStatus.jsp">View Registration Status</h:outputLink>
			
		</h:panelGrid>
</h:form><br><br>
<br>
<h:form styleClass="forms">
	<br><br>
	<h2>SignUp</h2>
	<h:panelGrid border="1" columns="3" styleClass="tableStyle">
			<h:outputText value="First Name*"></h:outputText>
			<h:inputText id="firstname" value="#{customerRegistrationMB.firstName}"required="true" requiredMessage="FirstName Is mandatory" >
			 
			<f:validator validatorId="nameValidator"/>
			</h:inputText>
			
			<h:message for="firstname" styleClass="error" ></h:message>
			
			<h:outputText value="Last Name*"></h:outputText>
			<h:inputText id="lastname" required="true" value="#{customerRegistrationMB.lastName}"  requiredMessage="LastName Is mandatory">
			
			<f:validator validatorId="nameValidator"/>
			</h:inputText>
			
			<h:message for="lastname"  styleClass="error"></h:message>
			
			<h:outputText value="Email Id*"></h:outputText>
			<h:inputText id="email" required="true" value="#{customerRegistrationMB.emailID}"   requiredMessage="Email Is mandatory">
			
			<f:validator validatorId="emailValidator"/>
			</h:inputText>
			
			<h:message for="email" styleClass="error" ></h:message>
			
			<h:outputText value="Gender*"></h:outputText>
			<h:selectOneRadio value="#{customerRegistrationMB.gender}" required="true" requiredMessage="Gender Is mandatory" id="gender">
			<f:selectItem  itemLabel="Male" itemValue="M" />
			<f:selectItem itemLabel="Female" itemValue="F"/>
			
			
			</h:selectOneRadio>
			
			
			<h:message for="gender" styleClass="error" ></h:message>
			
			<h:outputText value="Date Of Birth(dd-MMM-yyyy)*"></h:outputText>
			<h:inputText id="dob" required="true" value="#{customerRegistrationMB.dateOfBirth}"  requiredMessage="Date Of Birth Is mandatory"  converterMessage="Date must be of dd-MMM-yyyy format" validatorMessage="Date should be less than 3 years from current date">
			<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
			<f:validator validatorId="dateOfBirthValidator"/>
			</h:inputText>
			
			<h:message for="dob" styleClass="error" ></h:message>
			
			<h:outputText value="Address*"></h:outputText>
			<h:inputText id="address" required="true" value="#{customerRegistrationMB.address}"  requiredMessage="Address Is mandatory"></h:inputText>
			
			<h:message for="address" styleClass="error"></h:message>
			
			<h:outputText value="Contact No*"></h:outputText>
			<h:inputText id="contactno" required="true" value="#{customerRegistrationMB.contactNumber}"  requiredMessage="ContactNo Is mandatory" converterMessage="Contact no should have only digits"> 
			<f:convertNumber integerOnly="true"/>
			<f:validator validatorId="contactNumberValidator" />
			
			</h:inputText>
			
			<h:message for="contactno" styleClass="error"></h:message>
			<h:outputText value="Occupation*"></h:outputText>
			<h:inputText id="occupation" required="true" value="#{customerRegistrationMB.occupation}"   requiredMessage="Occupation Is mandatory" >
			<f:validator validatorId="occupationValidator"/>
			</h:inputText>
			
			<h:message for="occupation" styleClass="error"></h:message>
			
			<h:outputText value="Company*"></h:outputText>
			<h:inputText id="company" required="true" value="#{customerRegistrationMB.company}"   requiredMessage="Company Is mandatory" >
			
			</h:inputText>
			
			<h:message for="company" styleClass="error"></h:message>

			<h:commandButton value="Register" action="#{customerRegistrationMB.registerCustomer}" ></h:commandButton>
			<h:commandButton value="Reset"  type="reset"  ></h:commandButton>

		</h:panelGrid>
		<br>
		<h:outputText styleClass="error"    value="#{customerRegistrationMB.message}"></h:outputText>




	</h:form>
	

	

<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>