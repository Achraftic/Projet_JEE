<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vue</title>
</head>
<body>
<h1><%= ((User) session.getAttribute("user")).getNom() %></h1>
<h1> <%= ((User) session.getAttribute("user")).getPrenom() %></h1>

<c:if test="${not empty sessionScope.user}">





 <form action="ServletDeconnecter" method="post" >
   <button type ="submit">Logout</button>
 </form>
  
    
</c:if>
</body>
</html>
