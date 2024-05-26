<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dashboard/livre.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dashboard/ajouter.css" />
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
         
              <form action="/projet/ServletAjouterLivre" method="post" enctype="multipart/form-data">
              <div class="inputs">
                <input class="input" placeholder="Nom Livre" name="titre" />
                <input class="input" placeholder="ISBN"  name="ISBN"/>
                <input class="input" placeholder="auteur"  name="auteur"/>
                <input
                  type="number"
                  class="input"
                  placeholder="Nb Exemplaire"
                  name="nb_exemplaire"
                />
                                 <input class="input genre" placeholder="genre"  name="genre"/>
                
              </div>
              <div class="field">
                <textarea
                  name="description"
                  class="input"
                  id=""
                  cols="100"
                  rows="10"
                  placeholder="Description"
                ></textarea>
              </div>
              <div class="img_ajouter">
                <img id="imgprv" alt="" />
              </div>
              <label class="custum-file-upload" for="file">
                <div class="icon">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill=""
                    viewBox="0 0 24 24"
                  >
                    <g stroke-width="0" id="SVGRepo_bgCarrier"></g>
                    <g
                      stroke-linejoin="round"
                      stroke-linecap="round"
                      id="SVGRepo_tracerCarrier"
                    ></g>
                    <g id="SVGRepo_iconCarrier">
                      <path
                        fill=""
                        d="M10 1C9.73478 1 9.48043 1.10536 9.29289 1.29289L3.29289 7.29289C3.10536 7.48043 3 7.73478 3 8V20C3 21.6569 4.34315 23 6 23H7C7.55228 23 8 22.5523 8 22C8 21.4477 7.55228 21 7 21H6C5.44772 21 5 20.5523 5 20V9H10C10.5523 9 11 8.55228 11 8V3H18C18.5523 3 19 3.44772 19 4V9C19 9.55228 19.4477 10 20 10C20.5523 10 21 9.55228 21 9V4C21 2.34315 19.6569 1 18 1H10ZM9 7H6.41421L9 4.41421V7ZM14 15.5C14 14.1193 15.1193 13 16.5 13C17.8807 13 19 14.1193 19 15.5V16V17H20C21.1046 17 22 17.8954 22 19C22 20.1046 21.1046 21 20 21H13C11.8954 21 11 20.1046 11 19C11 17.8954 11.8954 17 13 17H14V16V15.5ZM16.5 11C14.142 11 12.2076 12.8136 12.0156 15.122C10.2825 15.5606 9 17.1305 9 19C9 21.2091 10.7909 23 13 23H20C22.2091 23 24 21.2091 24 19C24 17.1305 22.7175 15.5606 20.9844 15.122C20.7924 12.8136 18.858 11 16.5 11Z"
                        clip-rule="evenodd"
                        fill-rule="evenodd"
                      ></path>
                    </g>
                  </svg>
                </div>

                <div class="text">
                  <span id="filename">Click to upload image</span>
                </div>
                <input type="file" id="file" name="file" onchange="previewImage(event) " />
              </label>

              <div class="btn">
                <button type="submit">Ajouter</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> -->
    <script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
    <script>
      function previewImage(event) {
        const file = event.target.files[0];
        if (file) {
          const reader = new FileReader();
          reader.onload = function (e) {
            document.getElementById("imgprv").src = e.target.result;
          };
          reader.readAsDataURL(file);
        }
      }
      window.onload = function () {
        // Pre-populate file input with the current image URL
        document.getElementById("file").value =
          "/img/3afb3c491fbec34c7419f1264faf414b.jpg";
      };
    </script>
  </body>
</html>