<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Customers</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminCustomer.css">
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
                        <h1>Manage Customers</h1>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <div class="profile">
                            <div class="info">
                                <p>Hey, <b>Snijders</b></p>
                                <small>Admin</small>
                            </div>
                            <div class="profile-photo">
                                <img src="../img/admin/default_profile.png" alt="alt"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <div class="filter-flex-container">
                <div class="filter-container">
                    <div class="input-container" title="Date Joined">
                        <input type="date">
                    </div>
                    <div class="input-container" title="Validity">
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
                                       Account ID
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
                                       Username
                                   </th>
                                   <th scope="col">
                                       Email
                                   </th>
                                   <th scope="col">
                                       Country
                                   </th>
                                   <th scope="col">
                                       Validity
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Date Joined
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
                                       1392
                                   </td>
                                   <td>
                                       James Yates
                                   </td>
                                   <td>
                                       Web Designer
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+63 983 0962 971</td>
                                   <td>NY University</td>
                               </tr>
                               
                               <tr>
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       4616
                                   </td>
                                   <td>Matthew Wasil</td>
                                   <td>
                                       Graphic Designer
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+02 020 3994 929</td>
                                   <td>London College</td>
                               </tr>
                               <tr>
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       9841
                                   </td>
                                   <td>Sampson Murphy</td>
                                   <td>
                                       Mobile Dev
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+01 352 1125 0192</td>
                                   <td>Senior High</td>
                               </tr>
                               <tr>
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       9548
                                   </td>
                                   <td>Gaspar Semenov</td>
                                   <td>
                                       Illustrator
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+92 020 3994 929</td>
                                   <td>College</td>
                               </tr>
                               <tr>
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       4616
                                   </td>
                                   <td>Matthew Wasil</td>
                                   <td>
                                       Graphic Designer
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+02 020 3994 929</td>
                                   <td>London College</td>
                               </tr>
                               <tr>
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       9841
                                   </td>
                                   <td>Sampson Murphy</td>
                                   <td>
                                       Mobile Dev
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+01 352 1125 0192</td>
                                   <td>Senior High</td>
                               </tr>
                               <tr>
                                   <th scope="row">
                                       <label class="control control--checkbox">
                                           <input type="checkbox" />
                                           <div class="control__indicator">
                                               <i class="ri-check-line"></i>
                                           </div>
                                       </label>
                                   </th>
                                   <td>
                                       9548
                                   </td>
                                   <td>Gaspar Semenov</td>
                                   <td>
                                       Illustrator
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+92 020 3994 929</td>
                                   <td>College</td>
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
                                       Account ID
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
                                       Username
                                   </th>
                                   <th scope="col">
                                       Email
                                   </th>
                                   <th scope="col">
                                       Country
                                   </th>
                                   <th scope="col">
                                       Validity
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Date Joined
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
