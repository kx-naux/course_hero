<%@page import="java.time.Duration"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Arrays;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Hero | Search</title>
        <link rel="icon" type="image/ico" href="./ico/Logo.ico">
        <link type="text/css" href="./css/style.css" rel="stylesheet" >
        <link type="text/css" href="./css/search_result.css" rel="stylesheet" >
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body>
        <!--Toast message-->
        <%@ include file="./Components/toast_msg.html" %>

        <!--To top button-->
        <%@ include file="./Components/to_top_button.html" %>

        <!-- Include the navigation bar -->
        <%@ include file="./Components/navbar.jsp" %>

        <!-- Search Result Information -->
        <form hidden="" id="resetFilter"><input type="text" name="query" value="${param.query}" hidden /></form>
        <form id="searchForm">
            <section class="section search-result-section flex-col">
                <div class="search-result-info-div flex-col">
                    <input type="text" name="query" value="${param.query}" hidden />
                    <h1><span class="result-amount">10,000</span> results for “<span class="search-query">${param.query}</span>”</h1>

                    <div class="result-function flex-row">
                        <button type="button" class="filter-btn"><i class="ri-filter-3-fill"></i> Filter <span class="filter-count"></span></button>
                        <div class="sort-div">
                            <div class="sort-select flex-row">
                                <select id="sort-type" name="sort" onchange="this.form.submit()">
                                    <option value="most-reviewed" ${param.sort == 'most-reviewed' ? 'selected' : ''}>Most Reviewed</option>
                                    <option value="highest-rated" ${param.sort == 'highest-rated' ? 'selected' : ''}>Highest Rated</option>
                                    <option value="relevance" ${param.sort == 'relevance' ? 'selected' : ''}>Most Relevant</option>
                                    <option value="newest" ${param.sort == 'newest' ? 'selected' : ''}>Newest</option>
                                </select>
                                <div class="sort-select-icon flex-col"><p><i class="ri-arrow-down-s-line"></i></p></div>
                            </div>
                            <label for="sort-type">Sort by</label>
                        </div>
                        <button type="button" class="clear-filter-btn" onclick="document.getElementById('resetFilter').submit();"><i class="ri-delete-back-2-fill"></i> Clear filters</button>
                    </div>

                </div>

                <div class="search-result-div flex-row">
                    <!--filter div-->
                    <div class="search-filter-div flex-col">  

                        <input class="apply-filter-btn" type="submit" value="Apply Filter" />

                        <!--rating filter-->
                        <div class="filter-div flex-col ${param.ratings != null ? 'active' : ''}">
                            <div class="filter-type-title flex-row">
                                <h1>Ratings</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div rating-filter-div flex-col">
                                <label for="filter-rating-1" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-1" type="radio" value="4.5" ${param.ratings == '4.5' ? 'checked' : ''} />
                                    <span class="filter-icon flex-row">
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-half-fill"></i>
                                    </span>
                                    <span class="filter-desc">4.5 & up</span>
                                    <span class="result-count">(6000)</span>
                                </label>
                                <label for="filter-rating-2" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-2" type="radio" value="4.0" ${param.ratings == '4.0' ? 'checked' : ''} />
                                    <span class="filter-icon flex-row">
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-line"></i>
                                    </span>
                                    <span class="filter-desc">4.0 & up</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-rating-3" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-3" type="radio" value="3.5" ${param.ratings == '3.5' ? 'checked' : ''}/>
                                    <span class="filter-icon flex-row">
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-half-fill"></i>
                                        <i class="ri-star-line"></i>
                                    </span>
                                    <span class="filter-desc">3.5 & up</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-rating-4" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-4" type="radio" value="3.0" ${param.ratings == '3.0' ? 'checked' : ''} />
                                    <span class="filter-icon flex-row">
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-fill"></i>
                                        <i class="ri-star-line"></i>
                                        <i class="ri-star-line"></i>
                                    </span>
                                    <span class="filter-desc">3.0 & up</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>
                        </div>

                        <!--Video Duration-->
                        <%
                            String[] durations = request.getParameterValues("duration");
                            List<String> durationList = (durations != null) ? Arrays.asList(durations) : null;
                            String videoActive = (durationList != null) ? "active" : "";
                        %>
                        <div class="filter-div flex-col <%= videoActive%>">
                            <div class="filter-type-title flex-row">
                                <h1>Video Duration</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div duration-filter-div flex-col">
                                <label for="filter-duration-1" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-1" type="checkbox" value="extrashort" <%= (durationList != null && durationList.contains("extrashort")) ? "checked" : ""%> />
                                    <span class="filter-desc">0-1 Hour</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-2" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-2" type="checkbox" value="short" <%= (durationList != null && durationList.contains("short")) ? "checked" : ""%> />
                                    <span class="filter-desc">1-3 Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-3" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-3" type="checkbox" value="medium" <%= (durationList != null && durationList.contains("medium")) ? "checked" : ""%> />
                                    <span class="filter-desc">3-6 Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-4" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-4" type="checkbox" value="long" <%= (durationList != null && durationList.contains("long")) ? "checked" : ""%> />
                                    <span class="filter-desc">6-17 Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-5" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-5" type="checkbox" value="extralong" <%= (durationList != null && durationList.contains("extralong")) ? "checked" : ""%> />
                                    <span class="filter-desc">17+ Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>
                        </div>

                        <!--Course Level-->
                        <%
                            String[] levels = request.getParameterValues("level");
                            List<String> levelList = (levels != null) ? Arrays.asList(levels) : null;
                            String levelActive = (levelList != null) ? "active" : "";
                        %>
                        <div class="filter-div flex-col <%= levelActive%>">
                            <div class="filter-type-title flex-row">
                                <h1>Level</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div level-filter-div flex-col">
                                <label for="filter-level-1" class="check-filter-option flex-row">
                                    <input name="level" id="filter-level-1" type="checkbox" value="all" <%= (levelList != null && levelList.contains("all")) ? "checked" : ""%> />
                                    <span class="filter-desc">All Levels</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-level-2" class="check-filter-option flex-row">
                                    <input name="level" id="filter-level-2" type="checkbox" value="beginner" <%= (levelList != null && levelList.contains("beginner")) ? "checked" : ""%> />
                                    <span class="filter-desc">Beginner</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-level-3" class="check-filter-option flex-row">
                                    <input name="level" id="filter-level-3" type="checkbox" value="intermediate" <%= (levelList != null && levelList.contains("intermediate")) ? "checked" : ""%> />
                                    <span class="filter-desc">Intermediate</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-level-4" class="check-filter-option flex-row">
                                    <input name="level" id="filter-level-4" type="checkbox" value="expert" <%= (levelList != null && levelList.contains("expert")) ? "checked" : ""%> />
                                    <span class="filter-desc">Expert</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>
                        </div>

                        <!--Price-->
                        <%
                            String priceActive = "active";
                            String minPrice = request.getParameter("minPrice");
                            String maxPrice = request.getParameter("maxPrice");

                            if ((minPrice != null && minPrice.equals("")) && (maxPrice != null && maxPrice.equals(""))) {
                                priceActive = "";
                            } else if (minPrice == null && maxPrice == null) {
                                priceActive = "";
                            }
                        %>
                        <div class="filter-div flex-col <%= priceActive%>">
                            <div class="filter-type-title flex-row">
                                <h1>Price Range</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div price-filter-div flex-row">
                                <input type="number" min="0" name="minPrice" id="minPrice" maxlength="13" placeholder="RM MIN" value="${param.minPrice}" />
                                <span> to </span>
                                <input type="number" min="0" name="maxPrice" id="maxPrice" maxlength="13" placeholder="RM MAX" value="${param.maxPrice}" />
                            </div>
                        </div>

                        <!--course category-->   
                        <%
                            String[] categories = request.getParameterValues("category");
                            List<String> categoryList = null;
                            String categoryActive = "";

                            if (categories != null) {
                                categoryList = Arrays.asList(categories);
                                categoryActive = "active";
                            }
                        %>
                        <div class="filter-div flex-col <%= categoryActive%>">
                            <div class="filter-type-title flex-row">
                                <h1>Category</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div cat-filter-div flex-col">
                                <label for="filter-cat-1" class="check-filter-option flex-row">
                                    <input name="category" id="filter-cat-1" type="checkbox" value="Data Science" />
                                    <span class="filter-desc">Data Science</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-cat-2" class="check-filter-option flex-row">
                                    <input name="category" id="filter-cat-2" type="checkbox" value="Software Engineer" />
                                    <span class="filter-desc">Software Engineer</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>
                        </div>

                    </div>

                    <!--result course-->
                    <div class="result-course-div flex-col">

                        <div class="result-course-div flex-row" courseID="12376213" onclick="redirectToProductPage(this)">
                            <div class="result-course-left flex-col">
                                <img src="./img/course/beginner_excel.jpg" alt="">
                            </div>
                            <div class="result-course-right flex-col">
                                <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                <h2 class="course-category">Microsoft Excel</h2>
                                <h2 class="course-desc">This course will teach you the programming for Microsoft Excel</h2>
                                <p class="course-author">Woo Yu Beng, Snijders Wang</p>
                                <div class="course-rating flex-row">
                                    <p class="rating-digit">3.5</p>
                                    <i class="ri-star-fill"></i>
                                    <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                </div>
                                <div class="course-label flex-row">
                                    <p>9.5 Hours</p>
                                    <p>All Level</p>
                                </div>
                                <div class="course-tag-field flex-row">
                                    <p class="course-tag tag-orange">Hot Sell</p>
                                    <p class="course-tag tag-yellow">New Course</p>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM <span>449.90</span></p>                                      
                                    <p class="course-normal-price">RM <span>650.00</span></p>
                                </div>
                            </div>
                        </div>

                        <div class="result-course-div flex-row" courseID="12376213" onclick="redirectToProductPage(this)">
                            <div class="result-course-left flex-col">
                                <img src="./img/course/beginner_excel.jpg" alt="">
                            </div>
                            <div class="result-course-right flex-col">
                                <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                <h2 class="course-category">Microsoft Excel</h2>
                                <h2 class="course-desc">This course will teach you the programming for Microsoft Excel</h2>
                                <p class="course-author">Woo Yu Beng, Snijders Wang</p>
                                <div class="course-rating flex-row">
                                    <p class="rating-digit">3.5</p>
                                    <i class="ri-star-fill"></i>
                                    <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                </div>
                                <div class="course-label flex-row">
                                    <p>9.5 Hours</p>
                                    <p>All Level</p>
                                </div>
                                <div class="course-tag-field flex-row">
                                    <p class="course-tag tag-orange">Hot Sell</p>
                                    <p class="course-tag tag-yellow">New Course</p>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM <span>449.90</span></p>                                      
                                    <p class="course-normal-price">RM <span>650.00</span></p>
                                </div>
                            </div>
                        </div>

                        <div class="result-course-div flex-row" courseID="12376213" onclick="redirectToProductPage(this)">
                            <div class="result-course-left flex-col">
                                <img src="./img/course/beginner_excel.jpg" alt="">
                            </div>
                            <div class="result-course-right flex-col">
                                <h1 class="course-title">The Ultimate Excel Programming Course</h1>
                                <h2 class="course-category">Microsoft Excel</h2>
                                <h2 class="course-desc">This course will teach you the programming for Microsoft Excel</h2>
                                <p class="course-author">Woo Yu Beng, Snijders Wang</p>
                                <div class="course-rating flex-row">
                                    <p class="rating-digit">3.5</p>
                                    <i class="ri-star-fill"></i>
                                    <p class="rating-number-field">(<span class="raing-number">2303</span>)</p>
                                </div>
                                <div class="course-label flex-row">
                                    <p>9.5 Hours</p>
                                    <p>All Level</p>
                                </div>
                                <div class="course-tag-field flex-row">
                                    <p class="course-tag tag-orange">Hot Sell</p>
                                    <p class="course-tag tag-yellow">New Course</p>
                                </div>
                                <div class="course-price-field flex-col">
                                    <p class="course-price">RM <span>449.90</span></p>                                      
                                    <p class="course-normal-price">RM <span>650.00</span></p>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

                <!--pagination, 20 course per page-->
                <%
                    int currentPage = 1;
                    int lastPage = 500;
                    if (request.getParameter("p") != null) {
                        currentPage = Integer.parseInt(request.getParameter("p"));
                    }

                    boolean previousAllow = currentPage > 1;
                    boolean nextAllow = currentPage < lastPage;
                %>
                <input type="number" id="current_page" name="p" value="<%= currentPage%>" hidden />
                <div class="result-pagination-div flex-row">
                    <p class="page-action-btn page-previous-btn <%= previousAllow ? "allow" : ""%>"><i class="ri-arrow-left-s-line"></i></p>
                        <% if (currentPage - 1 != 0) { %>
                    <p class="first-page-number page-number">1</p>
                    <% }
                        if (currentPage - 1 != 1 && currentPage - 1 != 2 && currentPage - 1 > 0) { %>
                    <p class="number-dot">...</p>
                    <% }
                        if (currentPage - 1 > 1) {%>
                    <p class="first-page-number page-number"><%= currentPage - 1%></p>
                    <% }%>
                    <p class="first-page-number page-number current-page"><%= currentPage%></p>
                    <% if (currentPage + 1 < lastPage) {%>
                    <p class="first-page-number page-number"><%= currentPage + 1%></p>
                    <%
                        }
                        if (currentPage + 1 != lastPage && currentPage + 2 != lastPage && currentPage + 1 < lastPage) {
                    %>
                    <p class="number-dot">...</p>
                    <% }
                        if (lastPage > 1 && currentPage != lastPage) {%>
                    <p class="last-page-number page-number"><%= lastPage%></p>
                    <% }%>
                    <p class="page-action-btn page-next-btn <%= nextAllow ? "allow" : ""%>"><i class="ri-arrow-right-s-line"></i></p>
                </div>
            </section>
        </form>

        <script src="./js/search_result/search_result.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
