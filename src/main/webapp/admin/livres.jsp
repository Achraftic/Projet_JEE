<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=
    , initial-scale=1.0"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/global.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dashboard/dashboard.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dashboard/livre.css">
    <!-- <link rel="stylesheet" href="../style/dashboard/ajouter.css"> -->
    
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/style/fontawesome-free-6.5.2-web/css/all.min.css"
    />
    <title>Document</title>
  </head>
<body>
  <div class="countainer closed">
    <%@ include file="/admin/side.jsp" %>

      <div class="main">
        <div>
          <%@ include file="/admin/nav.jsp" %>
          <div class="content">
         <div class="topTable">
            <div class="btndash">
            <a href="ServletAjouterLivre">ajouter Livre  <i class="fa-solid fa-plus"></i></a>
          
           </div>
               <h6>nombre de livres : ${livres.size() }   </h6>
         </div>
        
    


         
            <div style="overflow-x:auto;">
              <table>
                <tr>
                  <th>Id</th>
                  <th>Image</th>
                  <th>ISBN</th>
                  <th>Nom Livre</th>
                  <th>Auteur</th>
                  <th>nb exemplaire</th>
                  <th>disponible</th>
                  <th >action</th>
                </tr>
                
                 <c:forEach items="${livres}" var="livre">
                <tr>
                  <td>${livre.id}</td>
                  <td><img src="uploads/${livre.image}" alt="Image du livre" style="width:30px" /></td> 
                 <th>${livre.ISBN}</th>
                  <th>${livre.titre}</th>
                  <th>${livre.auteur}</th>
                  <th>${livre.nbExemplaire}</th>
                  <th class="etat">  <c:choose>
                    <c:when test="${livre.disponible}">
                         <span class="true">true</span>  
                    </c:when>
                    <c:otherwise>
                       <span class="false">false</span> 
                    </c:otherwise>
                </c:choose></th>
                  <td class="action">
                  <form action="ServletLivreModifier" method="get">
                    <input type="hidden" name="id" value="${livre.id}">
                   <button type="submit">
                    <i class="fa-solid fa-pen-to-square"></i>
                   </button>
                    </form>
                  
                   
                    
                    <form action="ServleLivreSupprimer" method="post">
                    <input type="hidden" name="id" value="${livre.id}">
                   <button type="submit">
                     <i class="fa-solid fa-trash"></i>
                   </button>
                    </form>
                  
                  <form action="ServletDetailLivre" method="get">
                    <input type="hidden" name="id" value="${livre.id}">
                   <button type="submit">
                    <i class="fa-solid fa-eye"></i>
                   </button>
                    </form>
                  
                   
                    
                  </td>
                </tr>
                </c:forEach>
              </table>
            </div>
            
       
          
          </div>
        </div>
      </div>
    </div>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> -->
<script src="${pageContext.request.contextPath}/js/sidebar.js"></script>

</body>
</html>
