<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminOrder.css">
        <link rel="stylesheet" href="../admin_css/adminTable.css">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body> 
        <div class="flex-container">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <div class="main-top">
                    <div class="main-top-left">
                        <h1>Manage Orders</h1>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        
                        <!-- include profile  -->
                         <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <div class="filter-flex-container">
                <div class="filter-container">
                    <div class="input-container">
                        <select>
                            <option value="-1">Status</option>
                            <option value="web">Shipping</option>
                            <option value="graphic">Completed</option>
                            <option value="graphic">Cancelled</option>
                        </select>
                    </div>
                    <div class="input-container" title="Shipping Date">
                        <input type="date">
                    </div>
                    <div class="input-container" title="Expected Arrival Date">
                        <input type="date">
                    </div>
                    <div class="input-container" title="order Date">
                        <input type="date">
                    </div>
                    <div class="submit-container">
                        <input type="submit" value="Filter" class="submit-button">
                    </div>
                </div>
                <div class="search-container">
                    <div class="input-container">
                        <input type="search" placeholder="Search..." >
                    </div>
                    <div class="submit-container">
                        <input type="submit" value="Search for Clients" class="submit-button">
                    </div>
                </div>
                </div>
                <!----------  START OF TABLE ------------------->
                <div class="table-container">
                    <div class="tablenav-pages"> 
                        <div class="display-total-item-container">
                        <span class="display-total-item">Showing 7 items of 120 items</span>
                        </div>
                        <div class="pagination-links-container">
                        <span class="pagination-links">
                            <a class="first-page" href="#"><<</a>
                            <a class="prev-page" href="#"><</a>
                        </span>
                        <span class="pagination-input">
                            <div class="input-container">
                                <input class="current-page" type="text" value="1" size="1"/>
                            </div>
                            <span class="tablenav-paging-text">
                                of
                                <span class="total-pages">2</span>
                            </span>
                        </span>
                        <span class="pagination-links">
                            <a class="next-page" href="#">></a>
                            <a class="last-page" href="#">>></a>
                        </span>
                        </div>
                    </div>
                    <table class="client-table custom-table">
                           <thead>
                               <tr>
                                   <th scope="col">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" class="js-check-all" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <th scope="col">
                                       Transaction ID
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       User ID
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Product
                                   </th>
                                   <th scope="col">
                                       Status
                                   </th>
                                   <th scope="col">
                                       Total
                                   </th>
                                   <th scope="col">
                                       Shipping Date
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Expected Arrival Date
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Order Date
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                               </tr>
                           </thead>
                           <tbody>
                               <tr scope="row">
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       TR0000001
                                   </td>
                                   <td>
                                       U00000001
                                   </td>
                                   <td>
                                       Algebra Part 1
                                       <small class="d-block">x 2 = RM 3000</small>
                                       Algebra Rocks Hoodie
                                       <small class="d-block">x 1 = RM 800</small>
                                       Javascript
                                       <small class="d-block">x 1 = RM 100</small>
                                       C++
                                       <small class="d-block">x 1 = RM 50</small>
                                   </td>
                                   <td>Completed</td>
                                   <td>RM32443</td>
                                   <td>2024-04-14</td>
                                   <td>2024-04-21</td>
                                   <td>2024-04-14</td>
                               </tr>
                           </tbody>
                           <tfoot>
                               <tr>
                                   <th scope="col">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" class="js-check-all" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <th scope="col">
                                       Transaction ID
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       User ID
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Product
                                   </th>
                                   <th scope="col">
                                       Status
                                   </th>
                                   <th scope="col">
                                       Total
                                   </th>
                                   <th scope="col">
                                       Shipping Date
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Expected Arrival Date
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Order Date
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                               </tr>
                           </tfoot>
                       </table>
                    <div class="tablenav-pages"> 
                        <div class="display-total-item-container">
                        <span class="display-total-item">Showing 7 items of 120 items</span>
                        </div>
                        <div class="pagination-links-container">
                        <span class="pagination-links">
                            <a class="first-page" href="#"><<</a>
                            <a class="prev-page" href="#"><</a>
                        </span>
                        <span class="pagination-input">
                            <div class="input-container">
                                <input class="current-page" type="text" value="1" size="1"/>
                            </div>
                            <span class="tablenav-paging-text">
                                of
                                <span class="total-pages">2</span>
                            </span>
                        </span>
                        <span class="pagination-links">
                            <a class="next-page" href="#">></a>
                            <a class="last-page" href="#">>></a>
                        </span>
                        </div>
                    </div>
                </div>
            </div>
            <!----------  END OF MAIN ------------------->
            </div>
            <!-- Right after the browser renders the content -->
            <script type="text/javascript">
                // If localStorage is supported by the browser
                if (typeof(Storage) !== "undefined") {
                    // If we need to open the bar
                    if(localStorage.getItem("theme") === "dark"){

                        // check the dark theme button
                        let inputContainers = document.querySelectorAll(".input-container");
                        for (var i = 0; i < inputContainers.length; ++i) {
                        inputContainers[i].classList.add("dark");
                        }
                    }
                }
            </script>
            <script type="text/javascript" src="../admin_js/sweetalert2.all.js"></script>
            <script type="text/javascript" src="../admin_js/adminTable.js"></script>
    </body>
</html>
