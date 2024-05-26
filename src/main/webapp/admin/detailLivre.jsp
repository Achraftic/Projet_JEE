<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dashboard/detailLivre.css">
    
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/style/fontawesome-free-6.5.2-web/css/all.min.css"
    />
    <title>Document</title>
  </head>
  <body>
    <div class="countainer">
      <%@ include file="/admin/side.jsp" %>

      <div class="main">
        <div>
          <%@ include file="/admin/nav.jsp" %>
       

          <div class="content">
         
           <div class="btndash">
       
           <form action="ServletLivreModifier" method="get" style="width:max-content">
                    <input type="hidden" name="id" value="${livre.id}">
                   <button type="submit">
                   <a>Editer livre  <i class="fa-solid fa-pen-to-square"></i></a>
                   </button>
            </form>
           </div>
           
           <div class="livredetail">
            <div class="imglivre">
              <img src="${pageContext.request.contextPath}/uploads/${livre.image}" alt="">

            </div>
             <div class="content">
                 <input type="hidden" name="id" value="${livre.id}">
             
                <p> Titre : <span> ${livre.titre}  </span> </p>
                <p>Auteur : <span> ${livre.auteur } </span> </p>
                <p>nb Exemplaire : <span>  ${livre.nbExemplaire } </span> </p>
                <p class="">etat : <span>disponsible</span> </p>
               
                <p> Description : <span> ${livre.description } </span></p>
                <div class="etatchange">
              <div class="toggle-switch">
                <input class="toggle-input" id="toggle" type="checkbox">
                <label class="toggle-label" for="toggle"></label>
            
              </div>
              <h5>Pret</h5>
             </div>
               

             </div>

           </div>  
           
           <div class="exemplaire" style="display:flex;align-items:center;">
            <h2>
              Exemplaire

            </h2>
            <form class="btndash" method="post" action="AjouterExemplaire" style=" width:max-content ; padding:0px">
             <input type="hidden" name="ISBN"  value="${livre.ISBN }">
             
              <button style=" padding:10px" type ="submit" >Ajouter Exemplaire   <i class="fa-solid fa-plus"></i></button>
            
             </form>
             </div>
            <div style="overflow-x:auto;">
              <table>
              <tr>
                  <th>Id</th>
                  <td style="font-weight: bold;">Etat</td>
                  <td style="font-weight: bold;">Action</td>
                 
                  
                </tr>
                
              <c:forEach items="${exemplaires}" var="exemplaire">
               
                 <tr>
                  <th>${exemplaire.id }</th>
                  <td style="font-weight: bold;"> ${exemplaire.status } </td>
                  <td style="font-weight: bold;"><i class="fa-solid fa-trash"></i></td>
                 
                  
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