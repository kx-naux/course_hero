<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="JPAEntity.Ratings"%>
<%@page import="JPAEntity.Authors"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<jsp:useBean id="courseData" class="JPAEntity.Courses" scope="request"/>
<jsp:useBean id="rateStats" class="entity.UserRatingStatistic" scope="request"/>
<% String canAddToCart = (String) request.getAttribute("canAddToCart"); %>
<% List<Authors> authorList = (List<Authors>) request.getAttribute("authorList"); %>
<% long ratingCount = ((Long) request.getAttribute("ratingCount")).longValue(); %>
<% List<Ratings> ratingList = (List<Ratings>) request.getAttribute("ratingList"); %>
<% List<Ratings> allRatingList = (List<Ratings>) request.getAttribute("allRatingList"); %>
<% int totalLearners = ((Integer) request.getAttribute("totalLearners")).intValue();%>
<% boolean isOwn = ((Boolean) request.getAttribute("isOwn")).booleanValue();%>
<% boolean inCart = ((Boolean) request.getAttribute("inCart")).booleanValue();%>
<% boolean isReviewed = ((Boolean) request.getAttribute("isReviewed")).booleanValue();%>
<% boolean inWishlist = ((Boolean) request.getAttribute("inWishlist")).booleanValue();%>
<% String reviewError = (String) request.getAttribute("reviewError");
%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${companyName} | Course</title>
        <link rel="icon" type="image/ico" href="${companyIcon}">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/course.css" rel="stylesheet" >
        <!--<link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">-->
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <%  //already set on head
            //boolean isOwn = false; // set value to true if user already buy the course
            // boolean inCart = false;
            //boolean inWishlist = false;
        %>

        <!--put 1 to show add review form-->
        <%String showAddReview = "0";
            if (userData.getDateJoined() != null && isOwn && !isReviewed) {
                showAddReview = "1";
            }%>
        <input type="number" id="addReviewStatus" value="<%= showAddReview%>" hidden />

        <section class="section course-section" courseID="${param.id}">
            <div class="course-div flex-row">

                <div class="course-div-left flex-col">

                    <!--Course General Info-->
                    <div class="course-general-div  flex-col">
                        <a href="<%= webpath.getPageUrl("products")%>?id=${courseData.coursecatId.coursecatId}"><h1 class="course-category">${courseData.coursecatId.categoryName}</h1></a>
                        <h2 class="course-title">${courseData.productId.prodName}</h2>
                        <h3 class="course-short-desc">${courseData.productId.description}</h3>
                        <h4 class="course-level">${courseData.courseLevel}</h4>
                        <div class="course-tag-field flex-row">
                            <p class="course-tag tag-orange">Hot Sell</p>
                            <p class="course-tag tag-yellow">New Course</p>
                        </div>
                        <div class="course-rating flex-row">
                            <p class="rating-digit">${courseData.productId.avgRating}</p>
                            <div class="flex-row">
                                <%
                                    double score = courseData.getProductId().getAvgRating();
                                    for (int i = 0; i < 5; i++) {
                                        if (score >= i + 1) { %>
                                <i class="ri-star-fill"></i>
                                <% } else if (score > i) { %>
                                <i class="ri-star-half-fill"></i>
                                <% } else { %>
                                <i class="ri-star-line"></i>
                                <% } %>
                                <% }%>
                            </div>
                            <p class="rating-number-field"><a href="#rating-div">(<span class="raing-number"><%= ratingCount%></span> ratings)</a></p>
                            <p class="course-sell"><%= totalLearners%> students</p>
                        </div>
                        <%LocalDateTime currentDateTime = LocalDateTime.now();
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String currentDateTimeString = currentDateTime.format(dateTimeFormatter);%>
                        <p class="course-update-date"><i class="ri-information-line"></i> Last updated <%= currentDateTimeString%></p>
                    </div>

                    <!--What you will learn-->
                    <div class="course-learn-div  flex-col">
                        <h1>What you'll learn</h1>
                        <div class="course-learn-point">
                            <ul class="course-learn-point-list flex-row">
                                <%String whatToLearnContent = courseData.getLearningObj();
                                    String modifiedLearnContent = whatToLearnContent.replaceAll("\\\\n", "\n");
                                    String[] learnContentPoints = modifiedLearnContent.split("\\r?\\n"); // Splitting based on newline character
                                    // Display paragraphs in HTML
                                    for (String paragraph : learnContentPoints) {
                                %>
                                <li>
                                    <div class="flex-row">
                                        <p><i class="ri-check-line"></i></p>
                                        <div>
                                            <span><%= paragraph%></span>
                                        </div>
                                    </div>
                                </li>
                                <%}%>
                            </ul>
                        </div>   
                    </div>

                    <!--Course syllabus-->
                    <div class="course-syllabus-div flex-col">
                        <h1>Syllabus</h1>
                        <div class="syllabus-info flex-row">
                            <p>10 Section</p>
                            <p>58h 19m Video</p>
                        </div>
                        <div class="course-syllabus-list flex-col">
                            <%String sysContent = courseData.getSyllabus();
                                String modifiedSysContent = sysContent.replaceAll("\\\\n", "\n");
                                String[] sysContentPoints = modifiedSysContent.split("\\r?\\n"); // Splitting based on newline character
                                int count = 1;
                                // Display paragraphs in HTML
                                for (String paragraph : sysContentPoints) {
                            %>
                            <div class="syllabus-item flex-row">
                                <div class="syllabus-item-left flex-col">
                                    <div class="section-number"><%= count++%></div>
                                </div>
                                <div class="syllabus-item-right flex-col">
                                    <h1 class="section-title"><%= paragraph%></h1>
                                    <!--<p class="section-desc">Introduction to Python</p>-->
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>

                    <!--Course requirements-->
                    <div class="course-requirement-div flex-col">
                        <h1>Requirements</h1>
                        <ul class="flex-col">
                            <%String requiContent = courseData.getRequirements();
                                String modifiedrquiContent = requiContent.replaceAll("\\\\n", "\n");
                                String[] requiContentPoints = modifiedrquiContent.split("\\r?\\n"); // Splitting based on newline character
                                // Display paragraphs in HTML
                                for (String paragraph : requiContentPoints) {
                            %>
                            <li><%= paragraph%></li>
                                <%}%>
                        </ul>
                    </div>

                    <!--Course description-->
                    <div class="course-desc-div flex-col">
                        <h1>Description</h1>
                        <%String descContent = courseData.getDetailedDesc();
                            String modifiedDescContent = descContent.replaceAll("\\\\n", "\n");
                            String[] descContentPoints = modifiedDescContent.split("\\r?\\n"); // Splitting based on newline character
                            // Display paragraphs in HTML
                            for (String paragraph : descContentPoints) {
                        %>
                        <p> <%= paragraph%></p>
                        <%}%>
                    </div>

                    <!--Course target-->
                    <!--<div class="course-target-div flex-col">
                        <h1>Who this course is for</h1>
                        <ul class="flex-col">
                            <li>If you want to learn to code from scratch through building fun and useful projects, then take this course.</li>
                            <li>If you want to start your own startup by building your own websites and web apps.</li>
                            <li>If you are a complete beginner then this course will be everything you need to become a Python professional</li>
                            <li>If you are a seasoned programmer wanting to switch to Python then this is the quickest way. Learn through coding projects.</li>
                        </ul>
                    </div>-->

                    <!--Course Author-->
                    <div class="course-author-div flex-col">
                        <h1>Author</h1>

                        <div class="author-list flex-row">
                            <% for (Authors auth : authorList) {%>
                            <a href="<%= webpath.getPageUrl("author")%>?id=<%= auth.getAuthorId()%>">
                                <div class="author-item flex-col">
                                    <div class="author-img">
                                        <img src="./img/author/angelayu.jpg" alt=""/>
                                    </div>
                                    <div class="author-info flex-col">
                                        <h1 class="author-name"><%= auth.getAuthorName()%></h1>
                                        <p class="author-position"><%= auth.getAuthorPosition()%></p>
                                    </div>
                                </div>
                            </a>
                            <%}%>

                        </div>

                    </div>

                    <!--Course rating-->
                    <% String showRating = "";
                        if (ratingCount == 0) {
                            showRating = " inactive";%>
                    <%}%>
                    <div class="course-rating-div flex-col <%= showRating%>" id="rating-div">
                        <div class="course-overall-rating flex-row">
                            <p class="course-overall-rate"><i class="ri-star-fill"></i> ${courseData.productId.avgRating} course rating</p>
                            <p class="course-overall-rate"><%= ratingCount%> ratings</p>
                        </div>

                        <div class="course-review flex-row">
                            <% for (int i = 0; i < 4 && i < ratingList.size(); i++) {
                                    Ratings rating = ratingList.get(i);
                            %>
                            <div class="user-review flex-col">
                                <div class="user-review-top flex-row">
                                    <%String base64ImageData = "";
                                        if (rating.getUserId().getImgData() != null) {
                                            base64ImageData = Base64.getEncoder().encodeToString((byte[]) rating.getUserId().getImgData());
                                        }%>
                                    <div class="user-img">
                                        <img src="data:image/jpeg;base64,<%= base64ImageData%>" onerror="this.src='./img/user/default.png';" alt="" />
                                    </div>
                                    <div class="flex-col">
                                        <p class="user-name"><%= rating.getUserId().getDisplayName()%></p>
                                        <div class="review-top-bot flex-row">
                                            <div class="rating-stars flex-row">
                                                <% for (double z = rating.getScore(); z > 0; z--) { %>
                                                <%      if (z == 0.5) { %>
                                                <i class="ri-star-half-fill"></i>
                                                <%      } else {%>
                                                <i class="ri-star-fill"></i>
                                                <%      }%>
                                                <%}%>
                                                <% for (double z = 5 - rating.getScore(); z > 0; z--) { %>
                                                <%      if (z != 0.5) { %>
                                                <i class="ri-star-line"></i>
                                                <%      }%>
                                                <%}%>
                                            </div>
                                            <%String pattern = "yyyy-MM-dd HH:mm:ss";
                                                SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
                                                String formattedDate = dateFormatter.format(rating.getTimeRated());%>
                                            <p class="review-date"><%=formattedDate%></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="user-review-bot flex-col">
                                    <%String rateContent = rating.getComment();
                                        String modifiedrateContent = rateContent.replaceAll("\\\\n", "\n");
                                        String[] rateContentParagraphs = modifiedrateContent.split("\\r?\\n"); // Splitting based on newline character
                                        // Display paragraphs in HTML
                                        for (String paragraph : rateContentParagraphs) {
                                    %>
                                    <p><%= paragraph%></p>
                                    <%}%>
                                </div>
                            </div>        
                            <%}%>

                        </div>

                        <button class="all-review-btn">Show all reviews</button>

                        <!--add review form-->
                        <div class="flex-col add-review-div" id="addReviewDiv">
                            <form id="addReviewForm" class="flex-col" method="post" action="/course_hero/submit-course-review">
                                <div class="add-review-title">
                                    <h1>Write a review</h1>
                                </div>

                                <div class="add-review-input flex-col">
                                    <label>What is your rate</label>
                                    <div class="review-rating">
                                        <input value="5" name="rate" id="star5" type="radio">
                                        <label title="text" for="star5"></label>
                                        <input value="4" name="rate" id="star4" type="radio">
                                        <label title="text" for="star4"></label>
                                        <input value="3" name="rate" id="star3" type="radio">
                                        <label title="text" for="star3"></label>
                                        <input value="2" name="rate" id="star2" type="radio">
                                        <label title="text" for="star2"></label>
                                        <input value="1" name="rate" id="star1" type="radio">
                                        <label title="text" for="star1"></label>
                                    </div>
                                </div>

                                <div class="add-review-input flex-col">
                                    <label for="comment">Please share your opinion about the product</label>
                                    <textarea id="comment" name="comment" rows="5" cols="10" placeholder="Enter your review here"></textarea>
                                </div>
                                <% if (reviewError != null) {%>
                                <p id="reviewInvalidMsg" class="invalid-msg"><%= reviewError%></p>
                                <%} else {%>
                                <p id="reviewInvalidMsg" class="invalid-msg"></p>
                                <%}%>


                                <div class="add-review-submit">
                                    <input type="text" id="productIdToReview" name="productIdToReview" value="${courseData.productId.productId}" hidden />
                                    <input type="text" id="courseIdToReview" name="courseIdToReview" value="${courseData.courseId}" hidden />
                                    <input type="button" id="addReviewBtn" value="Send review" />
                                </div>
                            </form>
                        </div>

                    </div>

                    <!--More course form author-->

                </div>

                <div class="course-div-right flex-col">

                    <div class="course-sticky-div flex-col">   
                        <!--Course Preview Video-->
                        <div class="sticky-div-video">
                            <%  String base64StringVideoData = "";
                                if (courseData.getVideoData() != null) {
                                    base64StringVideoData = java.util.Base64.getEncoder().encodeToString((byte[]) courseData.getVideoData());
                                }
                            %>
                            <%--               <video controls>
                                               <source src="data:video/mp4;base64,<%= base64StringVideoData %>" onerror="this.src='./video/course/python_course.mp4';" type="video/mp4">
                                           </video> --%>
                            <img src="${courseData.productId.imagePath}" onerror="this.src='./img/course/beginner_excel.jpg'" alt="" />
                        </div>

                        <div class="sticky-div-bot flex-col">
                            <!--course title-->
                            <div class="sticky-div-title flex-row">
                                <h1>${courseData.productId.prodName}</h1>
                            </div>

                            <!--Course Price-->
                            <div class="sticky-div-price flex-row">
                                <p class="course-price">RM <span><%= String.format("%.2f", courseData.getProductId().getPrice() - courseData.getProductId().getDiscount())%></span></p>                                      
                                <p class="course-normal-price">RM <span><%= String.format("%.2f", courseData.getProductId().getPrice())%></span></p>
                                <% double disPercent = (courseData.getProductId().getDiscount()) / (courseData.getProductId().getPrice()) * 100;
                                    if (disPercent > 0) {%>
                                <p class="course-offer"><%= String.format("%.0f", disPercent)%>% off</p>
                                <%}%>
                            </div>

                            <% if (!isOwn) {%>
                            <!--Course add to cart or wish list-->
                            <div class="sticky-div-cart flex-row">
                                <button class="course-cart" status="<%= inCart ? "1" : "0"%>"><%= inWishlist ? "Go to cart" : "Add to cart"%></button>
                                <button class="course-wish" status="<%= inWishlist ? "1" : "0"%>"><i class="ri-heart-line"></i></button>
                            </div>

                            <!--Information-->
                            <div class="sticky-div-info flex-col">
                                <p>30-Day Money-Back Guarantee</p>
                                <p>Full Lifetime Access</p>
                            </div>
                            <% }%>

                            <!--share this course-->
                            <div class="sticky-div-share flex-col">
                                <p>Share this course</p>
                                <button class="share-btn">Copy link <i class="ri-link"></i></button>
                            </div>

                        </div>
                    </div>

                </div>

            </div>
        </section>
        <%if (rateStats.getAllStarCounts() > 0) {%>
        <section class="section all-review-section">
            <div class="all-review-div flex-col">

                <!--title and close btn-->
                <div class="all-review-top flex-row">
                    <p class="course-overall-rate"><i class="ri-star-fill"></i> ${courseData.productId.avgRating} course rating</p>
                    <p class="course-overall-rate">${rateStats.allStarCounts} ratings</p>
                    <span class="close-btn"><i class="ri-close-line"></i></span>
                </div>

                <div class="all-review-bot flex-row">

                    <div class="review-content-left flex-col">

                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar flex-col">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: <%= rateStats.getFiveStarPercentage()%>%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                            </div>
                            <p class="review-number">${rateStats.fiveStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: <%= rateStats.getFourStarPercentage()%>%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.fourStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: <%= rateStats.getThreeStarPercentage()%>%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.threeStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: <%= rateStats.getTwoStarPercentage()%>%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.twoStarCounts}</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar"  style="width: <%= rateStats.getOneStarPercentage()%>%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">${rateStats.oneStarCounts}</p>
                        </div>

                    </div>

                    <div class="review-content-right flex-col" id="reviewsDiv">

                        <!--record the last review in the list below-->
                        <% //review counts %>
                        <input type="text" id="lastRatingId" value="R00000008" hidden />
                        <input type="text" id="submitCount" value="1" hidden />

                        <% for (int i = 0; i < 4 && i < allRatingList.size(); i++) {
                            Ratings rating = allRatingList.get(i);
                        %>

                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <%String base64ImageData = "";
                                        if (rating.getUserId().getImgData() != null) {
                                            base64ImageData = Base64.getEncoder().encodeToString((byte[]) rating.getUserId().getImgData());
                                        }%>
                                    <img src="data:image/jpeg;base64,<%= base64ImageData%>" onerror="this.src='./img/user/default.png';" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name"><%= rating.getUserId().getDisplayName()%></p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <% for (double z = rating.getScore(); z > 0; z--) { %>
                                            <%      if (z == 0.5) { %>
                                            <i class="ri-star-half-fill"></i>
                                            <%      } else {%>
                                            <i class="ri-star-fill"></i>
                                            <%      }%>
                                            <%}%>
                                            <% for (double z = 5 - rating.getScore(); z > 0; z--) { %>
                                            <%      if (z != 0.5) { %>
                                            <i class="ri-star-line"></i>
                                            <%      }%>
                                            <%}%>
                                        </div>
                                        <%String pattern = "yyyy-MM-dd HH:mm:ss";
                                            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
                                            String formattedDate = dateFormatter.format(rating.getTimeRated());%>
                                        <p class="review-date"><%=formattedDate%></p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <%String rateContent = rating.getComment();
                                    String modifiedrateContent = rateContent.replaceAll("\\\\n", "\n");
                                    String[] rateContentParagraphs = modifiedrateContent.split("\\r?\\n"); // Splitting based on newline character
                                    // Display paragraphs in HTML
                                    for (String paragraph : rateContentParagraphs) {
                                %>
                                <p><%= paragraph%></p>
                                <%}%>
                            </div>
                        </div>

                        <% } %>

                        <%
                            if (allRatingList.size() > ratingCount) {
                        %> 
                        <button class="show-more-btn" id="showMoreReviewBtn">Show more reviews</button>
                        <% } %>
                    </div>
                </div>

            </div>
        </section>
        <%}%>
        <script src="./js/single_course/single_course.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
