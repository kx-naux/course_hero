<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Course</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
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

        <%            boolean isOwn = false; // set value to true if user already buy the course
            boolean inCart = false;
            boolean inWishlist = false;
        %>

        <!--put 1 to show add review form-->
        <input type="number" id="addReviewStatus" value="1" hidden />

        <section class="section course-section" courseID="${param.id}">
            <div class="course-div flex-row">

                <div class="course-div-left flex-col">

                    <!--Course General Info-->
                    <div class="course-general-div  flex-col">
                        <a href="<%= webpath.getPageUrl("products")%>?id=123123"><h1 class="course-category">Python Programming</h1></a>
                        <h2 class="course-title">100 Days of Code: The Complete Python Pro Bootcamp</h2>
                        <h3 class="course-short-desc">Master Python by building 100 projects in 100 days. Learn data science, automation, build websites, games and apps!</h3>
                        <h4 class="course-level">All Level</h4>
                        <div class="course-tag-field flex-row">
                            <p class="course-tag tag-orange">Hot Sell</p>
                            <p class="course-tag tag-yellow">New Course</p>
                        </div>
                        <div class="course-rating flex-row">
                            <p class="rating-digit">4.5</p>
                            <div class="flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-half-fill"></i>
                            </div>
                            <p class="rating-number-field"><a href="#rating-div">(<span class="raing-number">2303</span> ratings)</a></p>
                            <p class="course-sell">4864 students</p>
                        </div>
                        <p class="course-update-date"><i class="ri-information-line"></i> Last updated 16/4/2024</p>
                    </div>

                    <!--What you will learn-->
                    <div class="course-learn-div  flex-col">
                        <h1>What you'll learn</h1>
                        <div class="course-learn-point">
                            <ul class="course-learn-point-list flex-row">
                                <li>
                                    <div class="flex-row">
                                        <p><i class="ri-check-line"></i></p>
                                        <div>
                                            <span>You will master the Python programming language by building 100 unique projects over 100 days.</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="flex-row">
                                        <p><i class="ri-check-line"></i></p>
                                        <div>
                                            <span>You will learn automation, game, app and web development, data science and machine learning all using Python.</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="flex-row">
                                        <p><i class="ri-check-line"></i></p>
                                        <div>
                                            <span>You will learn Selenium, Beautiful Soup, Request, Flask, Pandas, NumPy, Scikit Learn, Plotly, and Matplotlib.</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="flex-row">
                                        <p><i class="ri-check-line"></i></p>
                                        <div>
                                            <span>You will be able to program in Python professionally</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="flex-row">
                                        <p><i class="ri-check-line"></i></p>
                                        <div>
                                            <span>Create a portfolio of 100 Python projects to apply for developer jobs</span>
                                        </div>
                                    </div>
                                </li>
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

                            <div class="syllabus-item flex-row">
                                <div class="syllabus-item-left flex-col">
                                    <div class="section-number">1</div>
                                </div>
                                <div class="syllabus-item-right">
                                    <h1 class="section-title">Introduction to Python</h1>
                                    <p class="section-desc">Learn the basics of Python, one of the world’s most popular and powerful programming languages, and see how it can be utilized in Finance.</p>
                                </div>
                            </div>

                            <div class="syllabus-item flex-row">
                                <div class="syllabus-item-left flex-col">
                                    <div class="section-number">2</div>
                                </div>
                                <div class="syllabus-item-right">
                                    <h1 class="section-title">Introduction to NumPy</h1>
                                    <p class="section-desc">Learn the basics of Python, one of the world’s most popular and powerful programming languages, and see how it can be utilized in Finance.</p>
                                </div>
                            </div>

                            <div class="syllabus-item flex-row">
                                <div class="syllabus-item-left flex-col">
                                    <div class="section-number">3</div>
                                </div>
                                <div class="syllabus-item-right">
                                    <h1 class="section-title">Introduction to Pandas</h1>
                                    <p class="section-desc">Learn the basics of Python, one of the world’s most popular and powerful programming languages, and see how it can be utilized in Finance.</p>
                                </div>
                            </div>

                        </div>
                    </div>

                    <!--Course requirements-->
                    <div class="course-requirement-div flex-col">
                        <h1>Requirements</h1>
                        <ul class="flex-col">
                            <li>No programming experience needed - I'll teach you everything you need to know</li>
                            <li>A Mac or Windows PC computer with access to the internet</li>
                            <li>No paid software required - I'll teach you how to use PyCharm, Jupyter Notebooks and Google Colab</li>
                            <li>I'll walk you through, step-by-step how to get all the software installed and set up</li>
                        </ul>
                    </div>

                    <!--Course description-->
                    <div class="course-desc-div flex-col">
                        <h1>Description</h1>
                        <p>Welcome to the 100 Days of Code - The Complete Python Pro Bootcamp, the only course you need to learn to code with Python. With over 500,000 5 STAR reviews and a 4.8 average, my courses are some of the HIGHEST RATED courses in the history of Udemy!  </p>

                        <p>100 days, 1 hour per day, learn to build 1 project per day, this is how you master Python.</p>

                        <p>At 60+ hours, this Python course is without a doubt the most comprehensive Python course available anywhere online. Even if you have zero programming experience, this course will take you from beginner to professional.</p>
                    </div>

                    <!--Course target-->
                    <div class="course-target-div flex-col">
                        <h1>Who this course is for</h1>
                        <ul class="flex-col">
                            <li>If you want to learn to code from scratch through building fun and useful projects, then take this course.</li>
                            <li>If you want to start your own startup by building your own websites and web apps.</li>
                            <li>If you are a complete beginner then this course will be everything you need to become a Python professional</li>
                            <li>If you are a seasoned programmer wanting to switch to Python then this is the quickest way. Learn through coding projects.</li>
                        </ul>
                    </div>

                    <!--Course Author-->
                    <div class="course-author-div flex-col">
                        <h1>Author</h1>

                        <div class="author-list flex-row">
                            <a href="<%= webpath.getPageUrl("author")%>?id=123123">
                                <div class="author-item flex-col">
                                    <div class="author-img">
                                        <img src="./img/author/angelayu.jpg" alt=""/>
                                    </div>
                                    <div class="author-info flex-col">
                                        <h1 class="author-name">Dr. Angela Yu</h1>
                                        <P class="author-position">Developer and Lead Instructor</P>
                                    </div>
                                </div>
                            </a>
                            <a href="<%= webpath.getPageUrl("author")%>?id=123123">
                                <div class="author-item flex-col">
                                    <div class="author-img">
                                        <img src="./img/author/angelayu.jpg" alt=""/>
                                    </div>
                                    <div class="author-info flex-col">
                                        <h1 class="author-name">Dr. Angela Yu</h1>
                                        <P class="author-position">Developer and Lead Instructor</P>
                                    </div>
                                </div>
                            </a>
                        </div>

                    </div>

                    <!--Course rating-->
                    <div class="course-rating-div flex-col" id="rating-div">
                        <div class="course-overall-rating flex-row">
                            <p class="course-overall-rate"><i class="ri-star-fill"></i> 4.7 course rating</p>
                            <p class="course-overall-rate">258 ratings</p>
                        </div>

                        <div class="course-review flex-row">

                            <div class="user-review flex-col">
                                <div class="user-review-top flex-row">
                                    <div class="user-img">
                                        <img src="./img/user/default.png" alt="" />
                                    </div>
                                    <div class="flex-col">
                                        <p class="user-name">Woo Yu Beng</p>
                                        <div class="review-top-bot flex-row">
                                            <div class="rating-stars flex-row">
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-half-fill"></i>
                                            </div>
                                            <p class="review-date">29/4/2024</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="user-review-bot flex-col">
                                    <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                                </div>
                            </div>

                            <div class="user-review flex-col">
                                <div class="user-review-top flex-row">
                                    <div class="user-img">
                                        <img src="./img/user/default.png" alt="" />
                                    </div>
                                    <div class="flex-col">
                                        <p class="user-name">Woo Yu Beng</p>
                                        <div class="review-top-bot flex-row">
                                            <div class="rating-stars flex-row">
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-half-fill"></i>
                                            </div>
                                            <p class="review-date">29/4/2024</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="user-review-bot flex-col">
                                    <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                                </div>
                            </div>

                            <div class="user-review flex-col">
                                <div class="user-review-top flex-row">
                                    <div class="user-img">
                                        <img src="./img/user/default.png" alt="" />
                                    </div>
                                    <div class="flex-col">
                                        <p class="user-name">Woo Yu Beng</p>
                                        <div class="review-top-bot flex-row">
                                            <div class="rating-stars flex-row">
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-half-fill"></i>
                                            </div>
                                            <p class="review-date">29/4/2024</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="user-review-bot flex-col">
                                    <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                                </div>
                            </div>

                            <div class="user-review flex-col">
                                <div class="user-review-top flex-row">
                                    <div class="user-img">
                                        <img src="./img/user/default.png" alt="" />
                                    </div>
                                    <div class="flex-col">
                                        <p class="user-name">Woo Yu Beng</p>
                                        <div class="review-top-bot flex-row">
                                            <div class="rating-stars flex-row">
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-fill"></i>
                                                <i class="ri-star-half-fill"></i>
                                            </div>
                                            <p class="review-date">29/4/2024</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="user-review-bot flex-col">
                                    <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                                </div>
                            </div>

                        </div>

                        <button class="all-review-btn">Show all reviews</button>

                        <div class="flex-col add-review-div" id="addReviewDiv">
                            <form id="addReviewForm" class="flex-col">
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

                                <p id="reviewInvalidMsg" class="invalid-msg"></p>

                                <div class="add-review-submit">
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
                            <video controls>
                                <source src="./video/course/python_course.mp4" type="video/mp4">
                            </video>
                        </div>

                        <div class="sticky-div-bot flex-col">
                            <!--course title-->
                            <div class="sticky-div-title flex-row">
                                <h1>100 Days of Code: The Complete Python Pro Bootcamp</h1>
                            </div>

                            <!--Course Price-->
                            <div class="sticky-div-price flex-row">
                                <p class="course-price">RM <span>449.90</span></p>                                      
                                <p class="course-normal-price">RM <span>650.00</span></p>
                                <p class="course-offer">50% off</p>
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

        <section class="section all-review-section">
            <div class="all-review-div flex-col">

                <!--title and close btn-->
                <div class="all-review-top flex-row">
                    <p class="course-overall-rate"><i class="ri-star-fill"></i> 4.7 course rating</p>
                    <p class="course-overall-rate">258 ratings</p>
                    <span class="close-btn"><i class="ri-close-line"></i></span>
                </div>

                <div class="all-review-bot flex-row">

                    <div class="review-content-left flex-col">

                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar flex-col">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 70%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                            </div>
                            <p class="review-number">70%</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 20%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">20%</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 4%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">4%</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar" style="width: 1%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">1%</p>
                        </div>
                        <div class="rating-count flex-row">
                            <div class="rating-percentage-bar">
                                <span class="background-bar"></span>
                                <span class="front-bar"  style="width: 1%"></span>
                            </div>
                            <div class="rating-stars flex-row">
                                <i class="ri-star-fill"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                                <i class="ri-star-line"></i>
                            </div>
                            <p class="review-number">1%</p>
                        </div>

                    </div>

                    <div class="review-content-right flex-col" id="reviewsDiv">

                        <!--record the last review in the list below-->
                        <input type="text" id="lastRatingId" value="R00000008" hidden />

                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <img src="./img/user/default.png" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name">Woo Yu Beng</p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-half-fill"></i>
                                        </div>
                                        <p class="review-date">29/4/2024</p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                            </div>
                        </div>
                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <img src="./img/user/default.png" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name">Woo Yu Beng</p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-half-fill"></i>
                                        </div>
                                        <p class="review-date">29/4/2024</p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                            </div>
                        </div>
                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <img src="./img/user/default.png" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name">Woo Yu Beng</p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-half-fill"></i>
                                        </div>
                                        <p class="review-date">29/4/2024</p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                            </div>
                        </div>
                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <img src="./img/user/default.png" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name">Woo Yu Beng</p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-half-fill"></i>
                                        </div>
                                        <p class="review-date">29/4/2024</p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                            </div>
                        </div>
                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <img src="./img/user/default.png" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name">Woo Yu Beng</p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-half-fill"></i>
                                        </div>
                                        <p class="review-date">29/4/2024</p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                            </div>
                        </div>
                        <div class="user-review flex-col">
                            <div class="user-review-top flex-row">
                                <div class="user-img">
                                    <img src="./img/user/default.png" alt="" />
                                </div>
                                <div class="flex-col">
                                    <p class="user-name">Woo Yu Beng</p>
                                    <div class="review-top-bot flex-row">
                                        <div class="rating-stars flex-row">
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-fill"></i>
                                            <i class="ri-star-half-fill"></i>
                                        </div>
                                        <p class="review-date">29/4/2024</p>
                                    </div>
                                </div>
                            </div>
                            <div class="user-review-bot flex-col">
                                <p>I have a B.S. in Computer Programming. My curriculum did not include Python, so I decided to give this a try. This course is amazing! I do not normally leave reviews, but I am very happy with the purchase.</p>
                            </div>
                        </div>

                        <button class="show-more-btn" id="showMoreReviewBtn">Show more reviews</button>
                    </div>
                </div>

            </div>
        </section>

        <script src="./js/single_course/single_course.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
