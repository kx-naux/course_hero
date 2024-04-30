<!-- Navigation Bar -->
<nav class="nav-bar">
    <!-- Logo -->
    <div class="nav-logo-div">
        <a href="<%= webpath.getPageUrl("home")%>"><img class="nav-logo" src="./img/Logo.png" alt="Course Hero" /></a>
    </div>
    <!--Nav options-->
    <div class="nav-option-div">
        <ul>
            <li>
                <a href="<%= webpath.getPageUrl("home")%>">
                    <div class="nav-option">
                        <p>Home</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("about us")%>">
                    <div class="nav-option">
                        <p>About Us <i class="ri-arrow-down-s-line"></i></p>
                    </div>
                </a>
                <div class="nav-option-dropdown">
                    <ul>
                        <li><a href="<%= webpath.getPageUrl("about us")%>#team">Our Team</a></li>
                        <li><a href="<%= webpath.getPageUrl("about us")%>#office">Our Office</a></li>
                        <li><a href="<%= webpath.getPageUrl("about us")%>#contact">Contact Us</a></li>
                    </ul>
                </div>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("products")%>">
                    <div class="nav-option">
                        <p>Courses</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("learning")%>">
                    <div class="nav-option">
                        <p>Learning</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<%= webpath.getPageUrl("merchandise")%>">
                    <div class="nav-option">
                        <p>Merchandise</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <!--Nav button-->
    <div class="nav-function-div">
        <!--Search course-->
        <p class="nav-icon toggle-search"><i class="ri-search-line"></i><span class="nav-icon-tooltip">Search</span></p>
        <!--JSP conditional rendering-->
        <c:choose>
            <c:when test="${not empty sessionScope.username}">
                <!-- Logged in -->
                <a href="<%= webpath.getPageUrl("wishlist")%>">
                    <p class="nav-icon"><i class="ri-heart-line"></i><span class="nav-icon-tooltip">Wishlist</span></P>
                </a>
                <a href="<%= webpath.getPageUrl("cart")%>">
                    <p class="nav-icon"><i class="ri-shopping-cart-line"></i><span class="nav-icon-tooltip">Cart</span></P>
                </a>
                <input hidden type="text" id="hidden_username" value="${sessionScope.username}"/>
            </c:when>
            <c:otherwise>
                <!-- Not Logged in -->
                <a href="<%= webpath.getPageUrl("login")%>">
                    <input class="nav-btn nav-login-btn" type="button" value="Log In" />
                </a>
                <a href="<%= webpath.getPageUrl("sign up")%>">
                    <input class="nav-btn nav-sign-up-btn" type="button" value="Sign Up" />
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>         
<div class="nav-search">
    <div class="nav-search-div-bg">
        <div class="nav-search-div flex-col">
            <div class="nav-search-box flex-row">
                <p><i class="ri-search-line"></i></p>
                <form class="nav-search-form flex-row" action="/course_hero/search" method="get" >
                    <input type="text" id="nav-search-input" name="query" placeholder="Search for courses" />
                </form>
            </div>
            <div class="nav-search-result flex-col">
<!--                <a onclick="search_suggestion_click(this)">
                    <div class="nav-search-suggestion-query flex-row">
                        <p class="suggest-icon"><i class="ri-search-line"></i></p>
                        <p class="suggest-query">Python</p>
                    </div>
                </a>
                <a onclick="search_suggestion_click(this)">
                    <div class="nav-search-suggestion-query flex-row">
                        <p class="suggest-icon"><i class="ri-search-line"></i></p>
                        <p class="suggest-query">Python</p>
                    </div>
                </a>
                <a onclick="search_suggestion_click(this)">
                    <div class="nav-search-suggestion-query flex-row">
                        <p class="suggest-icon"><i class="ri-search-line"></i></p>
                        <p class="suggest-query">Python</p>
                    </div>
                </a>
                <a onclick="search_suggestion_course_click(this)">
                    <div class="nav-search-suggestion-course flex-row" courseID="121238719823">
                        <img src="./img/course/beginner_excel.jpg" alt="" />
                        <div class="suggestion-course-detail flex-col">
                            <h1>The Ultimate Excel Programming Course</h1>
                            <p>Woo Yu Beng</p>
                        </div>
                    </div>
                </a>
                <a onclick="search_suggestion_course_click(this)">
                    <div class="nav-search-suggestion-course flex-row" courseID="121238719823">
                        <img src="./img/course/beginner_excel.jpg" alt="" />
                        <div class="suggestion-course-detail flex-col">
                            <h1>The Ultimate Excel Programming Course</h1>
                            <p>Woo Yu Beng</p>
                        </div>
                    </div>
                </a>
                <a onclick="search_suggestion_course_click(this)">
                    <div class="nav-search-suggestion-course flex-row" courseID="121238719823">
                        <img src="./img/course/beginner_excel.jpg" alt="" />
                        <div class="suggestion-course-detail flex-col">
                            <h1>The Ultimate Excel Programming Course</h1>
                            <p>Woo Yu Beng</p>
                        </div>
                    </div>
                </a>-->
            </div>
            <div class="nav-popular-search flex-col">
                <h1>Popular searches</h1>
                <ul class="flex-row">
                    <li><a onclick="popular_search_click(this)">Data Science</a></li>
                    <li><a onclick="popular_search_click(this)">Python</a></li>
                    <li><a onclick="popular_search_click(this)">Software Engineering</a></li>
                    <li><a onclick="popular_search_click(this)">Java Swing</a></li>
                    <li><a onclick="popular_search_click(this)">Web Development</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="./js/nav.js"></script> 
