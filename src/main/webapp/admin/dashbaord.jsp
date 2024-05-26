<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet" href="../style/global.css" />
    <link rel="stylesheet" href="../style/dashboard/dashboard.css" />
    <link
      rel="stylesheet"
      href="../style/fontawesome-free-6.5.2-web/css/all.min.css"
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
            <div class="containee_card">
              <div class="cards">
                <span>
                  <i class="fa-regular fa-lg fa-user"></i>
                </span>
                <div>
                  <h3>Users</h3>
                  <p>12</p>
                </div>
              </div>
              <div class="cards">
                <span>
                  <i class="fa-solid fa-lg fa-book"></i>
                </span>
                <div>
                  <h3>Book</h3>
                  <p>12</p>
                </div>
              </div>
              <div class="cards">
                <span>
                  <i class="fa-regular fa-lg fa-address-card"></i>
                </span>
                <div>
                  <h3>Users</h3>
                  <p>12</p>
                </div>
              </div>
              <div class="cards">
                <span>
                  <i class="fa-regular fa-lg fa-user"></i>
                </span>
                <div>
                  <h3>Users</h3>
                  <p>12</p>
                </div>
              </div>
            </div>

            <div class="charts">
              <canvas id="myChart"></canvas>
              <!-- <canvas id="myChart"></canvas> -->
            </div>
           
          
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="../js/dashboard.js"></script>
<script src="../js/sidebar.js"></script>
    
  </body>
</html>