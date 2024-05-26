<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ page import="models.User" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/global.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/home.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/fontawesome-free-6.5.2-web/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/unLivre.css">
  </head>
  <body>
    <%@ include file="/user/phoneNav.jsp" %>
    <div class="container">
   <%@ include file="/user/header.jsp" %>
 
   
      <!-- <a href="#" class="back"> <span><i class="fa-solid fa-angle-left"></i> </span><span>retour</span></a> -->
        <div class="flexContainer">
        

            <div class="image">
              <img src="${pageContext.request.contextPath}/uploads/${livre.image}" alt="" />
            </div>
            <div class="text">
              <h1> ${livre.titre} </h1>
              <p> <span>description : </span> 
              ${livre.description}
              </p>
              <div class="listinfo">
                    <p> <span> Auteur : </span> ${livre.auteur}</p>
                    <p> <span> ISBN : </span>  ${livre.ISBN}</p>
                    <p> <span> genre : </span>  ${livre.genre}</p>
                    <p> <span> Etat : </span> 
            <c:choose>
             <c:when test="${livre.disponible}">
                    Disponible
                    
                    </c:when> 
                    <c:otherwise>
                    Non Disponible
                    </c:otherwise>
            </c:choose>
                   
                    
                    
                     </p>
    
                 

              </div>
                <c:choose>
             <c:when test="${livre.disponible}">
                  <button type="submit "  onclick="window.dialog.showModal();">Emprunter</button>
                    
                    </c:when> 
                    <c:otherwise>
                 <button>Reserver</button>
                    </c:otherwise>
            </c:choose>
                   
           
              
            </div>
    
        </div>


<dialog id="dialog">
<form class="formEmprunte" action="demander" method="get">
    <div>
        <label for="Nom"><%= ((User) session.getAttribute("user")).getNom() %></label>
        <input type="hidden" name="id_user" class="input" value="<%= ((User) session.getAttribute("user")).getId() %>">
    </div>

    <div>
        <label for="Nom">${livre.titre}</label>
        <input type="hidden" class="input" name="ISBN" value="${livre.ISBN}">
    </div>
   
    <div>
        <label for="date_retour">Date de retour prévue</label>
        <input type="date" class="input" name="date_retour_prevue" placeholder="Date de retour prévue">
    </div>

    <button type="submit" class="submit">Confirmer</button>
</form>

	<button onclick="window.dialog.close();" aria-label="close" class="x">  <i class="fa-solid fa-xl fa-close"></i> </button>
</dialog>


    </div>


    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
  </body>
</html>