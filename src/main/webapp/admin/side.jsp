    <nav class="sidebar closed">
        <div class="close">  
          <i class="fa-solid fa-angle-left"></i>
        </div>
         
        
        <div class="logo">
          <h3>AdminDash</h3>
        </div>
       
          <ul class="listDash">
            <li>
                <a href="livres " class="<c:if test='${activePage == "dashboard"}'>active</c:if>">
                    <i class="fa-solid fa-sm fa-gauge-high"></i> Dashboard
                </a>
            </li>
            <li>
                <a href="users" class="<c:if test='${activePage == "users"}'>active</c:if>">
                    <i class="fa-solid fa-address-card fa-sm"></i> Users
                </a>
            </li>
            
               <li>
                <a href="ServletLivres"  class="<c:if test='${activePage == "livres"}'>active</c:if>">
                    <i class="fa-solid fa-address-card fa-sm"></i> Livres
                </a>
            </li>
            <li>
                <a href="emprunts" class="<c:if test='${activePage == "emprunts"}'>active</c:if>">
                    <i class="fa-solid fa-user fa-sm"></i> Emprunts
                </a>
            </li>
            <li>
                <a href="demandes" class="<c:if test='${activePage == "demandes"}'>active</c:if>">
                    <i class="fa-solid fa-sm fa-book"></i> Demande
                </a>
            </li>
            <li>
                <a href="reservation" class="<c:if test='${activePage == "reservation"}'>active</c:if>">
                    <i class="fa-solid fa-sm fa-book"></i> Reservation
                </a>
            </li>
        </ul>
        <ul class="listDash2">
          <li>
            <a>
            <form action="ServletDeconnecter" method="post">
              <button type="submit">
               <i class="fa-solid fa-right-to-bracket fa-sm"></i> Logout
              </button>
             </form>
             </a>
               <a href="livres">
            <form>
              <button>
               <i class="fa-solid fa-home fa-sm"></i> Home
              </button>
             </form>
             </a>
             
             
           
             
            
          </li>
        
        </ul>
      </nav>