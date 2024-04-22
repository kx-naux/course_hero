<!-- Navigation Bar -->
<nav class="nav-bar">
    <!-- Logo -->
    <div class="nav-logo-div">
        <a href="<%= webpath.getPageUrl("home") %>"><img class="nav-logo" src="./img/Logo.png" alt="Course Hero" /></a>
    </div>
    <!--Nav options-->
    <div class="nav-option-div">
        <ul>
            <li>
                <a href="<%= webpath.getPageUrl("home") %>">
                    <div class="nav-option">
                        <p>Home</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("about us") %>">
                    <div class="nav-option">
                        <p>About Us <i class="ri-arrow-down-s-line"></i></p>
                    </div>
                </a>
                <div class="nav-option-dropdown">
                    <ul>
                        <li><a href="<%= webpath.getPageUrl("about us") %>#team">Our Team</a></li>
                        <li><a href="<%= webpath.getPageUrl("about us") %>#office">Our Office</a></li>
                        <li><a href="<%= webpath.getPageUrl("about us") %>#contact">Contact Us</a></li>
                    </ul>
                </div>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("products") %>">
                    <div class="nav-option">
                        <p>Courses</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("learning") %>">
                    <div class="nav-option">
                        <p>Learning</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <!--Nav button-->
    <div class="nav-function-div">
        <!--Search course-->
        <p class="nav-icon"><i class="ri-search-line"></i></p>
        <div>

        </div>
        <!--JSP conditional rendering-->
        <c:choose>
            <c:when test="${not empty sessionScope.username}">
                <!-- Logged in -->
                <a href="<%= webpath.getPageUrl("wishlist") %>">
                    <p class="nav-icon"><i class="ri-heart-line"></i></P>
                </a>
                <a href="<%= webpath.getPageUrl("cart") %>">
                    <p class="nav-icon"><i class="ri-shopping-cart-line"></i></P>
                </a>
                <input hidden type="text" id="hidden_username" value="${sessionScope.username}"/>
            </c:when>
            <c:otherwise>
                <!-- Not Logged in -->
                <a href="<%= webpath.getPageUrl("login") %>">
                    <input class="nav-btn nav-login-btn" type="button" value="Log In" />
                </a>
                <a href="<%= webpath.getPageUrl("sign up") %>">
                    <input class="nav-btn nav-sign-up-btn" type="button" value="Sign Up" />
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>         
                  
<script src="./js/nav.js"></script> 
