<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
  <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
     <link rel="stylesheet" href="./style/form/login.css">
     
</head>
<body>


  <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <form action="login" method="post" >
        <h3>Login</h3>

       
        <input type="email" name="email" placeholder="janfoe@gmail.com" id="username">

       
        <input type="password" name="password" placeholder="********" id="password">
 <% String message = (String)request.getAttribute("errorMessage");
    if (message != null && !message.isEmpty()) { %>
      <p class="errormessage" style="color: red; text-align: center; margin-top: 20px; font-size:14px;"><%= message %></p>

    <% } %>
        <button type="submit">Log In</button>
        
    </form>



</body>
</html>