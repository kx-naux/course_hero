<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Course</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link href="../admin_css/adminCourse.css" rel="stylesheet">
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
                        <h1>Manage Course</h1>
                        <button type="button" class="add-button products">
                            <span class="add-button__text">Add Course</span>
                            <span class="add-button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
                        </button>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <!<!-- include profile  -->
                        <%@ include file="./Components/global-profile.jsp" %>
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
                            <option value="-1">Occupation</option>
                            <option value="web">Web Designer</option>
                            <option value="graphic">Graphic Designer</option>
                        </select>
                    </div>
                    <div class="input-container">
                        <select>
                            <option value="-1">Education</option>
                            <option value="edit">University</option>
                            <option value="delete">College</option>
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
                                       <i class="ri-image-line"></i>
                                   </th>
                                   <th scope="col">
                                       Course ID
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Course Name
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Course Category
                                   </th>
                                   <th scope="col">
                                       Description
                                   </th>
                                   <th scope="col">
                                       Price
                                   </th>
                                   <th scope="col">
                                       Status
                                   </th>
                                   <th scope="col">
                                       Date Added
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                               <label>Occupation</label>
                                               <div class="input-container">
                                                   <input type="text" name="occupation" placeholder="Occupation..." >
                                               </div>
                                               </div>
                                               <div class="update-container">
                                               <label>Contact</label>
                                               <div class="input-container">
                                                   <input type="text" name="name" placeholder="Contact..." >
                                               </div>
                                               </div>
                                               <div class="update-container">
                                               <label>Education</label>
                                               <div class="input-container">
                                                   <input type="text" name="name" placeholder="Education..." >
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
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
                                       Client ID
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">
                                       Name
                                       <div class="sort-by-container">
                                           <i class="ri-arrow-drop-up-fill"></i>
                                           <i class="ri-arrow-drop-down-fill"></i>
                                       </div>
                                   </th>
                                   <th scope="col">Occupation</th>
                                   <th scope="col">Contact</th>
                                   <th scope="col">Education</th>
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
