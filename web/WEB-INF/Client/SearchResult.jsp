<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form>
            <section class="section search-result-section flex-col">
                <div class="search-result-info-div flex-col">
                    <input type="text" name="query" value="${param.query}" hidden />
                    <h1><span class="result-amount">10,000</span> results for “<span class="search-query">${param.query}</span>”</h1>

                    <div class="result-function flex-row">
                        <button type="button" class="filter-btn"><i class="ri-filter-3-fill"></i> Filter <span class="filter-count"></span></button>
                        <div class="sort-div">
                            <div class="sort-select flex-row">
                                <select id="sort-type" name="sort" onchange="this.form.submit()">
                                    <option value="most-reviewed">Most Reviewed</option>
                                    <option value="highest-rated">Highest Rated</option>
                                    <option value="relevance" selected>Most Relevant</option>
                                    <option value="newest">Newest</option>
                                </select>
                                <div class="sort-select-icon flex-col"><p><i class="ri-arrow-down-s-line"></i></p></div>
                            </div>
                            <label for="sort-type">Sort by</label>
                        </div>
                        <button type="reset" class="clear-filter-btn"><i class="ri-delete-back-2-fill"></i> Clear filters</button>
                    </div>

                </div>

                <div class="search-result-div flex-row">
                    <!--filter div-->
                    <div class="search-filter-div flex-col">  
                        
                        <input class="apply-filter-btn" type="submit" value="Apply Filter" />

                        <!--rating filter-->
                        <div class="filter-div flex-col active">
                            <div class="filter-type-title flex-row">
                                <h1>Ratings</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div rating-filter-div flex-col">
                                <label for="filter-rating-1" class="rating-filter-option flex-row">
                                    <input name="ratings" id="filter-rating-1" type="radio" value="4.5" />
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
                                    <input name="ratings" id="filter-rating-2" type="radio" value="4.0" />
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
                                    <input name="ratings" id="filter-rating-3" type="radio" value="3.5" />
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
                                    <input name="ratings" id="filter-rating-4" type="radio" value="3.0" />
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
                        <div class="filter-div flex-col active">
                            <div class="filter-type-title flex-row">
                                <h1>Video Duration</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div duration-filter-div flex-col">
                                <label for="filter-duration-1" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-1" type="checkbox" value="extrashort" />
                                    <span class="filter-desc">0-1 Hour</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-2" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-2" type="checkbox" value="short" />
                                    <span class="filter-desc">1-3 Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-3" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-3" type="checkbox" value="medium" />
                                    <span class="filter-desc">3-6 Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-4" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-4" type="checkbox" value="long" />
                                    <span class="filter-desc">6-17 Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-duration-5" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-duration-5" type="checkbox" value="extralong" />
                                    <span class="filter-desc">17+ Hours</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>
                        </div>

                        <!--Course Level-->
                        <div class="filter-div flex-col">
                            <div class="filter-type-title flex-row">
                                <h1>Level</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div level-filter-div flex-col">
                                <label for="filter-level-1" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-level-1" type="checkbox" value="all" />
                                    <span class="filter-desc">All Levels</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-level-2" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-level-2" type="checkbox" value="beginner" />
                                    <span class="filter-desc">Beginner</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-level-3" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-level-3" type="checkbox" value="intermediate" />
                                    <span class="filter-desc">Intermediate</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                                <label for="filter-level-4" class="check-filter-option flex-row">
                                    <input name="duration" id="filter-level-4" type="checkbox" value="expert" />
                                    <span class="filter-desc">Expert</span>
                                    <span class="result-count">(1000)</span>
                                </label>
                            </div>
                        </div>

                        <!--Price-->
                        <div class="filter-div flex-col">
                            <div class="filter-type-title flex-row">
                                <h1>Price Range</h1>
                                <p><i class="ri-arrow-down-s-line"></i></p>
                            </div>
                            <div class="filter-option-div price-filter-div flex-row">
                                <input type="number" min="0" name="minPrice" id="minPrice" maxlength="13" placeholder="RM MIN" value />
                                <span> to </span>
                                <input type="number" min="0" name="maxPrice" id="maxPrice" maxlength="13" placeholder="RM MAX" value />
                            </div>
                        </div>

                        <!--course category-->   
                        <div class="filter-div flex-col">
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



                    </div>
                </div>

                <!--pagination, 20 course per page-->
                <input type="number" id="current_page" name="p" value="1" hidden />
                <div class="result-pagination-div flex-row">
                    <p class="page-action-btn page-previous-btn"><i class="ri-arrow-left-s-line"></i></p>
                    <p class="first-page-number page-number current-page">1</p>
                    <p class="page-number">2</p>
                    <p class="page-number">3</p>
                    <p>...</p>
                    <p class="last-page-number page-number">500</p>
                    <p class="page-action-btn page-nect-btn"><i class="ri-arrow-right-s-line"></i></p>
                </div>
            </section>
        </form>

        <script src="./js/search_result/search_result.js"></script>

        <!--Footer import-->
        <%--<%@ include file="./Components/footer_wave.html" %>--%>  
        <%@ include file="./Components/footer.jsp" %>  
    </body>
</html>
