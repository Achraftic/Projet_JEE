<style>

li{
font-size:14px;

}
</style>
      <header class="homeHeader">
        <div class="blur"></div>
        <nav class="laptopNav">
          <h1>Logo</h1>
          

          <ul class="link">
            <li>Livres</li>
            <li>Categories</li>
            <li>About Us</li>
            <li>Contact</li>
          </ul>
          <c:choose>
          <c:when test="${not empty sessionScope.user}">
          <form class="formLink" action="ServletDeconnecter" method="post">
            <button type ="submit" class="loginList">  <i class="fa-solid fa-right-to-bracket fa-sm"></i> Logout</button>
           
          </form>
          </c:when>
          <c:otherwise>
           <ul class="formLink">
            <li class="loginList">Login</li>
            <li class="loginList active">Register</li>
          </ul>
          </c:otherwise>
          </c:choose>
         
          

          <div class="burger">
            <i class="fa-solid fa-xl fa-bars-staggered"></i>
            
          </div>
       
        </nav>
      </header>
