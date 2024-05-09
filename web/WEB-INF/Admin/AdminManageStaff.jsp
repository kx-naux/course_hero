<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Staff</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link href="../admin_css/adminStaff.css" rel="stylesheet">
        <link href="../admin_css/adminTable.css" rel="stylesheet">
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
                        <h1>Manage Staff</h1>
                        <a href="<%= webpath.getPageUrl("add-staff") %>">
                        <button type="button" class="add-button products">
                            <span class="add-button__text">Add Staff</span>
                            <span class="add-button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
                        </button>
                        </a>
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
                <div class="bulk-action-container">
                    <div class="input-container">
                        <select>
                            <option value="-1">Bulk Actions</option>
                            <option value="edit">Edit</option>
                            <option value="delete">Delete</option>
                        </select>
                    </div>
                    <div class="submit-container">
                        <input type="submit" value="Apply" class="submit-button">
                    </div>
                </div>
                <div class="filter-container">
                    <div class="input-container">
                        <input type="date">
                    </div>
                    <div class="input-container">
                        <select>
                            <option value="-1">Country</option>
                            <option value="web">UK</option>
                            <option value="graphic">Malaysia</option>
                        </select>
                    </div>
                    <div class="input-container">
                        <select>
                            <option value="-1">Gender</option>
                            <option value="edit">Male</option>
                            <option value="delete">Female</option>
                        </select>
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
                    <table class="product-table custom-table">
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
                                       Name
                                   </th>
                                   <th scope="col">
                                       Role
                                   </th>
                                   <th scope="col">
                                       Email
                                   </th>
                                   <th scope="col">
                                       Country
                                   </th>
                                   <th scope="col">
                                       Gender
                                   </th>
                                   <th scope="col">
                                       Birth Date
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
                                       AC00000002
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
                                   </td>
                                   <td>
                                       U000000002
                                   </td>
                                   <td>
                                       KahXuan
                                   </td>
                                   <td>Pentamaster Manager</td>
                                   <td>lowkx-pm22@student.tarc.edu.my</td>
                                   <td>UK</td>
                                   <td>Male</td>
                                   <td>12-12-2012</td>
                                   <td>12-12-2012</td>
                                   <!---------------------- Edit Item ----------------------------------->
                                   <td class="edit-items-container">
                                       <form>
                                           <h2>Edit Item</h2>
                                            <div class="edit-items"> 
                                          
                                               <div class="update-container">
                                                   <label>Name</label>
                                                    <div class="input-container">
                                                        <input type="text" name="name" placeholder="Name..." >
                                                    </div>
                                               </div>
                                               <div class="update-container">
                                               <label>Role</label>
                                               <div class="input-container">
                                                   <input type="text" name="occupation" placeholder="Occupation..." >
                                               </div>
                                               </div>
                                               <div class="update-container">
                                               <label>Email</label>
                                               <div class="input-container">
                                                   <input type="email" name="name" placeholder="Contact..." >
                                               </div>
                                               </div>
                                               <div class="update-container">
                                               <label>Country</label>
                                               <div class="input-container">
                                                   <input type="text" name="name" placeholder="Education..." >
                                               </div>
                                               </div>
                                                <div class="update-container">
                                                    <label>Birth Date</label>
                                                    <div class="input-container">
                                                        <input type="date" name="name" placeholder="Education..." >
                                                    </div>
                                                </div>
                                            </div>
                                           <div class="update-container submit">
                                               <div class="submit-container update">
                                                   <input type="submit" value="Update" class="submit-button">
                                               </div>
                                               <div class="submit-container cancel">
                                                   <input type="button" value="Cancel" class="submit-button">
                                               </div>
                                           </div>
                                        </form>
                                   </td>
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
                                       Role
                                   </th>
                                   <th scope="col">
                                       Email
                                   </th>
                                   <th scope="col">
                                       Country
                                   </th>
                                   <th scope="col">
                                       Gender
                                   </th>
                                   <th scope="col">
                                       Birth Date
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
