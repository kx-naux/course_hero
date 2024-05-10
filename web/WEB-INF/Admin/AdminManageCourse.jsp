<%@page import="java.util.ArrayList"%>
<%@page import="JPAEntity.CourseCategory"%>
<%@page import="JPAEntity.Courses"%>
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
        <% List<Courses> coursesList = (List<Courses>) request.getAttribute("coursesList"); %>
        <% List<CourseCategory> courseCatList = (List<CourseCategory>) request.getAttribute("courseCatList");%>
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
                        <span class="display-total-item">Showing <%= coursesList.size() %>  items</span>
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
                                       Price (RM)
                                   </th>
                                   <th scope="col">
                                       Status
                                   </th>
                               </tr>
                           </thead>
                           <tbody>
                               <!--                               This is the row for every record -->
                               <%

                                   for (int i = 0; i < coursesList.size(); i++) {
                                       Courses course = coursesList.get(i);
                               %>
                               <tr scope="row">
                                   <td>
                                       <%= course.getCourseId() %>
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                       </div>
                                   </td>
                                   <td>
                                       <%= course.getProductId().getProdName() %>
                                   </td>
                                   <td class="description"><%= course.getProductId().getDescription()%></td>
                                   <td>
                                       <%= course.getCoursecatId().getCategoryName() %>
                                   </td>
                                   <td><%= course.getProductId().getPrice() %></td>
                                   <td><%= course.getProductId().getStatus() %></td>
                                   <!---------------------- Edit Item ----------------------------------->
                                   <td class="edit-items-container">
                                       <form method="post" action="manage-course" id="form-<%= course.getCourseId() %>">
                                           <input type="text"  name="courseId" id="courseId" value="<%= course.getCourseId() %>" hidden/>
                                           <h2>Edit Item</h2>
                                            <div class="edit-items"> 
                                          
                                               <div class="update-container">
                                                   <label>Course Name</label>
                                                    <div class="input-container">
                                                        <input type="text" name="productName" id="productName" required maxlength="50" placeholder="Course Name" >
                                                    </div>
                                               </div>
                                               <div class="update-container">
                                               <label>Course Category</label>
                                               <div class="input-container">
                                                   <select id="courseCategory" name="courseCategory">
                                                       <%
                                                           // Iterate through each merchandise in the original list
                                                           for (int a = 0; a < courseCatList.size(); a++) {
                                                       %>
                                                       <option value="<%= courseCatList.get(a).getCoursecatId()%>"><%= courseCatList.get(a).getCategoryName()%></option>
                                                       <%}%>
                                                   </select>
                                               </div>
                                               </div>
                                               <div class="update-container">
                                                   <label>Price</label>
                                                   <div class="input-container">
                                                       <input type="number" name="price" id="price" required placeholder="Price" >
                                                   </div>
                                               </div>
                                                   <div class="update-container">  
                                                       <label>Status</label>
                                                       <div class="input-container radio">
                                                           <div class="input-radio">
                                                               <input type="radio" name="status" id="active" value="Active" required checked/>
                                                               <label for="active">Active</label>
                                                           </div>
                                                           <div class="input-radio">
                                                               <input type="radio" name="status" id="inactive" value="Inactive" required>
                                                               <label for="inactive">Inactive</label>
                                                           </div>
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
                               <%}%>
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