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
         
    




 <c:when test="${empty demandes}">
        <p>pas de demande</p>
    </c:when>
<c:otherwise>
  </div>
               <h5>nombre de demande : ${demandes.size() }   </h6>
         </div>
        
     <div style="overflow-x:auto;">
              <table>
                <tr>
                  <th>Id</th>
                  <th>User</th>
                  <th>Nom Livre</th>
                  <th>ISBN</th>
                
    
                  <th>Date demande</th>
                  <th >Date retour Prevue</th>
                  <th>Action</th>
                </tr>
                
                 <c:forEach items="${demandes}" var="demande">
                                    <tr>
                                        <td>${demande.id}</td>
                                        <td>${demande.user.nom}</td>
                                         <td>${demande.exemplaire.livre.titre}</td>
                                        <td>${demande.exemplaire.ISBN}</td>

                                        <td>${demande.date_demande}</td>
                                      
                                        <td>${demande.date_retour_prevue}</td>
                                        
                                        <td style="display:flex; align-items:center; gap:10px">
                                        <form action="AccepterDemande" method="get">
                                        <input  type="hidden" name="id" value="${demande.id}">
                                        <button  style="color:green">Accepter</button>
                                        </form>
                                         <form action="RefuserDemande" method="get">
                                          <input  type="hidden" name="id" value="${demande.id}">
                                        <button style="color:red">Refuser</button>
                                        </form>
                                        </td>
                                    </tr>
                                </c:forEach>
              </table>
            </div>
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
