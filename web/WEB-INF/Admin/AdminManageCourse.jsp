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
                        <a href="<%= webpath.getPageUrl("add-course") %>">
                        <button type="button" class="add-button products">
                            <span class="add-button__text">Add Course</span>
                            <span class="add-button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
                        </button>
                        </a>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <!<!-- include profile  -->
                        <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <!----------  START OF TABLE ------------------->
                <div class="table-container">
                    <div class="tablenav-pages"> 
                        <div class="display-total-item-container">
                        <span class="display-total-item">Showing 7 items</span>
                        </div>
                    </div>
                    <table class="product-table custom-table">
                           <thead>
                               <tr>
                                   <th scope="col">
                                       Course ID
                                   </th>
                                   <th scope="col">
                                       Course Name
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
                               </tr>
                           </thead>
                           <tbody>
                               <tr scope="row">
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
                           </tbody>
                       </table>
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
