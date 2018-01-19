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

</HEAD>
<BODY>
<img src="images/sidebar.png" class="sidebarPanel" >
<img src="images/header.png" class="headerPanel" />
<img src="images/RaagaLogo.png" class="logo" >
<img src="images/separator.png" class="separatorHeader" />
<img src="images/RaagaSubtitle.png" class="subtitle" />
<f:view>


<h:panelGrid border="0" columns="3" styleClass="toolbar" cellpadding="4">

                  <h:outputLink value="ViewCourses.jsp">Courses</h:outputLink>

                  <h:outputLink value="CustomerRegistration.jsp">SignUp</h:outputLink>

                  <h:outputLink value="ViewRegistrationStatus.jsp">ViewRegistrationStatus</h:outputLink>

        </h:panelGrid>
<h:form styleClass="forms">

            

     

      <h1 align="center">About Us</h1>

      <p align="justify" style="left: 60%; width: 551px">RAAGA Institute of Fine Arts

      is an International Institute that provides traditional music and dance

      training services like guitar,jazz,violin and classical and modern

      dance for the corporate clients.<br>

      <br>

      RAAGA INSTITUTE OF FINE ARTS Ltd consists of business operations like

      trainee registration,training and certification,Instructor Leave

      Application,batch launching and scheduling.The company has many

      execution plans which will Increase their growth in terms of revenue

      and brand values in the next few years.One of the plans is to automate

      the above business functions for speeding up the service. The

      automation provides user friendly and rich GUI Interface to its

      customer and internal users.Trainees can get their course completion

      certificates,online.Internal user like Admin can generate various

      reports like Instructor report,course details report,certification

      report, and trainee performance report online.</p><br><br><br>



      <center><jsp:include page="footer.jsp"></jsp:include></center>

      </h:form>

</f:view>

</body>

</html>
