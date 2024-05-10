<%@page import="JPAEntity.Users"%>
<%@page import="JPAEntity.Keyword"%>
<%@page import="JPAEntity.Merchandise"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JPAEntity.CartItems"%>
<%@page import="java.util.Map"%>
<%@page import="JPAEntity.Courses"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
<jsp:useBean id="courseWishlist" class="List<JPAEntity.Courses>" scope="session" />
<jsp:useBean id="userCartItems" class="List<JPAEntity.CartItems>" scope="session" />
<jsp:useBean id="userCartCourse" class="Map<JPAEntity.CartItems, JPAEntity.Courses>" scope="session" />
<jsp:useBean id="userCartMerch" class="Map<JPAEntity.CartItems, JPAEntity.Merchandise>" scope="session" />
<jsp:useBean id="allKeywords" class="List<JPAEntity.Keyword>" scope="application" />
<%double userCartTotal = ((Double) session.getAttribute("userCartTotal")).doubleValue();%>
<!-- Navigation Bar -->
<nav class="nav-bar">
    <!-- Logo -->
    <div class="nav-logo-div">
        <a href="<%= webpath.getPageUrl("home")%>"><img class="nav-logo" src=${companyLogo} alt=${companyName} /></a>
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
            <!--            <li>
                            <a href="<%= webpath.getPageUrl("learning")%>">
                                <div class="nav-option">
                                    <p>Learning</p>
                                </div>
                            </a>
                        </li>-->
            <li>
                <a href="<%= webpath.getPageUrl("merchandises")%>">
                    <div class="nav-option">
                        <p>Merchandises</p>
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
            <c:when test="${not empty userData.accountId.username}">

                <span hidden id="isLogin"></span>

                <%
                    int numberWishlist = courseWishlist.size();
                    int numberCart = userCartItems.size();

                    boolean isWishlistEmpty = numberWishlist <= 0;
                    boolean isCartEmpty = numberCart <= 0;
                %>

                <!-- Logged in -->
                <div class="relative flex-col nav-icon-div">
                    <a href="<%= webpath.getPageUrl("wishlist")%>">
                        <p class="nav-icon">
                            <i class="ri-heart-line"></i>    
                        </P>
                    </a>
                    <div class="wish-list flex-col">
                        <input type="number" id="cartNumber" value="<%= numberWishlist%>" hidden />

                        <!-- wish list with items-->
                        <div class="wish-list-item flex-col <%= isWishlistEmpty ? "" : "active"%>" id="wishlistDiv">

                            <% for (Courses currentCourse : courseWishlist) {%>
                            <div class="flex-col">
                                <div class="course-item flex-row">
                                    <div class="course-item-img">
                                        <img src="./img/course/beginner_excel.jpg" alt=""  draggable="false" />
                                    </div>
                                    <div class="course-item-info flex-col">
                                        <h1 class="course-title"><%=currentCourse.getProductId().getProdName()%></h1>
                                        <p class="course-author"><%=currentCourse.getCoursecatId().getCategoryName()%></p>
                                        <p class="course-price">RM <%=String.format("%.2f", currentCourse.getProductId().getPrice())%></p>
                                    </div>

                                </div>
                                <div class="course-move-cart-div flex-col">
                                    <button class="move-cart-btn" courseID=<%=currentCourse.getCourseId()%> onclick="moveToCart(event)">Add to cart</button>
                                </div>
                            </div>
                            <%}%>

                        </div>

                        <div class="wish-list-bot flex-col  <%= isWishlistEmpty ? "" : "active"%>" id="wishlistLink">
                            <a href="<%= webpath.getPageUrl("wishlist")%>">Go to wishlist</a>
                        </div>
                        <!-- wish list without items-->
                        <div class="list-empty-div flex-col  <%= isWishlistEmpty ? "active" : ""%>" id="wishlistEmpty">
                            <p>Your wishlist is empty</p>
                            <a href="<%= webpath.getPageUrl("products")%>">Explore courses</a>
                        </div>
                    </div>
                </div>
                <div class="relative flex-col nav-icon-div">
                    <a href="<%= webpath.getPageUrl("cart")%>">
                        <p class="nav-icon">
                            <i class="ri-shopping-cart-line"></i>
                            <span class="nav-icon-number  <%= isCartEmpty ? "" : "active"%>" id="cartlistNumber"><%= numberCart%></span>
                        </P>
                    </a>
                    <div class="cart-list flex-col">
                        <!--cart list with items-->
                        <div class="cart-list-item flex-col <%= isCartEmpty ? "" : "active"%>" id="cartlistDiv">

                            <% for (CartItems currentCart : userCartItems) {
                                    Courses currentCourse = userCartCourse.get(currentCart);
                                    Merchandise currentMerch = userCartMerch.get(currentCart);

                                    if (currentCourse != null) {
                            %>
                            <div class="course-item flex-row" productid=<%=currentCourse.getCourseId()%>>
                                <div class="course-item-img">
                                    <img src="./img/course/beginner_excel.jpg" alt="" draggable="false" />
                                </div>
                                <div class="course-item-info flex-col" >
                                    <h1 class="course-title"><%=currentCourse.getProductId().getProdName()%></h1>
                                    <p class="course-author"><%=currentCourse.getCoursecatId().getCategoryName()%></p>
                                    <p class="course-price">RM <%=String.format("%.2f", currentCourse.getProductId().getPrice())%></p>
                                </div>
                            </div>
                            <%} else if (currentMerch != null) {%>
                            <div class="course-item flex-row" productid=<%=currentMerch.getMerchId()%>>
                                <div class="course-item-img">
                                    <img src="./img/merchandise/prx_shirt.png" alt="" draggable="false" />
                                </div>
                                <div class="course-item-info flex-col">
                                    <h1 class="course-title"><%=currentMerch.getProductId().getProdName()%></h1>
                                    <p class="course-author"><%=currentMerch.getMerchcatId().getCategoryName()%></p>
                                    <div class="flex-row">
                                        <p class="course-price">RM <%=String.format("%.2f", currentMerch.getProductId().getPrice())%></p>
                                        <p class="merch-qty">Qty: <span id="merch-span-qty"><%= currentCart.getQuantity()%></span></p>
                                    </div>
                                </div>
                            </div>
                            <%}%>

                            <%}%>

                        </div>
                        <div class="cart-list-bot flex-col <%= isCartEmpty ? "" : "active"%>" id="cartlistLink">
                            <h1>Total: RM <span id="cartlistPrice"><%=String.format("%.2f", userCartTotal)%></span></h1>
                            <a href="<%= webpath.getPageUrl("cart")%>">Go to cart</a>
                        </div>
                        <!--cart list without items-->
                        <div class="list-empty-div flex-col <%= isCartEmpty ? "active" : ""%>" id="cartlistEmpty">
                            <p>Your cart is empty</p>
                            <a href="<%= webpath.getPageUrl("home")%>">Keep shopping</a>
                        </div>
                    </div>
                </div>
                <div class="relative flex-col nav-icon-div">
                    <a href="<%= webpath.getPageUrl("profile")%>">
                        <%String base64Image = "";
                            if (userData.getImgData() != null) {
                                base64Image = Base64.getEncoder().encodeToString((byte[]) userData.getImgData());
                            }%>
                        <p class="nav-icon"><img class="nav-profile-pic" src="data:image/jpeg;base64,<%= base64Image%>" onerror="this.src='./img/user/default.png';" alt=""  draggable="false" /></p>
                    </a>
                    <div class="profile-list flex-col">

                        <div class="user-info flex-row">
                            <div class="user-info-left flex-col">
                                <img class="profile-list-pic" src="data:image/jpeg;base64,<%= base64Image%>" onerror="this.src='./img/user/default.png';" alt=""  draggable="false"  />
                            </div>
                            <div class="user-info-right flex-col">
                                <p class="username">${userData.accountId.username}</p>
                                <p class="email">${userData.accountId.email}</p>
                            </div>
                        </div>

                        <div>
                            <ul class="flex-col">
                                <li><a href="<%= webpath.getPageUrl("profile")%>"><i class="ri-user-settings-line"></i> Account Settings</a></li>
                                <li><a href="<%= webpath.getPageUrl("cart")%>"><i class="ri-shopping-cart-line"></i> Cart</a></li>
                                <li><a href="<%= webpath.getPageUrl("wishlist")%>"><i class="ri-heart-line"></i> Wishlist</a></li>
                                <li><a href="<%= webpath.getPageUrl("purchanse history")%>"><i class="ri-shopping-bag-line"></i> Purchase history</a></li>
                                <li><a href="<%= webpath.getPageUrl("help")%>"><i class="ri-question-line"></i> Help</a></li>
                                    <% if (userData.getUsertype().equals("Manager") || userData.getUsertype().equals("Staff")) { %>
                                <li><a href="<%= webpath.getPageUrl("dashboard")%>"><i class="ri-tools-line"></i> Admin</a></li>
                                    <% } %>
                            </ul>
                        </div>

                        <div>
                            <ul class="flex-col">
                                <li><a href="<%= webpath.getPageUrl("logout")%>"><i class="ri-logout-box-r-line"></i> Log out</a></li>
                            </ul>
                        </div>

                    </div>
                </div>
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
                <!--                                <a onclick="search_suggestion_click(this)">
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
                    <%for (Keyword eachKeyword : allKeywords) {%>
                    <li><a onclick="popular_search_click(this)"><%=eachKeyword.getKeyword()%></a></li>
                        <%}%>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="./js/nav.js"></script> 
