<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
     <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
     <link rel="stylesheet" href="./style/form/register.css">
</head>
<body>


  <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <form action="register" method="post">
        <h3>Register</h3>
        <div class="flex">
         
        <input type="text" name="nom" placeholder="nom" id="username">
        
        <input type="text" name="prenom" placeholder="nom" id="username">
        
        </div>
       
        <input type="email" name="email" placeholder="nom" id="username">

      
        <input type="password" name="confirmpassword" placeholder="********" id="password">
        
        
        <input type="password" name="password" placeholder="********" id="password">
        
 <% String message = (String)request.getAttribute("errorMessage");
    if (message != null && !message.isEmpty()) { %>
      <p class="errormessage" style="color: red; text-align: center; margin-top: 20px; font-size:14px;"><%= message %></p>

    <% } %>
        <button type="submit">Register</button>
        
    </form>



    
</body>
</html>
