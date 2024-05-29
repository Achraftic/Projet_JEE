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
           
       <c:choose>   
         
    




 <c:when test="${empty users}">
        <p>pas de users</p>
    </c:when>
<c:otherwise>
  </div>
               <h5>nombre de users : ${emprunts.size() }   </h6>
         </div>
        
     <div style="overflow-x:auto;">
              <table>
                <tr>
                  <th>Id</th>
                  <th>Nom Complet </th>
                  <th>Email</th>
               
                  <th>Action</th>
                </tr>
                
                 <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.nom} ${user.prenom }</td>
                                         <td>${user.email}</td>
                                      
       
                                   <td style="display:flex; align-items:center; gap:10px">
                                    
                                     <form action="" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                   <button type="submit">
                     <i class="fa-solid fa-trash"></i>
                   </button>
                    </form>
                                        </td>
                                    </tr>
                                </c:forEach>
              </table>
            </div>
            
            <!-- SELECT  nom, COUNT(emprunt.id_user) FROM `user` left join emprunt on user.id=emprunt.id_user
where emprunt.Date_Retour_Réelle IS NULL and  user.role='user'
GROUP by (nom) -->
</c:otherwise>
</c:choose>

         
       
            
       
          
          </div>
        </div>
      </div>
    </div>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> -->
<script src="${pageContext.request.contextPath}/js/sidebar.js"></script>

</body>
</html>
