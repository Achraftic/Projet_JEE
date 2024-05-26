<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/global.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/home.css" />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/style/fontawesome-free-6.5.2-web/css/all.min.css"
    />
  </head>
  <body>
    <%@ include file="/user/phoneNav.jsp" %>
    <div class="container">
   <%@ include file="/user/header.jsp" %>

      <div class="main">
        <div class="sidebar">
          <form class="search_container">
            <input type="text" class="sarch" placeholder="search.." />
            <button type="submit" class="searchbtn">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 512 512 "
                class="searchIcon"
              >
                <!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
                <path
                  fill="#bdbdbd"
                  d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                />
              </svg>
            </button>
          </form>
          <ul>
            <li><a href="#" class="active">All</a></li>
            <li><a href="#">action</a></li>
            <li><a href="#">aventure</a></li>
            <li><a href="#">Fatasy</a></li>
            <li><a href="#">Sport</a></li>
            <li><a href="#">Social</a></li>
            <li><a href="#">culture</a></li>
          </ul>
        </div>
        <div class="content">
          <div class="contentchild">
          
           <c:forEach items="${livres}" var="livre">
            <div class="livre">
              <div class="imgcontainer">
                <img src="${pageContext.request.contextPath}/uploads/${livre.image}" alt="" />
              </div>
              <div class="info" style="gap:10px; display:flex; flex-direction:column">
                <h4> ${livre.titre} </h4>
                <p>  ${livre.genre}  </p>
                <!-- <p> Nb exemplaire10</p> -->

                <form action="livre" method="get">
                <input type="hidden" name="id" value="${livre.id}">
                  
                  <button type ="submit" > <a class="btn">Detaile</a> </button> 
                </form>
              </div>
            </div>
             </c:forEach>

            

         

           
            
           
          </div>


        </div>
      </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
  </body>
</html>